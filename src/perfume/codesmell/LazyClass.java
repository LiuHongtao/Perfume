package perfume.codesmell;

import perfume.metric.AbstractMetricVisitor;
import perfume.metric.CBOMetric;
import perfume.metric.CYCLOMetric;
import perfume.metric.LOCMetric;
import perfume.metric.LOC_CLASSMetric;
import perfume.metric.MetricUtil;
import perfume.metric.NOAMetric;
import perfume.metric.NOMMetric;
import perfume.util.CSVUtil;

public class LazyClass extends AbstractCodeSmell {

	@Override
	public void makeTrainingSet(String projectDir, String projectName) {
		AbstractMetricVisitor measurement_1 = new NOMMetric();
		AbstractMetricVisitor measurement_2 = new CYCLOMetric();
		AbstractMetricVisitor measurement_3 = new LOC_CLASSMetric();
		AbstractMetricVisitor measurement_4 = new CBOMetric();
		AbstractMetricVisitor measurement_5 = new NOAMetric();
		
		MetricUtil.startMetric(
				projectDir + projectName, 
				measurement_1, measurement_2, measurement_3,
				measurement_4, measurement_5);
		
		CSVUtil.outputToCSV(
				RESULT_DIR + getCodeSmellName() + "\\", 
				projectName, 
				measurement_1, measurement_2, measurement_3, 
				measurement_4, measurement_5);
	}

	@Override
	protected String getCodeSmellName() {
		return "lazy class";
	}

}
