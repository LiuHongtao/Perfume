package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;

public class NOATest {
	public NOATest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAMetric();
		MetricUtil.startMetric(projectPath, measurement);
	}
}
