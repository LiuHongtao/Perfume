package main.resources.perfume.util;

public class StringUtil {
	
	public static String stringConnection(String... strings) {
		StringBuilder resultBuilder = new StringBuilder();
		
		for (String s: strings) {
			resultBuilder.append(s);
		}
		
		return resultBuilder.toString();
	}
}
