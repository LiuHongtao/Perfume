package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public abstract class AbstractMeasurement extends ASTVisitor {

	protected StringBuilder mPkgNameBuilder = new StringBuilder();
	
	/**
	 * Before the measurement started
	 */
	public abstract void beforeMeasurement(String javaPath,CompilationUnit compUnit);
	
	/**
	 * After the measurement finished
	 */
	public abstract void afterMeasurement();
	
	/**
	 * Return the metric result
	 * @return
	 */
	public abstract HashMap<String, Long> getMetricResult();
	
	/**
	 * Get the package name of the class
	 * @param compUnit
	 */
	protected void getPkgName(CompilationUnit compUnit) {
		PackageDeclaration pd = compUnit.getPackage();
		if (pd != null) {
			mPkgNameBuilder = new StringBuilder(compUnit.getPackage().getName().toString());
			mPkgNameBuilder.append('.');
		}
		else {
			mPkgNameBuilder = new StringBuilder();
		}
	}
	
}
