package dto;

public class MethodParam {
	public String path;
	private String methodName;
	private int noCommentCodeLine;
	private int lineOfCodeComment;
	private int startLineNum;
	private int endLineNum;
	private int methodCyclomatic =1;
	private String className;
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getNoCommentCodeLine() {
		return noCommentCodeLine;
	}

	public void setNoCommentCodeLine(int lineOfCode) {
		this.noCommentCodeLine = lineOfCode;
	}

	public int getLineOfCodeComment() {
		return lineOfCodeComment;
	}

	public void setLineOfCodeComment(int lineOfCodeComment) {
		this.lineOfCodeComment = lineOfCodeComment;
	}

	public int getStartLineNum() {
		return startLineNum;
	}

	public void setStartLineNum(int startLineNum) {
		this.startLineNum = startLineNum;
	}

	public int getEndLineNum() {
		return endLineNum;
	}

	public void setEndLineNum(int endLineNum) {
		this.endLineNum = endLineNum;
	}

	public int getMethodCyclomatic() {
		return methodCyclomatic;
	}

	public void setMethodCyclomatic(int methodCyclomatic) {
		this.methodCyclomatic = methodCyclomatic;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public String getResult() {
		String result = getMethodName() + "\t" + getNoCommentCodeLine() + "\t"
				+ getLineOfCodeComment()+"\t"+getMethodCyclomatic();
		return result;

	}
}
