package perfume.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;

import perfume.metric.AbstractMetric;

public class CSVUtil {
	
	public static void outputToCSV(String fileName, AbstractMetric... metrics) {
		LogUtil.print("output to CSV start");
		
		int times = metrics.length;
		if (times < 2) {
			return;
		}
		
		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(metrics[0].getMetricName());
		for (int i = 1; i < times; i++) {
			sBuilder.append(',');
			sBuilder.append(metrics[i].getMetricName());
		}
		sBuilder.append('\n');
		
		Iterator iter = metrics[0].getMetricResult().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			sBuilder.append(val);
			
			for (int i = 1; i < times; i++) {
				sBuilder.append(',');
				sBuilder.append(metrics[i].getMetricResult().get(key));
			}
			
			sBuilder.append('\n');
		}
		
		try {
			File file = new File(fileName);
			FileOutputStream fs;
			fs = new FileOutputStream(file);
			PrintStream p = new PrintStream(fs);
			p.println(sBuilder.toString());
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		LogUtil.print("output to CSV finish");
		LogUtil.print("see file " + fileName);
	}
}
