package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.CYCLOMetric;
import perfume.metric.MetricUtil;

public class CYCLOTest {
public CYCLOTest(String projectPath){
	AbstractMetricVisitor measurement = new CYCLOMetric();
	MetricUtil.startMetric(
			projectPath, 
			measurement);

	
	
}
}
