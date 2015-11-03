package perfume.metric.visitor.AFTD;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.metric.visitor.AbstractMetricVisitor;
import perfume.util.LogUtil;

/**
 * <ul>
 * <li>Name: ATFD, Access To Foreign Data</li>
 * <li>Description: The number of attributes from unrelated classes accessed directly 
 * or by invoking accessor methods</li>
 * <li>Granularity: Class</li>
 * </ul>
 */
public class ATFDMetricVisitor extends AbstractMetricVisitor {
	private long ATFD = 0;		
	
	@Override
	public boolean visit(TypeDeclaration node) {
		LogUtil.print(node.getName().toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {		
		ATFD += node.fragments().size();
		return super.visit(node);
	}

	@Override
	public void beforeMeasurement() {
		ATFD = 0;		
	}

	@Override
	public void afterMeasurement() {
		LogUtil.print(ATFD);
	}

	@Override
	public long getMetricResult() {
		return ATFD;
	}
	
}
