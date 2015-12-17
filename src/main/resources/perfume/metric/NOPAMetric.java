package main.resources.perfume.metric;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * <ul>
 * <li>Name: NOPA, Number Of Public Attribute</li>
 * <li>Description: The Number of Public Attributes, which are not static and constant, 
 * of a class.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
public class NOPAMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOPAMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {
		countNOPA(node);		
		return true;
	}
	
	private void countNOPA(TypeDeclaration node) {
		setPkgClassName(node);
		long result = 0;
		
		if (node.isInterface()) {
			result = -2;
			NOPAMap.put(getPkgClassName(), -2l);
			return;
		}
		
		for (FieldDeclaration field: node.getFields()) {
			List<ASTNode> modifiers = field.modifiers();
			boolean flag = false;
			for (ASTNode modifier: modifiers) {
				// if the Attribute is not static or final
				// it have to be public
				if (modifier instanceof Modifier) {
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
			result += flag ? field.fragments().size() : 0;
		}
		NOPAMap.put(getPkgClassName(), result);
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOPAMap;
	}

	@Override
	public String getMetricName() {
		return "NOPA";
	}	
}
