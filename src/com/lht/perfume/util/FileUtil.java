package com.lht.perfume.util;

import java.io.File;
import java.util.ArrayList;

public class FileUtil {
	
	private static final String DIR_NOT_EXIST = "????? directory %s not exist";
	
	private static ArrayList<String> filePath;
	
	public static void showAllJavaFilePath(String dirName) {
		getAllJavaFilePath(dirName);
		
		for (String path: filePath) {
			System.out.println(path);
		}
	}
	
	public static ArrayList<String> getAllJavaFilePath(String dirName) {
		filePath = new ArrayList<String>();
		getJavaFile(dirName);
		return filePath;
	}
	
	private static void getJavaFile(String dirName){ 
    	File dir = new File(dirName);
    	
    	if (!dir.exists()) {
    		LogUtil.print(
    				String.format(
    						DIR_NOT_EXIST, dirName));    		
    		return;
    	}
    	
    	File[]  fileList = dir.listFiles();
    	
    	for (File file: fileList) {
    		String path = file.getAbsolutePath();
    		if (path.endsWith(".java")) {
    			filePath.add(file.getAbsolutePath());
            }
    		else if (file.isDirectory()){
    			getJavaFile(path);
    		}
    	}
    }
    
}
