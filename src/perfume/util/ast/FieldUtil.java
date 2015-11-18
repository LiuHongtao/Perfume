package perfume.util.ast;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class FieldUtil {

	public static boolean isPublic(FieldDeclaration field) {
		List<ASTNode> modifiers = field.modifiers();
		for (ASTNode modifier: modifiers) {
			if (InstanceOfUtil.isModifier(modifier) && 
					((Modifier)modifier).isPublic()) {
				return true;
			}
		}
		
		return false;
	}
}
