package perfume.metric.visitor;

import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class NOAMetricVisitor extends AbstractMetricVisitor {

	private String javaPath;
	private CompilationUnit compUnit;

	public boolean visit(TypeDeclaration node) {int countArributes = 0;
		System.out.println("Class Name:"+node.getName().toString());
		FieldDeclaration[] fd = node.getFields();
		for (FieldDeclaration fdTemp : fd) {
			int md = fdTemp.getModifiers();
			Type tp = fdTemp.getType();
			List fg = fdTemp.fragments();
			countArributes+=fg.size();
			for (Object fgTemp : fg) {
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) fgTemp;
				System.out.println(md + "\t" + tp.toString() + "\t" + vdf.getName().toString());
			}
		}System.out.println("Total:"+countArributes);

		return false;

	}

	@Override
	public void beforeMeasurement(String javaPath, CompilationUnit compUnit) {
		this.compUnit = compUnit;
		this.javaPath = javaPath;
		System.out.println(javaPath);

	}

	@Override
	public void afterMeasurement() {
		// TODO Auto-generated method stub

	}

	@Override
	public long getMetricResult() {
		// TODO Auto-generated method stub
		return 0;
	}

}
