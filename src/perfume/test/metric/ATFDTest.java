package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOAMMetric;

public class ATFDTest {

	public ATFDTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAMMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}	
}
