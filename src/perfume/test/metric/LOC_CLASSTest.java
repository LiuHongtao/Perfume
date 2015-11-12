package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.LOC_CLASSMetric;
import perfume.metric.MetricUtil;

public class LOC_CLASSTest {
	public LOC_CLASSTest(String projectPath) {
		AbstractMetric measurement = new LOC_CLASSMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}
}
