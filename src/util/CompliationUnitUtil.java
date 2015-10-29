package util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;

import visitor.MRCVisitor;

public class CompliationUnitUtil {
	private String unitPath;
	private CompilationUnit unit;
	
	public CompliationUnitUtil(String dirPath) throws Exception{
		FileUtil util = new FileUtil();
		ArrayList<String> pathList = util.getAllJavaFilePath(dirPath);

		for (String path : pathList) {
			unitPath = path;
			JdtAstUtilP jaup = new JdtAstUtilP();
			unit = jaup.getCompilationUnit(dirPath,unitPath);
			unit.accept(new MRCVisitor());
		}
		
		
	}
}
