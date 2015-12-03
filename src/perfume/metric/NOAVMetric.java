package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.VarInMethodVisitor;
import perfume.util.StringUtil;

/**
 * <ul>
 * <li>Name: NOAV, Number Of Accessed Variables</li>
 * <li>Description: Include temporary variables, method parameters, ?</li>
 * <li>Granularity: Method</li>
 * </ul>
 */
public class NOAVMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOAVMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {	
		setPkgClassName(node);
		
		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		long result = node.parameters().size();
		VarInMethodVisitor visitor = new VarInMethodVisitor();
		
		Block body = node.getBody();
		if (body != null) {
			body.accept(visitor);
		}
		
		result += visitor.getCount();
		
		String methodName = StringUtil.stringConnection(
				getPkgClassName(), ".",
				node.getName().toString());
		
		NOAVMap.put(methodName, result);
		
		return false;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOAVMap;
	}
	
	@Override
	public String getMetricName() {
		return "NOAV";
	}

}
