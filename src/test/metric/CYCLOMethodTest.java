package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.CYCLOMethodMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class CYCLOMethodTest {


	public CYCLOMethodTest(String projectPath) {
		AbstractMetric measurement = new CYCLOMethodMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
