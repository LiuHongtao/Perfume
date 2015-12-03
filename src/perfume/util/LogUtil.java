package perfume.util;

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
}
