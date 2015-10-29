package visitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import util.FileUtil;
import util.JdtAstUtil;
import analyzer.SumJavaCode;

public class FieldVisitorTest extends ASTVisitor{
	private int LineOfImports= 0;
	private String unitPath;
	private CompilationUnit unit;
	int noCommentCodeLine,commentLines,totalLines;

	public FieldVisitorTest(String dirPath) throws Exception{
		super();

		FileUtil util = new FileUtil();
		ArrayList<String> pathList = util.getAllJavaFilePath(dirPath);
		System.out.println(dirPath);
		SumJavaCode lc = new SumJavaCode();
		for (String path : pathList) {
			unitPath = path;
//			System.out.println(path);
			unit = JdtAstUtil.getCompilationUnit(unitPath);

			unit.accept(this);
			
	
		}


	}
	
	public boolean visit(TypeDeclaration node) {
		FieldDeclaration[] fd =  node.getFields();
		for (FieldDeclaration fdTemp : fd) {
			int md =fdTemp.getModifiers();
			Type tp = fdTemp.getType();
			List fg = fdTemp.fragments();
			for (Object fgTemp : fg){
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) fgTemp;
				System.out.println(md+"\t"+tp.toString() +"\t"+vdf.getName().toString());
			}
		}

		
		return false;
		
	}
}
