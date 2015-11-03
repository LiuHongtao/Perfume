package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.AFTD.ATFDMetricVisitor;

public class ATFDTest {

	public ATFDTest() {
		AbstractMeasurement measurement = new ATFDMetricVisitor();
		MetricUtil.startMetric(
				"D:/Qualitas Corpus/112-xmojosrc_5.0.0", 
				measurement);
	}	
}
