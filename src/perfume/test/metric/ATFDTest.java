package perfume.test.metric;

import perfume.metric.ATFDMetric;
import perfume.metric.AbstractMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOAMMetric;

public class ATFDTest {

	public ATFDTest(String projectPath) {
		AbstractMetric measurement = new ATFDMetric(projectPath);
	}	
}
