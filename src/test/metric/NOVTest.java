package test.metric;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOVMetric;
import main.resources.perfume.util.LogUtil;

public class NOVTest {

	public NOVTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOVMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}	
}
