package perfume.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;

public abstract class AbstractMeasurement extends ASTVisitor {
	/**
	 * Before the measurement started
	 */
	public abstract void beforeMeasurement();
	
	/**
	 * After the measurement finished
	 */
	public abstract void afterMeasurement();
}
