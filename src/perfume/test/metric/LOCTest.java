package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.LOCMetric;
import perfume.metric.MetricUtil;

public class LOCTest {
	public LOCTest(String projectPath) {
		AbstractMetricVisitor measurement = new LOCMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}

}

