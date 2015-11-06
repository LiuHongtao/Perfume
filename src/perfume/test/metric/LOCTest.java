package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class LOCTest {
	public LOCTest(){
		AbstractMeasurement measurement = new LOCMetricVisitor();

		MetricUtil.startMetric(
				"e:/Qualitas Corpus/001-apache-ant-1.9.6-src/", 
				measurement);
	}

}
