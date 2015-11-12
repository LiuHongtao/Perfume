package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.CYCLOMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class CYCLOTest {
	public CYCLOTest(String projectPath) {
		AbstractMetric measurement = new CYCLOMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());

	}
}
