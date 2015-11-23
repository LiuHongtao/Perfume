package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MAXNESTINGMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class MAXNESTINGTest {

	public MAXNESTINGTest(String projectPath) {
		AbstractMetricVisitor measurement = new MAXNESTINGMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}

}
