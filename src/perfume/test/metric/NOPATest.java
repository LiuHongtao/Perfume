package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.NOPAMetricVisitor;

public class NOPATest {
	
	public NOPATest() {
		AbstractMeasurement measurement = new NOPAMetricVisitor();
//		MetricUtil.startMetric(
//				"D:/Qualitas Corpus/112-xmojosrc_5.0.0", 
//				measurement);
		MetricUtil.startMetric(
				"D:/Qualitas Corpus/001-apache-ant-1.9.6-src/", 
				measurement);
//		MetricUtil.startMetric(
//				"C:/Users/lht/Desktop/usecase", 
//				measurement);
	}
}
