package perfume.test.metric;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.MetricUtil;
import perfume.metric.visitor.CYCLOMetricVisitor;

public class CYCLOTest {
public CYCLOTest(String projectPath){
	AbstractMetricVisitor measurement = new CYCLOMetricVisitor();
	MetricUtil.startMetric(
			projectPath, 
			measurement);

	
	
}
}