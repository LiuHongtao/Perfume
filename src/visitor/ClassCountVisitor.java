package visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import analyzer.LineOfCode;
import util.FileUtil;
import util.JdtAstUtil;

public class ClassCountVisitor extends ASTVisitor {

	private String unitPath;
	private String packageName = "";
	private String className = "";
	private CompilationUnit unit;
	private FileUtil util;

	private long totalParamCount = 0;
	private int fileCount = 0;
	private int methodNumber = 0;
	private int classCount = 0,interClassCount = 0;
	private int interfaceCout = 0;
	private int LineOfMethodSum = 0;
	private int max = 0;
	private String maxMethod = "";
	private boolean isVisited = false;

	public ClassCountVisitor() {

	}

	public ClassCountVisitor(String dirPath) throws Exception {
		super();

		util = new FileUtil();
		ArrayList<String> pathList = util.getAllJavaFilePath(dirPath);
		for (String path : pathList) {
			unitPath = path;
			unit = JdtAstUtil.getCompilationUnit(unitPath);

			unit.accept(this);
			fileCount++;
			isVisited = false;
		}
		getResult();
	}

	public void getResult() {
		float avg = (float) totalParamCount / methodNumber;
		// System.out.println("TotalClassCount: "+classCount);
		// System.out.println("Method Number: " + methodNumber);
		//
		// System.out.println("Average Params Length: " + avg);
		// System.out.println("Maximal Params Length: " + max);
		// System.out.println("Maximal Params Length Method: " + maxMethod);
		// System.out.println("LineOfMethodSum: "+getLineOfMethodSum());
		System.out.println(classCount + "\t" + interfaceCout + "\t" +interClassCount+"\t"+ fileCount
				+ "\t" + methodNumber + "\t" + avg + "\t" + max + "\t"
				+ packageName);
		// System.out.println("LOC: "+ LOCsum);

	}

	@Override
	public boolean visit(MethodDeclaration node) {
		int paramLength = node.parameters().size();

		totalParamCount += paramLength;
		methodNumber++;

		if (paramLength > max) {
			max = paramLength;
			// maxMethod = unitPath ;

			// + "\nMethodName: " + node.getName();
			// +"\nLine Number: " +
			// unit.getLineNumber(node.getStartPosition()+node.getLength());
			if (unit.getPackage() != null) {
				PackageDeclaration pkg = unit.getPackage();
				Name pn = pkg.getName();
				packageName = pn.toString() + "\t" + className;

			}

		}

		return true;
	}

	public boolean visit(TypeDeclaration node) {
		if (!isVisited) {
			if (node.isInterface()) {
				interfaceCout++;
			} else
				classCount++;
			isVisited=true;
		}else{
			interClassCount++;
		}
		// String s = new HashSet() {
		// {
		// add(1);
		// add(2);
		// }
		//
		// public String mys() {
		// return "halo world";
		// }
		//
		// }.mys();
		// System.out.println(s);
		className = node.getName().toString();
		return true;

	}

	public int getLineOfMethodSum() {
		return LineOfMethodSum;
	};

}
