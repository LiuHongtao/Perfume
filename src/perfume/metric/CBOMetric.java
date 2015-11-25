package perfume.metric;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.CBOClassVisitor;

/**
 * <ul>
 * <li>Name: CBO, Coupling Between Objects</li>
 * <li>Description: CBO for a class is a count of the number of other classes to
 * which it is coupled.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 Interface</li>
 * </ul>
 */
public class CBOMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> CBOMap = new HashMap<>();
	private HashMap<String, Long> CBOOfClassMap = new HashMap<>();
	private String currentClassName;

	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);
		if (node.isInterface()) {
			CBOMap.put(getPkgClassName(), -2l);
			return true;
		}
		
		String className = getPkgClassName();
		currentClassName = className;
		if (!CBOMap.containsKey(className))
			CBOMap.put(className, 0l);
		CBOClassVisitor cboClassVisitor = new CBOClassVisitor(CBOOfClassMap,currentClassName);

		node.accept(cboClassVisitor);
		cboClassVisitor.getMetricResult();
		return true;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterMetric() {

	}

	public void combineMap() {

		Iterator<Entry<String, Long>> iter1 = CBOMap.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry<String, Long> entry1 = (Entry<String, Long>) iter1.next();
			Long m1value = entry1.getValue() == null ? 0 : entry1.getValue();
			Long m2value = CBOOfClassMap.get(entry1.getKey()) == null ? 0 : CBOOfClassMap.get(entry1.getKey());

			if (m2value != null) {
				CBOMap.put(entry1.getKey(), m1value + m2value);
			}

		}
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		combineMap();
		return CBOMap;
	}

}
