package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOC2Metric;
import main.resources.perfume.util.LogUtil;

public class NOC2Test {
	public NOC2Test(String projectPath) {
		AbstractMetric measurement = new NOC2Metric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
