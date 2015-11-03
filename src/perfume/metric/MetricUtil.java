package perfume.metric;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

import perfume.metric.visitor.AbstractMetricVisitor;
import perfume.util.FileUtil;
import perfume.util.LogUtil;
import perfume.util.ast.JdtAstUtil;

public class MetricUtil {

	/**
	 * Only for AST
	 * @param projectPath
	 * @param measurements
	 */
	public static void startMetric(String projectPath, AbstractMeasurement... measurements) {
		ArrayList<String> filePath = FileUtil.getAllJavaFilePath(projectPath);

		for (String path: filePath) {
			try {
				CompilationUnit compUnit = JdtAstUtil.getCompilationUnit(path);
				for (AbstractMeasurement measurement: measurements) {
					measurement.beforeMeasurement();
					//TODO
//					LogUtil.print(compUnit.getPackage().getName().toString());
					compUnit.accept(measurement);
					measurement.afterMeasurement();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
