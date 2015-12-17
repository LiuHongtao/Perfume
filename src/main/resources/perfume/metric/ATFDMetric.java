package main.resources.perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import main.resources.perfume.metric.visitor.ATFDVisitor;
import main.resources.perfume.metric.visitor.AccessorCollectionVisitor;
import main.resources.perfume.metric.visitor.VisitorUtil;

/**
 * <ul>
 * <li>Name: ATFD, Access To Foreign Data</li>
 * <li>Description: The number of attributes from unrelated classes accessed directly 
 * or by invoking accessor methods</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
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
		
		if (node.isInterface()) {
			ATFDMap.put(getPkgClassName(), -2l);
			return true;
		}
		
		ATFDVisitor visitor = new ATFDVisitor(mVisitor.getAccessorSet());
		
		for (MethodDeclaration method: node.getMethods()) {
			Block body = method.getBody();
			if (body != null) {
				body.accept(visitor);
			}
		}
		
		ATFDMap.put(getPkgClassName(), visitor.getATFD());
		
		return true;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return ATFDMap;
	}

	@Override
	public String getMetricName() {
		return "ATFD";
	}

}
