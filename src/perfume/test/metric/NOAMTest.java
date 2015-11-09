package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.NOAMetricVisitor;

public class NOAMTest {

	public NOAMTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAMetricVisitor();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}
}
