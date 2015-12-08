package perfume.codesmell;

public abstract class AbstractCodeSmell {
	protected String RESULT_DIR = "D:\\perfume\\";
	
	public abstract void makeTrainingSet(String projectDir, String projectName);
	protected abstract String getCodeSmellName();
}
