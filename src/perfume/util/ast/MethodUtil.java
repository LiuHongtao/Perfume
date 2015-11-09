package perfume.util.ast;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class MethodUtil {
	
	public static boolean isAbstract(MethodDeclaration method) {
		List<ASTNode> modifiers = method.modifiers();
		for (ASTNode modifier: modifiers) {
			if (InstanceOfUtil.isModifier(modifier) && 
					((Modifier)modifier).isAbstract()) {
				return true;
			}
		}
		
		return false;
	}
}
