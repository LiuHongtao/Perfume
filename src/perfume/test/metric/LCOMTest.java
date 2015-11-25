package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.LCOMMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class LCOMTest {
	public LCOMTest(String projectPath) {
		AbstractMetric measurement = new LCOMMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
