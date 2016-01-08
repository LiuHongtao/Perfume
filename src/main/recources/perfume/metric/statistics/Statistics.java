package main.recources.perfume.metric.statistics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.resources.perfume.metric.AbstractMetricVisitor;
import main.resources.perfume.metric.*;
import main.resources.perfume.util.FileUtil;
import main.resources.perfume.util.LogUtil;

public class Statistics {
	public void makeStatistics(String dirName, StatisticsMetric smetric) {
		long timeMillis = System.currentTimeMillis();
		ArrayList<String> projectNameList = FileUtil.getAllProjectName(dirName);
		
		for (String projectName : projectNameList) {
			AbstractMetricVisitor measurement = new ATFDMetric(projectName);
			smetric.makeStatisticsSet(dirName, projectName, measurement);

		}
		try {
			smetric.getResult("ATFDMetric");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeMillis = System.currentTimeMillis() -timeMillis;
		Date nowTime = new Date(timeMillis);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		LogUtil.print("total Time:"+retStrFormatNowDate);
	}
}
