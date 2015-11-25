package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.NOMMetric;
import perfume.util.LogUtil;

public class NOMTest {

	public NOMTest(String projectPath) {
		AbstractMetricVisitor measurement = new NOMMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);	
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}	
}
