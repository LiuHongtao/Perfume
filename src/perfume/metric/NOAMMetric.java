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
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

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
		setPkgClassName(node);
		if (node.isInterface()) {
			NOAMMap.put(getPkgClassName(), -2l);
			return true;
		}
		
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
		
		long result = 0;
		if (fieldsNameSet.size() > 0) {
			result = countNOAM(
					fieldsTypeSet, 
					fieldsNameSet, 
					node.getMethods());
		}
		
		NOAMMap.put(
				getPkgClassName(), 
				result);
		
		return true;
	}
	
	private long countNOAM(HashSet<String> fieldsTypeSet, 
			HashSet<String> fieldsNameSet,
			MethodDeclaration[] methods) {
		long result = 0;		
		for (MethodDeclaration method: methods) {
			if (method.isConstructor() || 
					method.getBody() == null) {
				continue;
			}
			
			Type returnType = method.getReturnType2();			
			if (returnType == null) {
				continue;
			}	
			
			// what method returns void is not an accessor
			if (returnType instanceof PrimitiveType) {
				PrimitiveType type = (PrimitiveType)returnType;
				if (type.getPrimitiveTypeCode() == PrimitiveType.VOID) {
					continue;
				}
			}		
			
			if (fieldsTypeSet.contains(returnType.toString())) {
				List<Statement> statements = method.getBody().statements();
				if (statements.size() == 1 && 
						statements.get(0) instanceof ReturnStatement) {
					ReturnStatement returnStmt = (ReturnStatement)statements.get(0);
					Expression returnExp = returnStmt.getExpression();
					// return this.[Field]
					if (returnExp instanceof FieldAccess) {
						FieldAccess fieldAccess = (FieldAccess)returnExp;
						if (fieldAccess.getExpression() instanceof ThisExpression) {
							result++;
						}
					}
					// return [Field]
					else if (returnExp instanceof SimpleName && 
							fieldsNameSet.contains(returnExp.toString())) {
						result++;
					}
				}
			}
		}
		return result;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOAMMap;
	}

	@Override
	public String getMetricName() {
		return "NOAM";
	}

}
