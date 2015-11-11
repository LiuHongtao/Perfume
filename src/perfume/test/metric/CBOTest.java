package perfume.test.metric;

import perfume.metric.AbstractMetric;
import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.util.LogUtil;
import perfume.metric.CBOMetric;

public class CBOTest {
	public CBOTest
		(String projectPath) {
			AbstractMetricVisitor measurement = new CBOMetric();
			MetricUtil.startMetric(
					projectPath, 
					measurement);
			LogUtil.print(measurement.getMetricResult());	
	}	
	}


