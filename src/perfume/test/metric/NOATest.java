package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class NOATest {
	public NOATest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAMetricVisitor();
		MetricUtil.startMetric(projectPath, measurement);
	}
}
