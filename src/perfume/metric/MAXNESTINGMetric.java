package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.MAXNESTINGMethodVisitor;
import perfume.util.StringUtil;
import perfume.util.ast.MethodUtil;

public class MAXNESTINGMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> MAXNESTINGMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {	
		setPkgClassName(node);
		MethodDeclaration[] methods = node.getMethods();
		for (MethodDeclaration method: methods) {
			if (!MethodUtil.isAbstract(method)) {
				MAXNESTINGMethodVisitor visitor = new MAXNESTINGMethodVisitor();
				method.accept(visitor);
				String methodName = StringUtil.stringConnection(
						getPkgClassName(), ".",
						method.getName().toString());
				MAXNESTINGMap.put(
						methodName, 
						visitor.getMAXNESTING());
			}
		}
		
		return false;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return MAXNESTINGMap;
	}

}
