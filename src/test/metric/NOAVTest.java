package test.metric;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOAVMetric;
import main.resources.perfume.util.LogUtil;

public class NOAVTest {

	public NOAVTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAVMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
