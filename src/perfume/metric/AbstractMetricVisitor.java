package perfume.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

public abstract class AbstractMetricVisitor extends AbstractMetric {
	
	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		setPkgName(compUnit);
	}	
	
	public void afterMetric() {
	}
}
