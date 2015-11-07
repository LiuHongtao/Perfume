package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.WMCMetricVisitor;

public class WMCTest {
public WMCTest(String projectPath){
	AbstractMeasurement measurement = new WMCMetricVisitor();

	MetricUtil.startMetric(
			 projectPath, 
			measurement);

}
}
