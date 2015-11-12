package perfume.metric.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.SimpleName;

import perfume.metric.AbstractMetricVisitor;

public class LOCMMethodVisitor extends AbstractMetricVisitor {
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
	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getVisitorResult() {
		//System.out.println(instanceVarMap);
		return instanceVarMap;
	}

}
