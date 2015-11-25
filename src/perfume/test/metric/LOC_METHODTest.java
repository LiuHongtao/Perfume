package perfume.test.metric;

import perfume.metric.AbstractMetric;

import perfume.metric.LOC_METHODMetric;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;

public class LOC_METHODTest {
	public LOC_METHODTest(String projectPath) {
		AbstractMetric measurement = new LOC_METHODMetric();
		MetricUtil.startMetric(projectPath, measurement);
//		LogUtil.print(measurement.getMetricResult());
		LogUtil.print(measurement.getMetricResult().size());
	}

}
