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
			print(key + "\t" + val);
		}
	}
	
	static private File logFile = new File("D://log.txt");
	
	public static void printToLog(String msg) {
		try {
			FileOutputStream fs;
			fs = new FileOutputStream(logFile);
			PrintStream p = new PrintStream(fs);
			
			p.println(msg);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void printToLog(int msg) {
		printToLog(msg + "");
	}
}
