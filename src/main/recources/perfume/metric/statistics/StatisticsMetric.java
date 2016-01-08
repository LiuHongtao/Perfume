package main.recources.perfume.metric.statistics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

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
	private Map<String, AtomicInteger> resultMap = new LinkedHashMap<String, AtomicInteger>();

	public void makeStatisticsSet(String projectDir, String projectName, AbstractMetricVisitor measurement) {
		long timeMillis = System.currentTimeMillis();
		MetricUtil.startMetric(projectDir + projectName, measurement);

		HashMap<String, Long> mResult = measurement.getMetricResult();

		Iterator<Entry<String, Long>> iter = mResult.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Long key = (Long) entry.getValue();
			String index = key.intValue() + "";

			if (resultMap.get(index) == null) {
				resultMap.put(index, new AtomicInteger());
			}
			resultMap.get(index).incrementAndGet();

		}
		timeMillis = System.currentTimeMillis() -timeMillis;
		Date nowTime = new Date(timeMillis);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		LogUtil.print(retStrFormatNowDate+"\n");

	}

	public void getResult(String metricName) throws IOException {
		StatisticsResultUtil.printToLog(resultMap, metricName);
	}

}
