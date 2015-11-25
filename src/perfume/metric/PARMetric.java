package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.util.StringUtil;

/**
 * <ul>
 * <li>Name: PAR, number of Parameter</li>
 * <li>Granularity: Method</li>
 * <li>Default Values: -1 for method without body</li>
 * </ul>
 */
public class PARMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> PARMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {	
		setPkgClassName(node);
		MethodDeclaration[] methods = node.getMethods();
		for (MethodDeclaration method: methods) {
			String methodName = StringUtil.stringConnection(
					getPkgClassName(), ".",
					method.getName().toString());
			Block body = method.getBody();
			if (body == null) {
				PARMap.put(
						methodName, 
						-1l);
			}
			else {
				PARMap.put(
						methodName, 
						(long) (method.parameters().size()));
			}
		}
		
		return true;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return PARMap;
	}

}
