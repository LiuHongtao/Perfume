package perfume.metric;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.util.ast.InstanceOfUtil;

/**
 * <ul>
 * <li>Name: NOM, Number Of Methods</li>
 * <li>Description: Don't measured for Abstract classes, Interface, and inner classes?</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -1 for Abstract, -2 for Interface</li>
 * </ul>
 */
public class NOMMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOMMap = new HashMap<>();

	@Override
	public boolean visit(TypeDeclaration node) {
		countNOM(node);		
		return false;
	}
	
	private void countNOM(TypeDeclaration node) {
		mPkgNameBuilder.append(node.getName().toString());
		long result = 0;
		
		if (node.isInterface()) {
			result = -2;
			NOMMap.put(mPkgNameBuilder.toString(), -2l);
			return;
		}
		
		result = node.getMethods().length;
		
		for (MethodDeclaration method: node.getMethods()) {
			List<ASTNode> modifiers = method.modifiers();
			for (ASTNode modifier: modifiers) {
				if (InstanceOfUtil.isModifier(modifier) && 
						((Modifier)modifier).isAbstract()) {
					result--;
				}
			}
		}
		
		NOMMap.put(mPkgNameBuilder.toString(), result);
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOMMap;
	}

}
