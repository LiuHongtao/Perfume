package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.NOPAMetricVisitor;

public class NOPATest {
	
	public NOPATest(String projectPath) {
		AbstractMeasurement measurement = new NOPAMetricVisitor();
//		MetricUtil.startMetric(
//				"D:/Qualitas Corpus/112-xmojosrc_5.0.0", 
//				measurement);
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
//		MetricUtil.startMetric(
//				"C:/Users/lht/Desktop/usecase", 
//				measurement);
	}
}
