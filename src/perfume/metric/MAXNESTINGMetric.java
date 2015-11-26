package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.MAXNESTINGMethodVisitor;
import perfume.util.StringUtil;
import perfume.util.ast.MethodUtil;

/**
 * <ul>
 * <li>Name: MAXNESTING, Max Nesting Level</li>
 * <li>Granularity: Method</li>
 * <li>Default Values: -1 for method without body</li>
 * </ul>
 */
public class MAXNESTINGMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> MAXNESTINGMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {	
		setPkgClassName(node);
		
		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		String methodName = StringUtil.stringConnection(
				getPkgClassName(), ".",
				node.getName().toString());
		Block body = node.getBody();
		if (body == null) {
			MAXNESTINGMap.put(
					methodName, 
					-1l);
		}
		else {
			MAXNESTINGMethodVisitor visitor = new MAXNESTINGMethodVisitor();
			body.accept(visitor);
			MAXNESTINGMap.put(
					methodName, 
					visitor.getMAXNESTING());
		}
		
		return false;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return MAXNESTINGMap;
	}

}
