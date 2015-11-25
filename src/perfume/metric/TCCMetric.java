package perfume.metric;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import perfume.metric.model.*;
/**
 * <ul>
 * <li>Name: TCC, Tight Class Cohesion</li>
 * <li>Description: the relative number of method 
 * pairs of a class that access in common at least
 *  one attribute of the measured class</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: -2 for Interface</li>
 * </ul>
 */
public class TCCMetric extends AbstractMetric {
	// 紧密类内聚：设一个类中有Ⅳ个公有方法；NP是共有方法对
	// 的最大值，NP=[Ⅳ×(Ⅳ一1)]／2；NDc表示共有方法之间直
	// 接关联的方法对数目，则Tcc=NDc／NP

	private Set<String> attributeSet;
	private Set<MethodParam> methodList;
	private HashMap<String, Long> TCCMap = new HashMap<>();

	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);
		FieldDeclaration[] fd = node.getFields();
		for (FieldDeclaration fdTemp : fd) {
			// int md = fdTemp.getModifiers();
			// Type tp = fdTemp.getType();
			List fg = fdTemp.fragments();
			for (Object fgTemp : fg) {
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) fgTemp;
				// IVariableBinding ivb = vdf.resolveBinding();
				attributeSet.add(vdf.getName().toString());

			}
		}
		MethodVisitor mv = new MethodVisitor();
		MethodDeclaration[] mds = node.getMethods();
		for (MethodDeclaration md : mds) {
			md.accept(mv);
		}
		TCCMap.put(node.getName().getIdentifier(), (long)getResult());

		return false;

	}

	class MethodVisitor extends ASTVisitor {
		private MethodParam param;

		// 获得方法遍历
		public boolean visit(MethodDeclaration node) {
			param = new MethodParam();

			param.setMethodName(node.getName().getIdentifier());
			methodList.add(param);
			// KEY:
			// "Lcn/csu/plusin/jsmellprober/visitor/TCCVisitor;.param)Lcn/csu/plusin/jsmellprober/model/MethodParam;"
			// KEY:
			// "Lcn/csu/plusin/jsmellprober/visitor/TCCVisitor;.param)Lcn/csu/plusin/jsmellprober/model/MethodParam;"
			return true;

		}

		public boolean visit(SimpleName node) {
			// 获得全部变量
			if (attributeSet.contains(node.toString())) {
				// IVariableBinding itb = (IVariableBinding)
				// node.resolveBinding();
				param.addMethodVarible(node.toString());
//				System.out.println(param.getMethodName()+param.getMethodVaribleList().toString());
				
			}

			return false;

		}
	}

	// 计算阶乘数
	private int fac(int n) {
		int i, t = 1;
		if (n > 0) {
			for (i = 1; i <= n; i++) {
				t = t * i;
			}
		}
		return t;
	}

	// 计算组合数ICPVisitor
	public int zuhe(int n, int r) {
		int fenzi = 1;
		for (int i = 0; i < r; i++) {
			fenzi *= (n - i);
		}
		return fenzi / fac(r);
	}

	public int getResult() {
		int pairsOfMethod = 0;
		int methodNum = methodList.size();
		if (methodNum > 2) {

			int pairsOfComAttrMethod = 0;
			pairsOfMethod = zuhe(methodNum, 2);
			// 遍历类的所有属性
			for (String akey : attributeSet) {
				// 属性在多少方法中出现
				int attributeUseTime = 0;
				// 变量所有方法，看是否有使用属性
				for (MethodParam mp : methodList) {
					if (mp.getMethodVaribleList().contains(akey)) {
						attributeUseTime++;
					}
				}
				// 计算出现的组合
				if (attributeUseTime >= 2) {
					pairsOfComAttrMethod += zuhe(attributeUseTime, 2);
				}

			}
			return (int) ((pairsOfComAttrMethod / (double) pairsOfMethod) * 100);
		}
		return 0;

	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		attributeSet = new HashSet<String>();
		methodList = new HashSet<MethodParam>();

	}

	@Override
	public void afterMetric() {

	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return TCCMap;
	}

	 public static void iii(){
		 
	 }
}
