package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.CYCLOMethodMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class CYCLOMethodTest {


	public CYCLOMethodTest(String projectPath) {
		AbstractMetric measurement = new CYCLOMethodMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
