package perfume.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public abstract class AbstractMetricVisitor extends AbstractMetric {
	
	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		getPkgName(compUnit);
	}	
	
	public void afterMetric() {
	}
	
	/**
	 * Get the package name of the class
	 * @param compUnit
	 */
	private void getPkgName(CompilationUnit compUnit) {
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
