package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.LOC_CLASSMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class LOC_CLASSTest {
	public LOC_CLASSTest(String projectPath) {
		AbstractMetric measurement = new LOC_CLASSMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
