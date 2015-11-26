package perfume.metric.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class VarInMethodVisitor extends ASTVisitor {
	
	private long count;
	
	public long getCount() {
		return count;
	}
	
	public VarInMethodVisitor() {
		this.count = 0;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		count++;
		return true;
	}
	
	
}
