package perfume.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LogUtil {
	
	public static void print(String msg) {
		System.out.println(msg);
	}
	
	public static void print(int msg) {
		print(msg + "");
	}
	
	public static void print(HashMap<String, Long> msg) {
		Iterator iter = msg.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			System.out.println(key + "\t" + val);
		}
	}
	
	private static final String LOG_NAME = "D:/log.txt";
	private static StringBuilder sBuilder = new StringBuilder();
	public static void printToFile(String msg) {
		sBuilder.append(msg);
	}
	
	public static void getLogFile() {
		try {
			File file = new File(LOG_NAME);
			FileOutputStream fs;
			fs = new FileOutputStream(file);
			PrintStream p = new PrintStream(fs);
			p.println(sBuilder.toString());
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("see file " + LOG_NAME);
	}
}
