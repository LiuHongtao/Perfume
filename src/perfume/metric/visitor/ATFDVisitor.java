package perfume.metric.visitor;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.Type;

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
	public boolean visit(QualifiedName node) {
		ASTNode parent = node.getParent(); 
		if (parent instanceof Type) {
			return false;
		}
		else if (parent instanceof MethodInvocation) {
			MethodInvocation invock = (MethodInvocation)parent;
			if (node == invock.getExpression()) {
				return false;
			}
		}

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
