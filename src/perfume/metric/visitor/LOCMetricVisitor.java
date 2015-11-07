package perfume.metric.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;

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
	public void beforeMeasurement(String javaPath, CompilationUnit compUnit) {
		this.javaPath = javaPath;
		int[] result = SumJavaCode.LineOfCode(JdtAstUtil.getFile(javaPath));
		noCommentCodeLine = result[0];
		commentLines = result[1];
		blankLines = result[2];
		totalLines = noCommentCodeLine + commentLines + blankLines;
		
	}

	@Override
	public void afterMeasurement() {
		System.out.println("FilePath:" + javaPath + "\tLOCtotal:" + totalLines + "\tlOJavaC:" + noCommentCodeLine
				+ "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
		
	}

	@Override
	public long getMetricResult() {
		// TODO Auto-generated method stub
		return 0;
	}

}
