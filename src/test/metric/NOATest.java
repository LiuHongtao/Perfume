package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOAMetric;
import main.resources.perfume.util.LogUtil;

public class NOATest {
	public NOATest(String projectPath) {
		AbstractMetric measurement = new NOAMetric();
		MetricUtil.startMetric(projectPath, measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
