package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOVMetric;
import perfume.util.LogUtil;

public class NOVTest {

	public NOVTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOVMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}	
}
