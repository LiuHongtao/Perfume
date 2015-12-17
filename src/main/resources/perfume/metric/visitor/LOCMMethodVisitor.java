package main.resources.perfume.metric.visitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

public class LOCMMethodVisitor extends ASTVisitor {
	private List<String> vdfs;
	private List<String> instanceVarMap = new  ArrayList<String> ();
	
	public LOCMMethodVisitor(List<String> vdfs) {
		this.vdfs = vdfs;
	}
	
	public boolean visit(SimpleName node){
		String varName=node.toString();
		if(vdfs.contains(varName)){
			instanceVarMap.add(varName);
		}
		
		return false;
	}
	
	public List<String> getVisitorResult() {
		return instanceVarMap;
	}
}
