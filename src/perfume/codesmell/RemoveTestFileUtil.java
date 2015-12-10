package perfume.codesmell;

import java.io.IOException;
import java.util.ArrayList;

import perfume.util.FileUtil;
import perfume.util.LogUtil;

public class RemoveTestFileUtil {
	public void removeAllTestFile(String dirName) {
		ArrayList<String> projectNameList = FileUtil.getAllProjectName(dirName);
		for (String projectName: projectNameList) {
			try {
				LogUtil.printToLog("#####" + projectName + "\r\n\r\n");
			
				ArrayList<String> paths = FileUtil.getAllTestJavaFilePath(dirName + projectName);
				
				for (String filePath: paths) {
					LogUtil.printToLog(filePath + "\r\n\r\n");
	//				FileUtil.delFolder(filePath);
				}
				
				LogUtil.printToLog(paths.size() + "\r\n\r\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
