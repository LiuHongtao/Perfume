package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.CYCLOMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class CYCLOTest {
	public CYCLOTest(String projectPath) {
		AbstractMetric measurement = new CYCLOMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
