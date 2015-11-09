package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.WMCMetricVisitor;

public class WMCTest {
	public WMCTest(String projectPath) {
		AbstractMetricVisitor measurement = new WMCMetricVisitor();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
	
	}
}
