package main.resources.perfume.metric.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import main.resources.perfume.util.LogUtil;

public class ProjectInfoVisitor extends ASTVisitor {
	
	private int mCompileUnit = 0;
	
	private int mClass = 0;
	private int mAbstractClass = 0;
	private int mInterface = 0;
	
	private int mMethod = 0;
	private int mAbstractMethod = 0;
	
	@Override
	public boolean visit(CompilationUnit node) {
		mCompileUnit++;
		return super.visit(node);
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		mClass++;
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		mMethod++;
		return super.visit(node);
	}
	
	public void print() {
		LogUtil.print("Compile Unit:\t" + mCompileUnit);
		LogUtil.print("Class:\t" + mClass);
		LogUtil.print("Method:\t" + mMethod);
	}
}
