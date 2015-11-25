package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOPMetric;
import perfume.util.LogUtil;

public class NOPTest {
	
	public NOPTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOPMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
