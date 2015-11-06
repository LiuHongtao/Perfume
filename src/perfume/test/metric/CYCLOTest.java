package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.CYCLOMetricVisitor;

public class CYCLOTest {
public CYCLOTest(){
	AbstractMeasurement measurement = new CYCLOMetricVisitor();

	MetricUtil.startMetric(
			"e:/Qualitas Corpus/001-apache-ant-1.9.6-src/", 
			measurement);

	
	
}
}
