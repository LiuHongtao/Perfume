package perfume.metric.visitor;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.analyzer.SumJavaCode;
import perfume.util.ast.JdtAstUtil;

public class LOCMetricVisitor extends AbstractMetricVisitor{
	//private SumJavaCode sjc;
	private int noCommentCodeLine;
	private int commentLines;
	private int blankLines;
	private int totalLines;
	private String javaPath;

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		this.javaPath = javaPath;
		int[] result = SumJavaCode.LineOfCode(JdtAstUtil.getFile(javaPath));
		noCommentCodeLine = result[0];
		commentLines = result[1];
		blankLines = result[2];
		totalLines = noCommentCodeLine + commentLines + blankLines;
		
	}

	@Override
	public void afterMetric() {
		System.out.println("FilePath:" + javaPath + "\tLOCtotal:" + totalLines + "\tlOJavaC:" + noCommentCodeLine
				+ "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
		
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
