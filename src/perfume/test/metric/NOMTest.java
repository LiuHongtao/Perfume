package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.NOMMetricVisitor;

public class NOMTest {

	public NOMTest() {
		AbstractMeasurement measurement = new NOMMetricVisitor();
		MetricUtil.startMetric(
				"D:/Qualitas Corpus/112-xmojosrc_5.0.0", 
				measurement);
	}	
}
