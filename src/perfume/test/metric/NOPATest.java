package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOPAMetric;
import perfume.util.LogUtil;

public class NOPATest {
	
	public NOPATest(String projectPath) {
		AbstractMetricVisitor measurement = new NOPAMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
