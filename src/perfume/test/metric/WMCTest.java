package perfume.test.metric;

import perfume.metric.AbstractMetric ;
import perfume.metric.MetricUtil;
import perfume.metric.WMCMetric;
import perfume.util.LogUtil;

public class WMCTest {
	public WMCTest(String projectPath) {
		AbstractMetric measurement = new WMCMetric();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
