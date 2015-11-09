package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.NOPAMetricVisitor;
import perfume.util.LogUtil;

public class NOPATest {
	
	public NOPATest(String projectPath) {
		AbstractMeasurement measurement = new NOPAMetricVisitor();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
