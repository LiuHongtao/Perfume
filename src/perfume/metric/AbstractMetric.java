package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import perfume.util.StringUtil;

public abstract class AbstractMetric extends ASTVisitor {

	public abstract String getMetricName();
	
	/**
	 * Before the measurement started
	 */
	public abstract void beforeMetric(String projectPath, CompilationUnit compUnit);
	
	/**
	 * After the measurement finished
	 */
	public abstract void afterMetric();
	
	/**
	 * Return the metric result
	 * @return
	 */
	public abstract HashMap<String, Long> getMetricResult();
	
	private String mPkgName;
	
	/**
	 * Save the package name of the class
	 * @param compUnit
	 */
	protected void setPkgName(CompilationUnit compUnit) {
		PackageDeclaration pd = compUnit.getPackage();
		if (pd != null) {
			mPkgName = compUnit.getPackage().getName().getFullyQualifiedName();
		}
		else {
			mPkgName = "";
		}
	}
	
	protected String getPkgName() {
		return mPkgName;
	}
	
	private String mPkgClassName;
	
	protected void setPkgClassName(TypeDeclaration type) {
		mPkgClassName = StringUtil.stringConnection(
				mPkgName, ".", 
				type.getName().getIdentifier());
	}
	
	protected String getPkgClassName() {
		return mPkgClassName;
	}
}
