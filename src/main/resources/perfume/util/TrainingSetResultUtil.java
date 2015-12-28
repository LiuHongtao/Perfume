package main.resources.perfume.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.resources.perfume.metric.AbstractMetric;

public class TrainingSetResultUtil {
	
	public static void outputToCSV(
			String dirName, 
			String fileName, 
			HashMap<String, Boolean> result, 
			AbstractMetric... metrics) {
		LogUtil.print("output to result start");
		
		int times = metrics.length;
		if (times < 2) {
			return;
		}
		
		//make a file
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		fileName = dirName + fileName + ".tsv";
		File file = new File(fileName);		
		
		try {
			FileOutputStream fs;
			fs = new FileOutputStream(file);
			PrintStream p = new PrintStream(fs);
			
			for (int i = 0; i < times; i++) {
				p.print(metrics[i].getMetricName());
				p.print('\t');
			}
			p.print("test result");
			p.print("\r\n");
			
			Iterator iter = metrics[0].getMetricResult().entrySet().iterator();
			StringBuilder lineBuilder;
			while (iter.hasNext()) {
				lineBuilder = new StringBuilder();
				
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				
				lineBuilder.append(val);
				
				for (int i = 1; i < times; i++) {
					lineBuilder.append('\t');
					lineBuilder.append(metrics[i].getMetricResult().get(key));
				}
				
				if (lineBuilder.toString().equals("-2\t-2\t-2\t-2\t-2")) {
					continue;
				}
				
				lineBuilder.append('\t');
				lineBuilder.append(result.containsKey(key));
				lineBuilder.append("\r\n");
				
				p.print(lineBuilder.toString());
			}	
			p.close();
			
			LogUtil.print("output to result finish");
			LogUtil.print("see file " + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
	
	public static void collectToCSV(String dirPath, String filePath) throws IOException {
		File result = new File(filePath);
		FileWriter fw;
		BufferedWriter bw;
		fw = new FileWriter(result);
		bw = new BufferedWriter(fw);
		
        File dir = new File(dirPath);
		File[] fileList = dir.listFiles();
		String myreadline;
		for (File file: fileList) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); 
	        while (br.ready()) {
	            myreadline = br.readLine();
	            if (myreadline.startsWith("NOM")) {
	            	continue;
	            }
	            bw.write(myreadline); 
	            bw.newLine();
	        }
	        
	        br.close();
	        fr.close();
		}
		
		bw.flush(); 
        bw.close();
        fw.close();
	}
}
