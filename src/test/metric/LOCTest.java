package test.metric;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.LOCMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class LOCTest {
	public LOCTest(String projectPath) {
		AbstractMetricVisitor measurement = new LOCMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}

}
