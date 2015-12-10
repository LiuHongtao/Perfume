package perfume.codesmell;

import java.io.IOException;
import java.util.ArrayList;

import perfume.util.FileUtil;
import perfume.util.LogUtil;

public class RemoveTestFileUtil {
	public void removeAllTestFile(String dirName) {
		ArrayList<String> projectNameList = FileUtil.getAllProjectName(dirName);
		for (String projectName: projectNameList) {
			LogUtil.print("#####" + projectName);
		
			ArrayList<String> paths = FileUtil.getAllTestJavaFilePath(dirName + projectName);
			
			for (String filePath: paths) {
				LogUtil.print(filePath);
				FileUtil.delFolder(filePath);
			}
			
			LogUtil.print(paths.size());
		}
	}
}
