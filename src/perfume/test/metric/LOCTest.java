package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class LOCTest {
	public LOCTest(String projectPath){
		AbstractMeasurement measurement = new LOCMetricVisitor();

		MetricUtil.startMetric(
				projectPath, 
				measurement);
	}

}

