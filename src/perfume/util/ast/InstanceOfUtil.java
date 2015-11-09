package perfume.util.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class InstanceOfUtil {
	
	public static boolean isEmptyStatement(ASTNode node) {
		return (node instanceof EmptyStatement);
	}
	
	public static boolean isBlock(ASTNode node) {
		return (node instanceof Block);
	}
	
	public static boolean isMethodInvocation(ASTNode node) {
		return (node instanceof MethodInvocation);
	}
	
	public static boolean isExpressionStatement(ASTNode node) {
		return (node instanceof ExpressionStatement);
	}
	
	public static boolean isParenthesizedExpression(ASTNode node) {
		return (node instanceof ParenthesizedExpression);
	}
	
	public static boolean isAssignment(ASTNode node) {
		return (node instanceof Assignment);
	}
	
	public static boolean isPrefixExpression(ASTNode node) {
		return (node instanceof PrefixExpression);
	}
	
	public static boolean isInfixExpression(ASTNode node) {
		return (node instanceof InfixExpression);
	}
	
	public static boolean isPostfixExpression(ASTNode node) {
		return (node instanceof PostfixExpression);
	}
	
	public static boolean isSimpleName(ASTNode node) {
		return (node instanceof SimpleName);
	}
	
	public static boolean isFieldAccess(ASTNode node) {
		return (node instanceof FieldAccess);
	}
	
	public static boolean isAnnotation(ASTNode node) {
		return (node instanceof Annotation);
	}
	
	public static boolean isMarkerAnnotation(ASTNode node) {
		return (node instanceof MarkerAnnotation);
	}
	
	public static boolean isThisExpression(ASTNode node) {
		return (node instanceof ThisExpression);
	}
	
	public static boolean isTypeDeclaration(ASTNode node) {
		return (node instanceof TypeDeclaration);
	}
	
	public static boolean isVariableDeclarationFragment(ASTNode node) {
		return (node instanceof VariableDeclarationFragment);
	}
	
	public static boolean isPrimitiveType(ASTNode node) {
		return (node instanceof PrimitiveType);
	}
	
	public static boolean isArrayType(ASTNode node) {
		return (node instanceof ArrayType);
	}
	
	public static boolean isSimpleType(ASTNode node) {
		return (node instanceof SimpleType);
	}
	
	public static boolean isSwitchCase(ASTNode node) {
		return (node instanceof SwitchCase);
	}
	
	public static boolean isBreakStatement(ASTNode node) {
		return (node instanceof BreakStatement);
	}
	
	public static boolean isIfStatement(ASTNode node) {
		return (node instanceof IfStatement);
	}
	
	public static boolean isMethodDeclaration(ASTNode node) {
		return (node instanceof MethodDeclaration);
	}
	
	public static boolean isFieldDeclaration(ASTNode node) {
		return (node instanceof FieldDeclaration);
	}
	
	public static boolean isName(ASTNode node) {
		return (node instanceof Name);
	}
	
	public static boolean isType(ASTNode node) {
		return (node instanceof Type);
	}
	
	public static boolean isArrayAccess(ASTNode node) {
		return (node instanceof ArrayAccess);
	}
	
	public static boolean isModifier(ASTNode node) {
		return (node instanceof Modifier);
	}
	
	public static boolean isReturnStatement(ASTNode node) {
		return (node instanceof ReturnStatement);
	}
}
