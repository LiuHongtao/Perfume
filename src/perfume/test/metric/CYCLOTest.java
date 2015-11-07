package perfume.test.metric;

import perfume.metric.AbstractMeasurement;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.CYCLOMetricVisitor;

public class CYCLOTest {
public CYCLOTest(String projectPath){
	AbstractMeasurement measurement = new CYCLOMetricVisitor();

	MetricUtil.startMetric(
			projectPath, 
			measurement);

	
	
}
}
