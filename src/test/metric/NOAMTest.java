package test.metric;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOAMMetric;
import main.resources.perfume.util.LogUtil;

public class NOAMTest {

	public NOAMTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAMMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
