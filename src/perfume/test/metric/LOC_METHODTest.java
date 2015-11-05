package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.*;

public class LOC_METHODTest {
public LOC_METHODTest(){
	AbstractMeasurement measurement = new LOC_METHODMetricVisitor();

	MetricUtil.startMetric(
			"D:/Qualitas Corpus/001-apache-ant-1.9.6-src/", 
			measurement);
}
}
