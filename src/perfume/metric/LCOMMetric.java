package perfume.metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import perfume.metric.visitor.LOCMMethodVisitor;
/**
 * <ul>
 * <li>Name: LCOM, Lack Of Cohesion between Methods </li>
 * <li>A class's lack of cohesion in methods (LCOM) metric counts the sets of 
 * methods in a class that are not related through the sharing of some of the 
 * class's fields. The original definition of this metric (which is the one 
 * used in ckjm) considers all pairs of a class's methods.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: </li>
 * </ul>
 */
public class LCOMMetric extends AbstractMetric {
	private Map<String, List<String>> methodList;
	private List<String> vdfs;
	boolean isMainClass = true; // is the main class or inner class
	int P = 0, Q = 0;
	private HashMap<String, Long> LCOMMap = new HashMap<String, Long>();
	private String className, javaPath;

	public boolean visit(TypeDeclaration node) {
		if (isMainClass) {
			className = node.getName().toString();
			FieldDeclaration[] fd = node.getFields();
			for (FieldDeclaration fdTemp : fd) {

				List fg = fdTemp.fragments();

				for (Object fgTemp : fg) {
					VariableDeclarationFragment vdf = (VariableDeclarationFragment) fgTemp;
					vdfs.add(vdf.getName().toString());
				}
			}

			MethodDeclaration[] mds = node.getMethods();
			int i = 0;
			for (MethodDeclaration md : mds) {

				LOCMMethodVisitor locmMV = new LOCMMethodVisitor(vdfs);
				md.accept(locmMV);
				methodList.put(md.getName().toString() + i, locmMV.getVisitorResult());
				i++;// incase of method overload
			}
		}
		isMainClass = false;
		return false;

	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		this.javaPath = javaPath;
		methodList = new HashMap<String, List<String>>();

		vdfs = new ArrayList<String>();
		P = 0;
		Q = 0;
	}

	@Override
	public void afterMetric() {
		isMainClass = true;
		Set<String> mlKeySet = methodList.keySet();

		for (String method : mlKeySet) {
			List<String> ml = methodList.get(method);
			//System.out.println(method + ml);
			boolean isnext = false;
			for (String method2 : mlKeySet) {
				if (isnext) {
					List<String> ml2 = methodList.get(method2);
					List<String> tempList = new ArrayList<String>();
					tempList.addAll(0, ml);
					tempList.retainAll(ml2);
					if (tempList.size()!=0) {
						//System.out.println("Q++\t" + method + "\t" + method2);
						Q++;
					} else {
						P++;
						//System.out.println("P++\t" + method + "\t" + method2);
					}
				}
				if (method2.equals(method)) {
					isnext = true;
				}
			}
		}
		LCOMMap.put(/*javaPath + "\t" +*/ className, (long) (P - Q >= 0 ? P - Q : 0));

	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return LCOMMap;
	}

}
