package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.MetricUtil;
import perfume.metric.LOCPROBMetric;
import perfume.util.LogUtil;

public class LOCPROBTest {
 public LOCPROBTest(String projectPath) {
		AbstractMetric measurement = new LOCPROBMetric();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
