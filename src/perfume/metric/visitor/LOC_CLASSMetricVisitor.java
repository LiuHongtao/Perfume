package perfume.metric.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.analyzer.SumJavaCode;
import perfume.util.LogUtil;
import perfume.util.XlsOutput;
import perfume.util.ast.InstanceOfUtil;
import perfume.util.ast.JdtAstUtil;

/**
 * <ul>
 * <li>Name: NOPA, Number Of Public Attribute</li>
 * <li>Description: The Number of Public Attributes, which are not static and
 * constant, of a class. Don't measured for Abstract classes, and inner classes?
 * </li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -1 for Interface</li>
 * </ul>
 */
public class LOC_CLASSMetricVisitor extends AbstractMetricVisitor {

	private String unitPath;
	private CompilationUnit compUnit;
	private ArrayList<List<String>> output = new ArrayList<List<String>>();
	int noCommentCodeLine, commentLines, totalLines;
	private SumJavaCode sjc;

	private String packageName, className;
	private String fullClassName;
	private int blankLines;

	@Override
	public boolean visit(TypeDeclaration node) {

		if (node.isInterface()) {

		} else {
			// classCount++;

			if (compUnit.getPackage() != null) {
				packageName = compUnit.getPackage().getName().getFullyQualifiedName();
			}
			className = node.getName().getIdentifier();
			fullClassName = packageName + "." + className;

			int startLine, endLine;
			startLine = compUnit.getLineNumber(node.getStartPosition());
			int length = node.getLength() - 1;
			endLine = compUnit.getLineNumber(node.getStartPosition() + length);

			int[] result = sjc.LineOfCode(JdtAstUtil.getFile(unitPath), startLine, endLine);
			noCommentCodeLine = result[0];
			commentLines = result[1];
			blankLines = result[2];
			totalLines = noCommentCodeLine + commentLines + blankLines;

		}

		return false;
	}

	@Override
	public void beforeMeasurement(String javaPath, CompilationUnit compUnit) {
		noCommentCodeLine = 0;
		commentLines = 0;
		blankLines = 0;
		totalLines = 0;
		unitPath = javaPath;
		sjc = new SumJavaCode();
		this.compUnit = compUnit;
	}

	@Override
	public void afterMeasurement() {
		System.out.println("className:" + fullClassName + "\tLOCtotal:" + totalLines + "\tlOJavaC:" + noCommentCodeLine
				+ "\tcommentLines:" + commentLines + "\tLOBlankL:" + blankLines);
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
