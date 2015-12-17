package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.LCOMMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class LCOMTest {
	public LCOMTest(String projectPath) {
		AbstractMetric measurement = new LCOMMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
