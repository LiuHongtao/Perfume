package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.TCCMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class TCCTest {
	
	public TCCTest(String projectPath) {
		AbstractMetric measurement = new TCCMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		
	}
}
