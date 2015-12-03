package perfume.metric;

import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * <ul>
 * <li>Name: NOM, Number Of Methods</li>
 * <li>Description: Don't measured for abstract method</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -1 for Abstract, -2 for Interface</li>
 * </ul>
 */
public class NOMMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOMMap = new HashMap<>();

	@Override
	public boolean visit(TypeDeclaration node) {
		countNOM(node);		
		return true;
	}
	
	private void countNOM(TypeDeclaration node) {
		setPkgClassName(node);
		long result = 0;
		
		if (node.isInterface()) {
			result = -2;
			NOMMap.put(getPkgClassName(), -2l);
			return;
		}
		
		result = node.getMethods().length;
		
		for (MethodDeclaration method: node.getMethods()) {
			if (Modifier.isAbstract(method.getModifiers())) {
				result--;
			}
		}
		
		NOMMap.put(getPkgClassName(), result);
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOMMap;
	}

	@Override
	public String getMetricName() {
		return "NOM";
	}

}
