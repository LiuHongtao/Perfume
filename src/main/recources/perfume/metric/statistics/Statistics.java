package main.recources.perfume.metric.statistics;

import java.io.IOException;
import java.util.ArrayList;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.*;
import main.resources.perfume.util.FileUtil;

public class Statistics {
	public void makeStatistics(String dirName, StatisticsMetric smetric) {
		ArrayList<String> projectNameList = FileUtil.getAllProjectName(dirName);
		
		for (String projectName : projectNameList) {
			AbstractMetricVisitor measurement = new LOC_CLASSMetric();
			smetric.makeStatisticsSet(dirName, projectName, measurement);

		}
		try {
			smetric.getResult("LOC_CLASSMetric");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
