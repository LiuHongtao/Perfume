package perfume.metric;

import java.util.HashMap;


import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.analyzer.SumJavaCode;
import perfume.util.ast.JdtAstUtil;

public class LOC_METHODMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> LOC_METHOD = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;
	int noCommentCodeLine, commentLines, totalLines;
	private int blankLines;

	
	public boolean visit(TypeDeclaration node) {
		mPkgNameBuilder.append(node.getName().toString());

		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {

		
			
			String methodName = node.getName().getIdentifier();
			mPkgNameBuilder.append(methodName);
			int startLine, endLine;
			startLine = compUnit.getLineNumber(node.getStartPosition());
			int length = node.getLength() - 1;
			endLine = compUnit.getLineNumber(node.getStartPosition() + length);

			int[] result = SumJavaCode.LineOfCode(JdtAstUtil.getFile(javaPath), startLine, endLine);
			noCommentCodeLine = result[0];
			commentLines = result[1];
			blankLines = result[2];
			totalLines = noCommentCodeLine + commentLines + blankLines;
			LOC_METHOD.put(mPkgNameBuilder.toString(), (long)noCommentCodeLine);
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
}
