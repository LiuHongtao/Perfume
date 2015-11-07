package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.LOC_CLASSMetricVisitor;


public class LOC_CLASSTest {
	public LOC_CLASSTest(String projectPath) {
	AbstractMeasurement measurement = new LOC_CLASSMetricVisitor();
	MetricUtil.startMetric(
			projectPath, 
			measurement);
	}
}
