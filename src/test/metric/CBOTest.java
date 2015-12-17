package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;
import main.resources.perfume.metric.CBOMetric;

public class CBOTest {
	public CBOTest(String projectPath) {
		AbstractMetric measurement = new CBOMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
