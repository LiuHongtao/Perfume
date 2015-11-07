package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class NOATest {
	public NOATest(String projectPath) {
		AbstractMeasurement measurement = new NOAMetricVisitor();

		MetricUtil.startMetric(projectPath, measurement);
	}
}
