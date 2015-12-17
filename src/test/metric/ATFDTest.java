package test.metric;

import main.resources.perfume.metric.ATFDMetric;
import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class ATFDTest {

	public ATFDTest(String projectPath) {
		AbstractMetricVisitor measurement = new ATFDMetric(projectPath);
		MetricUtil.startMetric(projectPath, measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}	
}
