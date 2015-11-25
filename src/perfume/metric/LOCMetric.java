package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.analyzer.SumJavaCode;
import perfume.util.ast.JdtAstUtil;
/**
 * <ul>
 * <li>Name: LOC, Line of Code Of Java File</li>
 * <li>Description: Line of Code Of Java File.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
public class LOCMetric  extends AbstractMetricVisitor{
	private HashMap<String, Long> LOCMetric = new HashMap<>();
	private int noCommentCodeLine;
	private int commentLines;
	private int blankLines;
	private int totalLines;
	private String javaPath;

	public boolean visit(TypeDeclaration node){
		setPkgClassName(node);
//		long result = 0;
//		
//		if (node.isInterface()) {
//			long result = -2;
//			LOCMetric.put(getPkgClassName().toString(), result);
//			return true;
//		}
//		
		return false;
	}
	
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
		LOCMetric.put(getPkgClassName(), (long)noCommentCodeLine);
//		System.out.println("FilePath:" + javaPath + "\tLOCtotal:" + totalLines + "\tlOJavaC:" + noCommentCodeLine
//				+ "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
		
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		
		return LOCMetric;
	}
}