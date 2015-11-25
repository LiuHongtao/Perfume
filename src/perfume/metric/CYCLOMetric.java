package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;

import perfume.metric.model.MethodParam;
import perfume.util.ast.JdtAstUtil;
/**
 * <ul>
 * <li>Name: CYCLO, McCabe’s CYCLOmatic complexity</li>
 * <li>Description: McCabe’s CYCLOmatic complexity of a java class.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: Total number of CYCLO of a class</li>
 * </ul>
 */
public class CYCLOMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> CYCLOMetric = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;
	private int cyclomatic = 1 ,totalCYCLO;
	private String source;
	private MethodParam param;
		

	
	public boolean visit(TypeDeclaration node){
		setPkgClassName(node);
		long result = 0;
		
		if (node.isInterface()) {
			result = -2;
			CYCLOMetric.put(getPkgClassName(), result);
			return false;
		}
		
		return true;
	}
	
	public boolean visit(CatchClause node) {
		cyclomatic++;
		return true;
	}

	public boolean visit(ConditionalExpression node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(DoStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(ForStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(IfStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(SwitchCase node) {
		if (!node.isDefault())
			cyclomatic++;
		return true;
	}

	public boolean visit(WhileStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(ExpressionStatement exs) {
		inspectExpression(exs.getExpression());
		return false;
	}

	public int getCyclomatic() {
		return cyclomatic;
	}

	/**
	 * Count occurrences of && and || (conditional and or) Fix for BUG 740253
	 * 
	 * @param ex
	 */
	private void inspectExpression(Expression ex) {
		if (ex != null) {
			int start = ex.getStartPosition();
			int end = ex.getStartPosition() + ex.getLength();
			String expression = source.substring(start, end);
			// System.out.print(expression);
			char[] chars = expression.toCharArray();
			for (int i = 0; i < chars.length - 1; i++) {
				char next = chars[i];
				if ((next == '&' || next == '|') && (next == chars[i + 1])) {

					cyclomatic++;
				}
			}
		}
	}

	public boolean preVisit2(ASTNode node) {
		if ((node instanceof MethodDeclaration) && (param != null)) {
//			param.setMethodCyclomatic(cyclomatic);
//			System.out.println(param.getMethodName()+"\t"+param.getMethodCyclomatic());
			cyclomatic = 1;
		}
		return true;

	}
	
	public void endVisit(MethodDeclaration node) {
		
			param.setMethodCyclomatic(cyclomatic);
			//System.out.println("MethodName:"+param.getMethodName()+"\t"+param.getMethodCyclomatic());
			totalCYCLO+=cyclomatic;
			cyclomatic = 1;
		


	}

	public boolean visit(MethodDeclaration node) {


		param = new MethodParam();
		param.setPath(javaPath);
		param.setMethodName(node.getName().toString());
		param.setStartLineNum(compUnit.getLineNumber(node.getStartPosition()));
		param.setEndLineNum(compUnit.getLineNumber(node.getStartPosition()
				+ node.getLength()));

		
		return true;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);
		this.javaPath = javaPath;
		try {
			source = JdtAstUtil.getSource(javaPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(javaPath);
		this.compUnit = compUnit;
	}

	@Override
	public void afterMetric() {
		source = "";
		//System.out.println("Total CYCLO of this java file :"+totalCYCLO);
		CYCLOMetric.put(getPkgClassName(), (long)totalCYCLO);
		cyclomatic = 1;
		totalCYCLO = 0;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		
		return CYCLOMetric;
	}

}

