package main.resources.perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import main.resources.perfume.metric.model.MethodParam;
import main.resources.perfume.metric.visitor.CYCLOVisitor;

/**
 * <ul>
 * <li>Name: CYCLO, McCabe’s CYCLOmatic complexity</li>
 * <li>Description: McCabe’s CYCLOmatic complexity of a java class.</li>
 * <li>Granularity: Class</li>
 * <li>Default Values: Total number of CYCLO of a class</li>
 * </ul>
 */
public class CYCLOMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> CYCLOMetric = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;

	private String source;
	private MethodParam param;
	private boolean isVisited = false, isInterface = false;
	private CYCLOVisitor cycloVisitor;
	private long cyclomatic = 1;
	public boolean visit(TypeDeclaration node) {
//		if (!isVisited) {
//			isVisited = true;
			setPkgClassName(node);
//		}

		long result;

		if (node.isInterface()) {
			isInterface = true;
			result = -2;
			CYCLOMetric.put(getPkgClassName(), result);
			return true;
		}
		long totalCYCLO = 0;
		MethodDeclaration[] mds = node.getMethods();
		for (MethodDeclaration md : mds) {
			String methodName = getPkgClassName()+"."+md.getName().toString();
//			System.out.println(methodName+" "+CYCLOMethodMetric.get(methodName));
			try{
				md.accept(cycloVisitor);
				cyclomatic = cycloVisitor.getMetricResult();
//				System.out.println(cyclomatic);
				totalCYCLO+=cyclomatic;
//				System.out.println(totalCYCLO);
			}catch(Exception e){
				System.out.println(node.getName().toString()+" "+methodName);
				e.printStackTrace();
			}
			
			
		}
		CYCLOMetric.put(getPkgClassName(), totalCYCLO);
		return true;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);
		cycloVisitor = new CYCLOVisitor();
		cycloVisitor.beforeMetric(javaPath, compUnit);

		this.javaPath = javaPath;
		
		this.compUnit = compUnit;
	}

	@Override
	public void afterMetric() {
		source = "";
		// System.out.println(getPkgClassName()+totalCYCLO);
//		if (!isInterface) {
//			CYCLOMetric.put(getPkgClassName(), (long) totalCYCLO);
//		}
		isInterface = false;
		
		
	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return CYCLOMetric;
	}

	@Override
	public String getMetricName() {
		return "CYCLO";
	}

}
