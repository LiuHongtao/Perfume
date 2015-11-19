package perfume.metric;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * <ul>
 * <li>Name: NOA, Number Of Attributes</li>
 * <li>Description: Number of attributes in one class.
 * <li>Granularity: Class</li>
 * <li>Default Values: Number Of Attributes</li>
 * </ul>
 */
public class NOAMetric extends AbstractMetric {

	private String javaPath;
	private CompilationUnit compUnit;
	private HashMap<String, Long> NOAMap = new HashMap<>();
	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);
		int countArributes = 0;
		System.out.println("Class Name:" + node.getName().toString());
		FieldDeclaration[] fd = node.getFields();
		for (FieldDeclaration fdTemp : fd) {
			int md = fdTemp.getModifiers();
			Type tp = fdTemp.getType();
			List fg = fdTemp.fragments();
			countArributes += fg.size();
			for (Object fgTemp : fg) {
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) fgTemp;
//				System.out.println(md + "\t" + tp.toString() + "\t" + vdf.getName().toString());
			}
		}
//		System.out.println("Total:" + countArributes);
		NOAMap.put(getPkgClassName(), (long)countArributes);
		return false;

	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		this.compUnit = compUnit;
		this.javaPath = javaPath;
//		System.out.println(javaPath);

	}

	@Override
	public void afterMetric() {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		 
		return NOAMap;
	}
}
