package perfume.metric;

import java.util.HashMap;


import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.analyzer.SumJavaCode;
import perfume.util.StringUtil;
import perfume.util.ast.JdtAstUtil;
/**
 * <ul>
 * <li>Name: LOC_METHOD, Lines Of Code in a method</li>
 * <li>Description: The line of code in one method.
 * <li>Granularity: Class</li>
 * <li>Default Values: Number of Code Lines </li>
 * </ul>
 */
public class LOC_METHODMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> LOC_METHOD = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;
	int noCommentCodeLine, commentLines, totalLines;
	private int blankLines;

	
	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);

		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
			String methodName = StringUtil.stringConnection(
					getPkgClassName(), ".",
					node.getName().getIdentifier());
			
			int startLine, endLine;
			startLine = compUnit.getLineNumber(node.getStartPosition());
			int length = node.getLength() - 1;
			endLine = compUnit.getLineNumber(node.getStartPosition() + length);

			int[] result = SumJavaCode.LineOfCode(JdtAstUtil.getFile(javaPath), startLine, endLine);
			noCommentCodeLine = result[0];
			commentLines = result[1];
			blankLines = result[2];
			totalLines = noCommentCodeLine + commentLines + blankLines;
			LOC_METHOD.put(methodName, (long)noCommentCodeLine);
			
//			System.out.println("MethodName:" + fullClassName + "\tLOCtotal:" + totalLines + "\tlOJavaC:" + noCommentCodeLine
//					+ "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
			noCommentCodeLine = 0;
			commentLines = 0;
			blankLines = 0;
			totalLines = 0;

		return false;
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
		
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		 
		return LOC_METHOD;
	}

	@Override
	public String getMetricName() {
		return "LOC_METHOD";
	}
}
