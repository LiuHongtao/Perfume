package main.recources.perfume.metric.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.resources.perfume.metric.AbstractMetric;
import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.CBOMetric;
import main.resources.perfume.metric.CYCLOMetric;
import main.resources.perfume.metric.LOC_CLASSMetric;
import main.resources.perfume.metric.MetricUtil;
import main.resources.perfume.metric.NOAMetric;
import main.resources.perfume.metric.NOMMetric;
import main.resources.perfume.util.FileUtil;
import main.resources.perfume.util.LogUtil;

public class StatisticsMetric {
	private Map<Long, Integer> resultMap = new LinkedHashMap<Long, Integer>();

	public void makeStatisticsSet(String projectDir, String projectName) {

		AbstractMetricVisitor measurement_1 = new NOMMetric();

		MetricUtil.startMetric(projectDir + projectName, measurement_1);

		HashMap<String, Long> mResult = measurement_1.getMetricResult();
		
		Iterator<Entry<String, Long>> iter = mResult.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Long key = (Long) entry.getValue();
			
			if (resultMap.get(key) == null) {
				resultMap.put(key,0);
			}
			int tmp = resultMap.get(key).intValue();
			resultMap.put(key, tmp+1);
		}
		
		
	}
	public void getResult(){
		LogUtil.print(resultMap);
	}

}
