package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOAMMetric;
import perfume.util.LogUtil;

public class NOAMTest {

	public NOAMTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOAMMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
