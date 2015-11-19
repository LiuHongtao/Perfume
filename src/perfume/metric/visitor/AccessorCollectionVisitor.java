package perfume.metric.visitor;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import perfume.util.StringUtil;
import perfume.util.ast.MethodUtil;

/**
 * collection of accessor in the whole project
 * @author lht-Mac
 * 不够严谨
 */
public class AccessorCollectionVisitor extends ASTVisitor {

	private HashSet<String> accessorSet = new HashSet<>();

	public HashSet<String> getAccessorSet() {
		return accessorSet;
	}

	private String mPkgName = "";
	private HashSet<String> fieldsTypeSet = new HashSet<>();
	private HashSet<String> fieldsNameSet = new HashSet<>();

	@Override
	public boolean visit(CompilationUnit node) {
		PackageDeclaration pd = node.getPackage();
		if (pd != null) {
			mPkgName = node.getPackage().getName().toString();
		}
		
		for (Object o: node.types()) {
			if (o instanceof TypeDeclaration) {
				fieldsTypeSet.clear();
				fieldsNameSet.clear();
						
				TypeDeclaration type = (TypeDeclaration)o;
				String className = type.getName().toString();
				visitField(className, type.getFields());
				visitAccessor(className, type.getMethods());
			}
		}
		return super.visit(node);
	}

	private void visitField(String className, FieldDeclaration[] fields) {
		for (FieldDeclaration field: fields) {
			String fieldsType = field.getType().toString();
			for (VariableDeclarationFragment variable: 
				(List<VariableDeclarationFragment>)field.fragments()) {
				String varName = variable.getName().toString();

				fieldsTypeSet.add(fieldsType);
				fieldsNameSet.add(varName);
			}
		}
	}

	private void visitAccessor(String className, MethodDeclaration[] methods) {
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
							accessorSet.add(
									StringUtil.stringConnection(
//											mPkgName, ".", 
//											className, ".", 
											method.getName().toString()));
						}
					}
					// return [Field]
					else if (returnExp instanceof SimpleName && 
							fieldsNameSet.contains(returnExp.toString())) {
						accessorSet.add(
								StringUtil.stringConnection(
//										mPkgName, ".", 
//										className, ".", 
										method.getName().toString()));
					}
				}
			}
		}
	}
}
