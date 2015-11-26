package perfume.metric.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleType;

import perfume.metric.AbstractMetricVisitor;

public class CBOClassVisitor extends AbstractMetricVisitor {
	private HashMap<String, Long> CBOOfClassMap;
	private List<String> exsit = new ArrayList<String>();
	private String currentClassName;

	public CBOClassVisitor(HashMap CBOOfClassMap, String currentClassName) {
		this.CBOOfClassMap = CBOOfClassMap;
		this.currentClassName = currentClassName;
	}

	public boolean visit(QualifiedName node) {

		String className = node.getQualifier().getFullyQualifiedName();
		addToMap(className);
		return false;
	}

	public boolean visit(SimpleType node) {

		String className = node.getName().toString();
		addToMap(className);

		return false;

	}
	
	public boolean visit(PrimitiveType node){
		String className = node.getPrimitiveTypeCode().toString();
		addToMap(className);

		return false;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
//		if(currentClassName.equalsIgnoreCase("TagRule")){
//			for(String s : exsit)
//			System.out.println(s);
//		}
		if (CBOOfClassMap.get(currentClassName) == null) {
			CBOOfClassMap.put(currentClassName, 0l);
		}
		CBOOfClassMap.put(currentClassName, CBOOfClassMap.get(currentClassName) + exsit.size());
		return CBOOfClassMap;
	}

	private void addToMap(String className) {
		if (!currentClassName.equals(className)) {
			// System.out.println(className);
			if (!exsit.contains(className)) {
				if (CBOOfClassMap.get(className) == null) {
					CBOOfClassMap.put(className, 0l);

				}
				CBOOfClassMap.put(className, CBOOfClassMap.get(className) + 1l);

				exsit.add(className);
			}
		}
	}

}
