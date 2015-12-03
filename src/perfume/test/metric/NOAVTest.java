package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOAVMetric;
import perfume.util.LogUtil;

public class NOAVTest {

	public NOAVTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAVMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
