package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.PARMetric;
import main.resources.perfume.util.LogUtil;

public class PARTest {
	
	public PARTest(String projectPath) {
		AbstractMetric measurement = new PARMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
