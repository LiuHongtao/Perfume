package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.LOC_CLASSMetricVisitor;

public class LOC_CLASSTest {
	public LOC_CLASSTest(String projectPath) {
		AbstractMetricVisitor measurement = new LOC_CLASSMetricVisitor();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}
}
