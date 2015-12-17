package test.metric;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.WOCMetric;
import main.resources.perfume.util.LogUtil;

public class WOCTest {
public WOCTest(String projectPath) {
		AbstractMetric measurement = new WOCMetric();	
		MetricUtil.startMetric(
				 projectPath, 
				measurement);
		LogUtil.print(measurement.getMetricResult());
	}
}

