package perfume.metric;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

import perfume.util.FileUtil;
import perfume.util.LogUtil;
import perfume.util.ast.JdtAstUtil;

public class MetricUtil {

	/**
	 * Only for AST
	 * @param projectPath
	 * @param measurements
	 */
	public static void startMetric(String projectPath, AbstractMetric... metrics) {
		LogUtil.print("measurement start");
		
		ArrayList<String> filePath = FileUtil.getAllJavaFilePath(projectPath);

		for (String path: filePath) {
			LogUtil.print(path);
			try {
				CompilationUnit compUnit = JdtAstUtil.getCompilationUnit(path);
				for (AbstractMetric metric: metrics) {					
					metric.beforeMetric(path, compUnit);
					compUnit.accept(metric);
					metric.afterMetric();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		LogUtil.print("measurement finish");
	}
	
}
