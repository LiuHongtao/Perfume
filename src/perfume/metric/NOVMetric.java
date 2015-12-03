package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.VarInMethodVisitor;

/**
 * <ul>
 * <li>Name: NOV, Number Of Variables</li>
 * <li>Description: Include temporary variables, method parameters</li>
 * <li>Granularity: Class</li>
 * </ul>
 */
public class NOVMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOVMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);
		
		long result = 0;
		VarInMethodVisitor visitor = new VarInMethodVisitor();
		
		for (MethodDeclaration method: node.getMethods()) {
			result += method.parameters().size();
			Block body = method.getBody();
			if (body != null) {
				body.accept(visitor);
			}
		}
		
		result += visitor.getCount();
		
		NOVMap.put(getPkgClassName(), result);
		
		return true;
	}
		
	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOVMap;
	}

	@Override
	public String getMetricName() {
		return "NOV";
	}

}
