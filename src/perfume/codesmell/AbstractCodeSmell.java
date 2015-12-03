package perfume.codesmell;

public abstract class AbstractCodeSmell {
	private String RESULT_DIR = "D://";
	
	protected String getFileName(String codeSmellName, String projectName) {
		return RESULT_DIR + codeSmellName + projectName + ".csv";
	}
	
	protected abstract String getCodeSmellName();
}
