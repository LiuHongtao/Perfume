package perfume.metric.visitor;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class ATFDVisitor extends ASTVisitor {

	private long ATFD;
	
	public long getATFD() {
		return ATFD;
	}
	
	private HashSet<String> accessorSet;
	
	public ATFDVisitor(HashSet<String> accessorSet) {
		this.ATFD = 0;
		this.accessorSet = accessorSet;
	}

	@Override
	public boolean visit(FieldAccess node) {
		ATFD++;
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodInvocation node) {
		if (accessorSet.contains(
				node.getName().toString())) {
			ATFD++;
		}
		return super.visit(node);
	}
}
