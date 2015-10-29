package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JdtAstUtilP {
	private ASTParser parser = ASTParser.newParser(AST.JLS4);// ·Ç³£Âý

	public CompilationUnit getCompilationUnit(String javaProjectPath,String javaFilePath)
			throws Exception {
	
		File file = new File(javaFilePath);
		
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(javaFilePath));

		byte[] input = new byte[bufferedInputStream.available()];
		bufferedInputStream.read(input);
		bufferedInputStream.close();
		parser.setSource(new String(input).toCharArray());

		Map<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_6);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
				JavaCore.VERSION_1_6);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);

		String unitName = file.getName();
		parser.setUnitName(unitName);

		String[] sources = {javaProjectPath+"//src"};
		String[] classPaths = { javaProjectPath+"//bin","E:\\Workspaces\\OurMetrics\\liborg.eclipse.jdt.core_3.9.1.v20130905-0837.jar"  };
		parser.setBindingsRecovery(true);
		parser.setResolveBindings(true);
		parser.setEnvironment(classPaths, sources, new String[] { "UTF-8" },
				true);
		
		parser.setCompilerOptions(options);
		parser.setStatementsRecovery(true);

		CompilationUnit result = (CompilationUnit) parser.createAST(null);

		IProblem[] problems = result.getProblems();

		for (IProblem problem : problems) {
			System.out.println(problem.toString());
		}

		if (result.getAST().hasBindingsRecovery()) {
			System.out.println("Binding activated.");
		}
		
		
		return result;
	}
}
