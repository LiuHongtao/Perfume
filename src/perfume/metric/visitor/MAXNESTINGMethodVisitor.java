package perfume.metric.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class MAXNESTINGMethodVisitor extends ASTVisitor {

	private int MAXNESTING = 0;
	
	public long getMAXNESTING() {
		return MAXNESTING;
	}
	
	private int level = 0;
	
	private void startNest() {
		level++;
		if (level > MAXNESTING) {
			MAXNESTING = level;
		}
	}
	
	private void endNest() {
		level--;
	}
	
	@Override
	public boolean visit(DoStatement node) {
		startNest();
		return true;
	}
	
	@Override
	public boolean visit(EnhancedForStatement node) {
		startNest();
		return true;
	}
	
	@Override
	public boolean visit(ForStatement node) {
		startNest();
		return true;
	}
	
	@Override
	public boolean visit(IfStatement node) {
		startNest();
		return true;
	}
	
	@Override
	public boolean visit(SwitchStatement node) {
		startNest();
		return true;
	}
	
	@Override
	public boolean visit(WhileStatement node) {
		startNest();
		return true;
	}
	
	@Override
	public void endVisit(DoStatement node) {
		super.endVisit(node);
		endNest();
	}
	
	@Override
	public void endVisit(EnhancedForStatement node) {
		endNest();
	}
	
	@Override
	public void endVisit(ForStatement node) {
	}
	
	@Override
	public void endVisit(IfStatement node) {
		endNest();
	}
	
	@Override
	public void endVisit(SwitchStatement node) {
		endNest();
	}
	
	@Override
	public void endVisit(WhileStatement node) {
		endNest();
	}
}
