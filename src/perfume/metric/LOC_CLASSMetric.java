package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.analyzer.SumJavaCode;
import perfume.util.ast.JdtAstUtil;

/**
 * <ul>
 * <li>Name: LOC_CLASS, Lines Of Code in a class</li>
 * <li>Description: The line of code in a single class node, not contains
 * imports lines and package deceleration lines.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: Number of Code Lines ,-2 Interface</li>
 * </ul>
 */
public class LOC_CLASSMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> LOC_CLASS = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;

	int noCommentCodeLine, commentLines, totalLines;

	private int blankLines;

	@Override
	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);

		if (node.isInterface()) {
			LOC_CLASS.put(getPkgClassName(), -2l);
			return true;
		}

		int startLine, endLine;
		startLine = compUnit.getLineNumber(node.getStartPosition());
		int length = node.getLength() - 1;
		endLine = compUnit.getLineNumber(node.getStartPosition() + length);

		int[] result = SumJavaCode.LineOfCode(JdtAstUtil.getFile(javaPath), startLine, endLine);
		noCommentCodeLine = result[0];
		commentLines = result[1];
		blankLines = result[2];
		totalLines = noCommentCodeLine + commentLines + blankLines;
		LOC_CLASS.put(getPkgClassName(), (long) noCommentCodeLine);
		noCommentCodeLine = 0;
		commentLines = 0;
		blankLines = 0;
		totalLines = 0;
		return true;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);
		noCommentCodeLine = 0;
		commentLines = 0;
		blankLines = 0;
		totalLines = 0;
		this.javaPath = javaPath;
		this.compUnit = compUnit;
	}

	@Override
	public void afterMetric() {
		// System.out.println("className:" + fullClassName + "\tLOCtotal:" +
		// totalLines + "\tlOJavaC:" + noCommentCodeLine
		// + "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return LOC_CLASS;
	}
}
