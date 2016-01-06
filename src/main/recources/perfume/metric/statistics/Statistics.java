package main.recources.perfume.metric.statistics;

import java.util.ArrayList;

import main.resources.perfume.util.FileUtil;

public class Statistics {
	public void makeStatistics(String dirName, StatisticsMetric smetric) {
		ArrayList<String> projectNameList = FileUtil.getAllProjectName(dirName);
		for (String projectName : projectNameList) {
			smetric.makeStatisticsSet(dirName, projectName);

		}
		smetric.getResult();
	}
}
