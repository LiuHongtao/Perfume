package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.NOMMetricVisitor;

public class NOMTest {

	public NOMTest(String projectPath) {
		AbstractMeasurement measurement = new NOMMetricVisitor();
		MetricUtil.startMetric(
				  projectPath, 
				measurement);
	}	
}
