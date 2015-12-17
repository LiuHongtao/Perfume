package main.resources.perfume.metric.visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import main.resources.perfume.util.FileUtil;
import main.resources.perfume.util.ast.JdtAstUtil;

public class VisitorUtil {

	/**
	 * Only for Visitor
	 * @param projectPath
	 * @param visitors
	 */
	public static void startVisit(String projectPath, ASTVisitor... visitors) {
		ArrayList<String> filePath = FileUtil.getAllJavaFilePath(projectPath);

		for (String path: filePath) {
			try {
				CompilationUnit compUnit = JdtAstUtil.getCompilationUnit(path);
				for (ASTVisitor visitor: visitors) {					
					compUnit.accept(visitor);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
