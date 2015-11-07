package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.WMCMetricVisitor;

public class WMCTest {
public WMCTest(){
	AbstractMeasurement measurement = new WMCMetricVisitor();

	MetricUtil.startMetric(
			"e:/Qualitas Corpus/001-apache-ant-1.9.6-src/", 
			measurement);

}
}
