package test.metric;

import main.resources.perfume.metric.AbstractMetric;

import main.resources.perfume.metric.LOC_METHODMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.util.LogUtil;

public class LOC_METHODTest {
	public LOC_METHODTest(String projectPath) {
		AbstractMetric measurement = new LOC_METHODMetric();
		MetricUtil.startMetric(projectPath, measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}

}
