package perfume.metric.visitor;


import java.util.HashMap;

import org.eclipse.jdt.core.dom.SimpleType;

import perfume.metric.AbstractMetricVisitor;

public class CBOClassVisitor extends AbstractMetricVisitor {
	private HashMap<String, Long> CBOOfClassMap ;
	
	
	public CBOClassVisitor(HashMap CBOMap){
		this.CBOOfClassMap = CBOMap;
	}
	public boolean visit(SimpleType node){
		
		String className = node.getName().toString();
		if (CBOOfClassMap.get(className) == null) {
			CBOOfClassMap.put(className, 1l);
		}
		CBOOfClassMap.put(className, CBOOfClassMap.get(className)+1l);
		return false;
		
	}
	@Override
	public HashMap<String, Long> getMetricResult() {
		 
		return CBOOfClassMap;
	}

}
