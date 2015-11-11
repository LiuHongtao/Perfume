package perfume.metric;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;


import perfume.metric.visitor.CBOClassVisitor;

/**
 * <ul>
 * <li>Name: CBO, Coupling Between Objects</li>
 * <li>Description: CBO for a class is a count of the number of other classes to
 * which it is coupled.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
public class CBOMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> CBOMap = new HashMap<>();
	private HashMap<String, Long> CBOOfClassMap = new HashMap<>();
	public boolean visit(TypeDeclaration node) {
		CBOMap.put(node.getName().toString(), 1l);
		CBOClassVisitor cboClassVisitor = new CBOClassVisitor(CBOOfClassMap);
		
		node.accept(cboClassVisitor);

		return false;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterMetric() {
		

	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return CBOMap;
	}

}
