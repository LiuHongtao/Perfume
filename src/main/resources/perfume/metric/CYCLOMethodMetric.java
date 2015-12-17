package main.resources.perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import main.resources.perfume.metric.model.MethodParam;
import main.resources.perfume.metric.visitor.CYCLOVisitor;

public class CYCLOMethodMetric extends AbstractMetricVisitor {

	private HashMap<String, Long> CYCLOMethodMetric = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;
	private long cyclomatic = 1;
	private String source;
	private MethodParam param;
	private CYCLOVisitor cycloVisitor;
	public boolean visit(TypeDeclaration node){
		setPkgClassName(node);
		MethodDeclaration[] mds = node.getMethods();
		for (MethodDeclaration md : mds) {
			String methodName = getPkgClassName()+"."+md.getName().toString();
//			
			try{
				md.accept(cycloVisitor);
				cyclomatic = cycloVisitor.getMetricResult();
				
				CYCLOMethodMetric.put(methodName, cyclomatic);
			}catch(Exception e){
				System.out.println(node.getName().toString()+" "+methodName);
				System.out.println(CYCLOMethodMetric);
				e.printStackTrace();
			}
			
			
		}
//		getMetricResult();
		return true;
		
	}
	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);
		this.javaPath = javaPath;
		
		this.compUnit = compUnit;
		cycloVisitor = new CYCLOVisitor();
		cycloVisitor.beforeMetric(javaPath, compUnit);
	}

	@Override
	public void afterMetric() {
		source = "";
		cyclomatic = 1;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return CYCLOMethodMetric;
	}

	@Override
	public String getMetricName() {
		return "CYCLO";
	}

}

