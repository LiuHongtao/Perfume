package perfume.metric.visitor;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.util.LogUtil;
import perfume.util.ast.InstanceOfUtil;

/**
 * <ul>
 * <li>Name: NOM, Number Of Methods</li>
 * <li>Description: Don't measured for Abstract classes, Interface, and inner classes?</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -1 for Abstract, -2 for Interface</li>
 * </ul>
 */
public class NOMMetricVisitor extends AbstractMetricVisitor {
	private long NOM = 0;

	@Override
	public boolean visit(TypeDeclaration node) {
		//TODO
		LogUtil.print(node.getName().toString());
		countNOM(node);		
		return false;
	}
	
	private void countNOM(TypeDeclaration node) {
		List<ASTNode> modifiers = node.modifiers();
		for (ASTNode modifier: modifiers) {
			if (InstanceOfUtil.isModifier(modifier) && ((Modifier)modifier).isAbstract()) {
				NOM = -1;
				return;
			}
		}
		
		if (node.isInterface()) {
			NOM = -2;
			return;
		}
		
		NOM = node.getMethods().length;
	}
	
	@Override
	public void beforeMeasurement() {
		NOM = 0;
	}

	@Override
	public void afterMeasurement() {
		LogUtil.print(NOM);
	}

	@Override
	public long getMetricResult() {
		return NOM;
	}

}