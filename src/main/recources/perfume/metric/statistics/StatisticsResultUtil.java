package main.recources.perfume.metric.statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

public class StatisticsResultUtil {
	static private File logFile = new File("E://log2.md");

	public static String MapToStr(Map msg) {
		Iterator iter = msg.entrySet().iterator();
		String msgStr = "";
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			msgStr += key + "\t" + val + "\n";
			
		}
		return msgStr;
	}

	public static void printToLog(Map msg) throws IOException {
		Writer txtWriter = new FileWriter(logFile, true);

		txtWriter.write(MapToStr(msg));
		txtWriter.flush();
	}

}
