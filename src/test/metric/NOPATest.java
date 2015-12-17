package test.metric;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOPAMetric;
import main.resources.perfume.util.LogUtil;

public class NOPATest {
	
	public NOPATest(String projectPath) {
		AbstractMetricVisitor measurement = new NOPAMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
