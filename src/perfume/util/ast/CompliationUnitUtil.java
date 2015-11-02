package perfume.util.ast;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

import perfume.util.FileUtil;
import visitor.MRCVisitor;

public class CompliationUnitUtil {
	private String unitPath;
	private CompilationUnit unit;
	
	public CompliationUnitUtil(String dirPath) throws Exception{
		ArrayList<String> pathList = FileUtil.getAllJavaFilePath(dirPath);

		for (String path : pathList) {
			unitPath = path;
			JdtAstUtilP jaup = new JdtAstUtilP();
			unit = jaup.getCompilationUnit(dirPath,unitPath);
			unit.accept(new MRCVisitor());
		}
		
		
	}
}
