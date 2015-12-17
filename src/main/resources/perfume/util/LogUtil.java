package main.resources.perfume.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
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
	
	static private File logFile = new File("E://log.md");
	
	public static void printToLog(String msg) throws IOException {
		Writer txtWriter = new FileWriter(logFile, true);
		txtWriter.write(msg);
		txtWriter.flush();
	}
	
	public static void printToLog(int msg) throws IOException {
		printToLog(msg + "");
	}
}
