package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class LOC_METHODTest {
	public LOC_METHODTest(String projectPath) {
		AbstractMetricVisitor measurement = new LOC_METHODMetricVisitor();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
	}
}
