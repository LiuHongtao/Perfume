package perfume.codesmell;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.LOC_METHODMetric;
import perfume.metric.MAXNESTINGMetric;
import perfume.metric.MetricUtil;
import perfume.util.CSVUtil;

public class LongMethod extends AbstractCodeSmell{
	
	public LongMethod(String projectDir, String projectName) {
		AbstractMetricVisitor measurement_1 = new LOC_METHODMetric();
		AbstractMetricVisitor measurement_2 = new MAXNESTINGMetric();
		
		MetricUtil.startMetric(projectDir + projectName, measurement_1, measurement_2);
		
		CSVUtil.outputToCSV(
				getFileName(getCodeSmellName(), projectName), 
				measurement_1, measurement_2);
	}
	
	@Override
	protected String getCodeSmellName() {
		return "Long-Method-";
	}
}
