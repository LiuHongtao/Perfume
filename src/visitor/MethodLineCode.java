package visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import dto.MethodParam;
import analyzer.SumJavaCode;
import util.FileUtil;
import util.JdtAstUtil;

public class MethodLineCode extends ASTVisitor {
	private String unitPath;
	private CompilationUnit unit;
	private SumJavaCode sjc;
	private MethodParam param;

	public MethodLineCode(String dirPath) throws Exception {
		super();

		FileUtil util = new FileUtil();
		ArrayList<String> pathList = util.getAllJavaFilePath(dirPath);
		sjc = new SumJavaCode();
		for (String path : pathList) {
			unitPath = path;
			unit = JdtAstUtil.getCompilationUnit(unitPath);
			unit.accept(this);
		}
	}

	public boolean visit(MethodDeclaration node) {

		param = new MethodParam();
		param.setPath(unitPath);
		param.setMethodName(node.getName().toString());
		param.setStartLineNum(unit.getLineNumber(node.getStartPosition()));
		param.setEndLineNum(unit.getLineNumber(node.getStartPosition()
				+ node.getLength()));
		int[] result = sjc.LineOfCode(JdtAstUtil.getFile(unitPath),
				param.getStartLineNum(), param.getEndLineNum());
		param.setNoCommentCodeLine(result[0]);
		param.setLineOfCodeComment(result[1]);

		System.out.println(param.getResult());
		// return super.visit(node);
		return true;
	}

	// class MethodParam {
	// String path;
	// String methodName;
	// int lineOfCode;
	// int lineOfCodeComment;
	// int startLineNum;
	// int endLineNum;
	//
	//
	// public String getPath() {
	// return path;
	// }
	// public void setPath(String path) {
	// this.path = path;
	// }
	// public String getMethodName() {
	// return methodName;
	// }
	// public void setMethodName(String methodName) {
	// this.methodName = methodName;
	// }
	// public int getStartLineNum() {
	// return startLineNum;
	// }
	// public void setStartLineNum(int lineNum) {
	// this.startLineNum = lineNum;
	// }
	// public int getEndLineNum() {
	// return endLineNum;
	// }
	// public void setEndLineNum(int endLineNum) {
	// this.endLineNum = endLineNum;
	// }
	// public int getLineOfCode() {
	// return lineOfCode;
	// }
	// public void setLineOfCode(int lineOfCode) {
	// this.lineOfCode = lineOfCode;
	// }
	// public int getLineOfCodeComment() {
	// return lineOfCodeComment;
	// }
	// public void setLineOfCodeComment(int lineOfCodeComment) {
	// this.lineOfCodeComment = lineOfCodeComment;
	// }
	// public String getResult(){
	// String result =
	// getMethodName()+"\t"+getLineOfCode()+"\t"+getLineOfCodeComment();
	// return result;
	//
	// }
	// }
}
