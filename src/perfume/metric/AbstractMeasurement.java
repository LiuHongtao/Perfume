package perfume.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

public abstract class AbstractMeasurement extends ASTVisitor {
	/**
	 * Before the measurement started
	 */
	public abstract void beforeMeasurement(String javaPath,CompilationUnit compUnit);
	
	/**
	 * After the measurement finished
	 */
	public abstract void afterMeasurement();
	
	public abstract long getMetricResult();
	
}
