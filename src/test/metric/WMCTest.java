package test.metric;

import main.resources.perfume.metric.AbstractMetric ;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.WMCMetric;
import main.resources.perfume.util.LogUtil;

public class WMCTest {
	public WMCTest(String projectPath) {
		AbstractMetric measurement = new WMCMetric();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}
