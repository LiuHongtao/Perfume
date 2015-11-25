package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.MetricUtil;
import perfume.metric.NOAMetric;
import perfume.util.LogUtil;

public class NOATest {
	public NOATest(String projectPath) {
		AbstractMetric measurement = new NOAMetric();
		MetricUtil.startMetric(projectPath, measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
