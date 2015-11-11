package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOAMetric;

public class NOATest {
	public NOATest(String projectPath) {
		AbstractMetric measurement = new NOAMetric();
		MetricUtil.startMetric(projectPath, measurement);
	}
}
