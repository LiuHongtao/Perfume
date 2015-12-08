package perfume.codesmell;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.CYCLOMethodMetric;
import perfume.metric.LOC_METHODMetric;
import perfume.metric.MAXNESTINGMetric;
import perfume.metric.MetricUtil;
import perfume.util.CSVUtil;

public class LongMethod extends AbstractCodeSmell{

	@Override
	public void makeTrainingSet(String projectDir, String projectName) {
		AbstractMetricVisitor measurement_1 = new LOC_METHODMetric();
		AbstractMetricVisitor measurement_2 = new CYCLOMethodMetric();
		AbstractMetricVisitor measurement_3 = new MAXNESTINGMetric();
		
		MetricUtil.startMetric(
				projectDir + projectName, 
				measurement_1, measurement_2, measurement_3);
		
		CSVUtil.outputToCSV(
				RESULT_DIR + getCodeSmellName() + "\\", 
				projectName, 
				measurement_1, measurement_2, measurement_3);
	}
	
	@Override
	protected String getCodeSmellName() {
		return "long method";
	}
}
