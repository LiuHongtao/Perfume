package perfume.test.metric;

import perfume.metric.ATFDMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class ATFDTest {

	public ATFDTest(String projectPath) {
		AbstractMetricVisitor measurement = new ATFDMetric(projectPath);
		MetricUtil.startMetric(projectPath, measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}	
}
