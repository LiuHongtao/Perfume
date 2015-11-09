package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class LOCTest {
	public LOCTest(String projectPath) {
		AbstractMetricVisitor measurement = new LOCMetricVisitor();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}

}

