package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.TCCMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class TCCTest {
	
	public TCCTest(String projectPath) {
		AbstractMetric measurement = new TCCMetric();
		MetricUtil.startMetric(projectPath, measurement);
		LogUtil.print(measurement.getMetricResult());
		
	}
}
