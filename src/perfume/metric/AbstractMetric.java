package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

public abstract class AbstractMetric extends ASTVisitor {

	protected StringBuilder mPkgNameBuilder = new StringBuilder();
	
	/**
	 * Before the measurement started
	 */
	public abstract void beforeMetric(String projectPath, CompilationUnit compUnit);
	
	/**
	 * After the measurement finished
	 */
	public abstract void afterMetric();
	
	/**
	 * Return the metric result
	 * @return
	 */
	public abstract HashMap<String, Long> getMetricResult();
}
