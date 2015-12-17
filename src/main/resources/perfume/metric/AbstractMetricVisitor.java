package main.resources.perfume.metric;

import org.eclipse.jdt.core.dom.CompilationUnit;

public abstract class AbstractMetricVisitor extends AbstractMetric {
	
	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		setPkgName(compUnit);
	}	
	
	@Override
	public void afterMetric() {
	}
}
