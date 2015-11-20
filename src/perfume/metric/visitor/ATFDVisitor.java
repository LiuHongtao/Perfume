package perfume.metric.visitor;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ThisExpression;
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
	
	/**
	 * for field access like foo.bar or Class.bar
	 */
	@Override
	public boolean visit(QualifiedName node) {
		ASTNode parent = node.getParent(); 
		// type like java.io.IOException
		if (parent instanceof Type) {
			return false;
		}
		// method invocation like System.out.println()
		else if (parent instanceof MethodInvocation) {
			MethodInvocation invock = (MethodInvocation)parent;
			if (node == invock.getExpression()) {
				return false;
			}
		}

		ATFD++;
		
		return false;
	}
	
	/**
	 * for field access like func().bar
	 */
	@Override
	public boolean visit(FieldAccess node) {
		if (node.getExpression() instanceof MethodInvocation) {
			ATFD++;
		}
		
		return true;
	}

	/**
	 * for accessor
	 */
	@Override
	public boolean visit(MethodInvocation node) {
		if (accessorSet.contains(
				node.getName().toString())) {
			ATFD++;
		}
		return true;
	}
}
