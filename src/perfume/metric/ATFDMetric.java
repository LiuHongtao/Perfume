package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.ATFDVisitor;
import perfume.metric.visitor.AccessorCollectionVisitor;
import perfume.metric.visitor.VisitorUtil;

public class ATFDMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> ATFDMap = new HashMap<>();
	
	AccessorCollectionVisitor mVisitor;
	
	public ATFDMetric(String projectPath) {
		mVisitor = new AccessorCollectionVisitor();
		VisitorUtil.startVisit(projectPath, mVisitor);
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);
		ATFDVisitor visitor = new ATFDVisitor(mVisitor.getAccessorSet());
		
		for (MethodDeclaration method: node.getMethods()) {
			method.accept(visitor);
		}
		
		ATFDMap.put(getPkgClassName(), visitor.getATFD());
		
		return true;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return ATFDMap;
	}

}
