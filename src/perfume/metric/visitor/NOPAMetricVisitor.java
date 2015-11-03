package perfume.metric.visitor;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.util.LogUtil;
import perfume.util.ast.InstanceOfUtil;

/**
 * <ul>
 * <li>Name: NOPA, Number Of Public Attribute</li>
 * <li>Description: The Number of Public Attributes, which are not static and constant, 
 * of a class. Don't measured for Abstract classes, and inner classes?</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -1 for Abstract, -2 for Interface</li>
 * </ul>
 */
public class NOPAMetricVisitor extends AbstractMetricVisitor {
	private long NOPA = 0;
	
	@Override
	public boolean visit(TypeDeclaration node) {
		//TODO
		LogUtil.print(node.getName().toString());
		countNOPA(node);		
		return false;
	}
	
	private void countNOPA(TypeDeclaration node) {
		List<ASTNode> modifiers = node.modifiers();
		for (ASTNode modifier: modifiers) {
			if (InstanceOfUtil.isModifier(modifier) && ((Modifier)modifier).isAbstract()) {
				NOPA = -1;
				return;
			}
		}
		
		if (node.isInterface()) {
			NOPA = -2;
			return;
		}
		
		for (FieldDeclaration field: node.getFields()) {
			modifiers = field.modifiers();
			boolean flag = false;
			for (ASTNode modifier: modifiers) {
				if (InstanceOfUtil.isModifier(modifier)) {
					if (((Modifier)modifier).isStatic() ||
							((Modifier)modifier).isFinal()) {
						flag = false;
						break;
					}
					else if (((Modifier)modifier).isPublic()) {
						flag = true;
					}
				}
			}
			NOPA += flag ? field.fragments().size() : 0;
		}
	}
	
	@Override
	public void beforeMeasurement() {
		NOPA = 0;
	}

	@Override
	public void afterMeasurement() {
		LogUtil.print(NOPA);
	}

	@Override
	public long getMetricResult() {
		return NOPA;
	}
}
