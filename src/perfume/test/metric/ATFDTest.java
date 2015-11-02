package perfume.test.metric;

import perfume.metric.visitor.ATFDMetricVisitor;
import perfume.metric.visitor.MetricUtil;

public class ATFDTest {

	public ATFDTest() {
		ATFDMetricVisitor visitor = new ATFDMetricVisitor();
		MetricUtil.startMetric(
				"D:/Qualitas Corpus/112-xmojosrc_5.0.0", 
				visitor);
	}
	
}
