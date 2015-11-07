package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.AFTD.ATFDMetricVisitor;

public class ATFDTest {

	public ATFDTest(String projectPath) {
		AbstractMeasurement measurement = new ATFDMetricVisitor();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}	
}
