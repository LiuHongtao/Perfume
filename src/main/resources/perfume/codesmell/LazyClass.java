package main.resources.perfume.codesmell;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.CBOMetric;
import main.resources.perfume.metric.CYCLOMetric;
import main.resources.perfume.metric.LOC_CLASSMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOAMetric;
import main.resources.perfume.metric.NOMMetric;
import main.resources.perfume.util.CSVUtil;
import visitor.LazyClassDetector;

public class LazyClass extends AbstractCodeSmell {

	@Override
	public void makeTrainingSet(String projectDir, String projectName) {
		LazyClassDetector detector = new LazyClassDetector(projectDir + projectName);
		
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
				projectName, detector.getIsLazyClassMap(),
				measurement_1, measurement_2, measurement_3, 
				measurement_4, measurement_5);
	}

	@Override
	protected String getCodeSmellName() {
		return "lazy class";
	}

}
