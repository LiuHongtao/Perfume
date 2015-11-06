package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.LOC_CLASSMetricVisitor;


public class LOC_CLASSTest {
	public LOC_CLASSTest() {
	AbstractMeasurement measurement = new LOC_CLASSMetricVisitor();

	MetricUtil.startMetric(
			"e:/Qualitas Corpus/001-apache-ant-1.9.6-src/", 
			measurement);

}
}
