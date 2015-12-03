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
import perfume.util.StringUtil;
import perfume.util.ast.JdtAstUtil;

public class CYCLOMethodMetric extends AbstractMetricVisitor {

	private HashMap<String, Long> CYCLOMethodMetric = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;
	private int cyclomatic = 1;
	private String source;
	private MethodParam param;

	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);

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
			cyclomatic = 1;
		}
		return true;

	}

	public void endVisit(MethodDeclaration node) {
		//方法度量结束 输出结果到CYCLOMethodMetric中
		param.setMethodCyclomatic(cyclomatic);
		String methodName = StringUtil.stringConnection(
				getPkgClassName(), ".",
				node.getName().toString());
		CYCLOMethodMetric.put(methodName, (long) cyclomatic);
		cyclomatic = 1;

	}

	public boolean visit(MethodDeclaration node) {

		param = new MethodParam();
		param.setPath(javaPath);
		param.setMethodName(node.getName().toString());
		param.setStartLineNum(compUnit.getLineNumber(node.getStartPosition()));
		param.setEndLineNum(compUnit.getLineNumber(node.getStartPosition() + node.getLength()));

		return true;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);
		this.javaPath = javaPath;
		try {
			source = JdtAstUtil.getSource(javaPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.compUnit = compUnit;
	}

	@Override
	public void afterMetric() {
		source = "";
		cyclomatic = 1;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return CYCLOMethodMetric;
	}

	@Override
	public String getMetricName() {
		return "CYCLO";
	}

}

