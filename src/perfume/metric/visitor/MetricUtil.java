package perfume.metric.visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

import perfume.util.FileUtil;
import perfume.util.ast.JdtAstUtil;

public class MetricUtil {

	public static void startMetric(String projectPath, AbstractMetricVisitor... visitors) {
		ArrayList<String> filePath = FileUtil.getAllJavaFilePath(projectPath);

		for (String path: filePath) {
			try {
				CompilationUnit compUnit = JdtAstUtil.getCompilationUnit(path);
				for (AbstractMetricVisitor visitor: visitors) {
					visitor.beforeMeasurement();
					compUnit.accept(visitor);
					visitor.afterMeasurement();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
