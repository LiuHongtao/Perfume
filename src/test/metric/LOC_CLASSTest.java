package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.LOC_CLASSMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class LOC_CLASSTest {
	public LOC_CLASSTest(String projectPath) {
		AbstractMetric measurement = new LOC_CLASSMetric();
		MetricUtil.startMetric(
				projectPath, 
				measurement);
		//LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}
}
