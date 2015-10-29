package visitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import analyzer.SumJavaCode;
import util.FileUtil;
import util.JdtAstUtil;

public class LineOfCodeVisitor extends ASTVisitor {
	private int LineOfImports = 0;
	private String unitPath;
	private CompilationUnit unit;
	private ArrayList<List<String>> output = new ArrayList<List<String>>();
	int noCommentCodeLine, commentLines, totalLines;
	private SumJavaCode sjc;
	private boolean isVisited;
	private String source, packageName, className;
	private String fullClassName;

	public LineOfCodeVisitor(String dirPath) throws Exception {
		super();

		FileUtil util = new FileUtil();
		ArrayList<String> pathList = util.getAllJavaFilePath(dirPath);
		// System.out.println(dirPath);
		sjc = new SumJavaCode();
		for (String path : pathList) {
			unitPath = path;
			unit = JdtAstUtil.getCompilationUnit(unitPath);
			source = JdtAstUtil.getSource(unitPath);

			// int[] result = sjc.LineOfCode(JdtAstUtil.getFile(unitPath));
			// noCommentCodeLine+=result[0];
			// commentLines+=result[1];
			unit.accept(this);
			isVisited=false;
		}

		// System.out.println(noCommentCodeLine+"\t"+commentLines);
	}

	public boolean visit(TypeDeclaration node) {
		if (!isVisited) {
			if (node.isInterface()) {

			} else {
				// classCount++;
				List<String> tempStr = new ArrayList<String>();
				
				if(unit.getPackage()!=null){
					packageName = unit.getPackage().getName()
							.getFullyQualifiedName();
				}
				className = node.getName().getIdentifier();
				fullClassName = packageName + "." + className;
				tempStr.add(fullClassName);
				
				int startLine, endLine;
				startLine = unit.getLineNumber(node.getStartPosition());
				int length = node.getLength()-1;
				endLine = unit.getLineNumber(node.getStartPosition()+length );

				int[] result = sjc.LineOfCode(JdtAstUtil.getFile(unitPath),
						startLine, endLine);
				noCommentCodeLine = result[0];
				commentLines = result[1];
				tempStr.add(((Integer) noCommentCodeLine).toString());
				tempStr.add(unitPath);
				output.add(tempStr);

			}
			isVisited = true;
		} else {
			// interClassCount++;
		}

		return true;

	}

	public ArrayList<List<String>> getDetailResult() {
		return output;

	}
}
