package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.LOC_METHODMetric;
import perfume.metric.MetricUtil;

public class LOC_METHODTest {
	public LOC_METHODTest(String projectPath) {
		AbstractMetricVisitor measurement = new LOC_METHODMetric();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
	}
}
