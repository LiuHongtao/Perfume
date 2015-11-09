package perfume.metric.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.analyzer.SumJavaCode;
import perfume.util.ast.JdtAstUtil;

public class LOC_METHODMetricVisitor extends AbstractMetricVisitor {
	private String unitPath;
	private CompilationUnit compUnit;
	private ArrayList<List<String>> output = new ArrayList<List<String>>();
	int noCommentCodeLine, commentLines, totalLines;
	//private SumJavaCode sjc;

	private String packageName, className,methodName;
	private String fullClassName;
	private int blankLines;

	
	public boolean visit(TypeDeclaration node) {
		className = node.getName().getIdentifier();

		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {

		
			// classCount++;

			if (compUnit.getPackage() != null) {
				packageName = compUnit.getPackage().getName().getFullyQualifiedName();
			}
			
			
			methodName = node.getName().getIdentifier();
			fullClassName = packageName + "." + className+"."+methodName;
			int startLine, endLine;
			startLine = compUnit.getLineNumber(node.getStartPosition());
			int length = node.getLength() - 1;
			endLine = compUnit.getLineNumber(node.getStartPosition() + length);

			int[] result = SumJavaCode.LineOfCode(JdtAstUtil.getFile(unitPath), startLine, endLine);
			noCommentCodeLine = result[0];
			commentLines = result[1];
			blankLines = result[2];
			totalLines = noCommentCodeLine + commentLines + blankLines;

			System.out.println("MethodName:" + fullClassName + "\tLOCtotal:" + totalLines + "\tlOJavaC:" + noCommentCodeLine
					+ "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
			noCommentCodeLine = 0;
			commentLines = 0;
			blankLines = 0;
			totalLines = 0;

		return false;
	}

	@Override
	public void beforeMeasurement(String javaPath, CompilationUnit compUnit) {
		noCommentCodeLine = 0;
		commentLines = 0;
		blankLines = 0;
		totalLines = 0;
		unitPath = javaPath;
		 
		this.compUnit = compUnit;
	}

	@Override
	public void afterMeasurement() {
		
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
