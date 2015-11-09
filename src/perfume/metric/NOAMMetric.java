package perfume.metric;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import perfume.util.ast.InstanceOfUtil;
import perfume.util.ast.MethodUtil;

/**
 * <ul>
 * <li>Name: NOAM, Number Of Accessor Methods</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
public class NOAMMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOAMMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {
		mPkgNameBuilder.append(node.getName().toString());		
		
		// collect fields information
		HashSet<String> fieldsTypeSet = new HashSet<>();
		HashSet<String> fieldsNameSet = new HashSet<>();
		for (FieldDeclaration field: node.getFields()) {
			String fieldsType = field.getType().toString();
			for (VariableDeclarationFragment variable: 
				(List<VariableDeclarationFragment>)field.fragments()) {
				fieldsTypeSet.add(fieldsType);
				fieldsNameSet.add(variable.getName().toString());
			}
		}
		
		if (fieldsNameSet.size() > 0) {
			countNOAM(fieldsTypeSet, fieldsNameSet, node.getMethods());	
		}	
		
		return false;
	}
	
	private void countNOAM(HashSet<String> fieldsTypeSet, 
			HashSet<String> fieldsNameSet,
			MethodDeclaration[] methods) {
		long result = 0;		
		for (MethodDeclaration method: methods) {
			if (method.isConstructor() || 
					MethodUtil.isAbstract(method)) {
				continue;
			}
			
			Type returnType = method.getReturnType2();			
			if (returnType == null) {
				continue;
			}	
			
			// what method returns void is not an accessor
			if (InstanceOfUtil.isPrimitiveType(returnType)) {
				PrimitiveType type = (PrimitiveType)returnType;
				if (type.getPrimitiveTypeCode() == PrimitiveType.VOID) {
					continue;
				}
			}		
			
			if (fieldsTypeSet.contains(returnType.toString())) {
				List<Statement> statements = method.getBody().statements();
				if (statements.size() == 1 && 
						InstanceOfUtil.isReturnStatement(statements.get(0))) {
					ReturnStatement returnStmt = (ReturnStatement)statements.get(0);
					Expression returnExp = returnStmt.getExpression();
					// return this.[Field]
					if (InstanceOfUtil.isFieldAccess(returnExp)) {
						FieldAccess fieldAccess = (FieldAccess)returnExp;
						if (InstanceOfUtil.isThisExpression(
								fieldAccess.getExpression())) {
							result++;
						}
					}
					// return [Field]
					else if (InstanceOfUtil.isSimpleName(returnExp) && 
							fieldsNameSet.contains(returnExp.toString())) {
						result++;
					}
				}
			}
		}
		NOAMMap.put(mPkgNameBuilder.toString(), result);
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOAMMap;
	}

}
