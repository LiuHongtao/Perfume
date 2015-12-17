package test.metric;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOMMetric;
import main.resources.perfume.util.LogUtil;

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
