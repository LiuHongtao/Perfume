package perfume.metric.visitor;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.util.LogUtil;
import perfume.util.ast.InstanceOfUtil;

/**
 * <ul>
 * <li>Name: NOPA, Number Of Public Attribute</li>
 * <li>Description: The Number of Public Attributes, which are not static and constant, 
 * of a class. Don't measured for Abstract classes, and inner classes?</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
public class NOPAMetricVisitor extends AbstractMetricVisitor {
	private HashMap<String, Long> NOPAMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {
		countNOPA(node);		
		return false;
	}
	
	private void countNOPA(TypeDeclaration node) {		
		mPkgNameBuilder.append(node.getName().toString());
		long result = 0;
		
		if (node.isInterface()) {
			result = -2;
			NOPAMap.put(mPkgNameBuilder.toString(), -2l);
			return;
		}
		
		for (FieldDeclaration field: node.getFields()) {
			List<ASTNode> modifiers = field.modifiers();
			boolean flag = false;
			for (ASTNode modifier: modifiers) {
				// if the Attribute is not static or final
				// it have to be public
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
			result += flag ? field.fragments().size() : 0;
		}
		NOPAMap.put(mPkgNameBuilder.toString(), result);
	}
	
	@Override
	public void beforeMeasurement(String javaPath,CompilationUnit compUnit) {
		getPkgName(compUnit);
	}

	@Override
	public void afterMeasurement() {
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOPAMap;
	}	
}
