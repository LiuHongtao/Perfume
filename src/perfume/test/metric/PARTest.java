package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.MetricUtil;
import perfume.metric.PARMetric;
import perfume.util.LogUtil;

public class PARTest {
	
	public PARTest(String projectPath) {
		AbstractMetric measurement = new PARMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
