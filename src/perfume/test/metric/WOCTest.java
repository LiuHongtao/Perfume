package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.MetricUtil;
import perfume.metric.WOCMetric;
import perfume.util.LogUtil;

public class WOCTest {
public WOCTest(String projectPath) {
		AbstractMetric measurement = new WOCMetric();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}

