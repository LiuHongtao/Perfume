package main.resources.perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import main.resources.perfume.metric.visitor.NOC2Visitor;

public class NOC2Metric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOC2Map = new HashMap<>();
	private Long result = new Long(0);

	public boolean visit(TypeDeclaration node) {
		setPkgClassName(node);
		if (node.isInterface()) {
			NOC2Map.put(getPkgClassName(), -2l);
			return true;
		}

		MethodDeclaration[] mds = node.getMethods();
		NOC2Visitor noc2v = new NOC2Visitor();
		for (MethodDeclaration md : mds) {
			md.accept(noc2v);
		}

		result += noc2v.getResult();
		NOC2Map.put(getPkgClassName(), result);
		result = (long) 0;
		return true;
	}

	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);

	}

	@Override
	public void afterMetric() {
		super.afterMetric();
	}

	@Override
	public String getMetricName() {

		return "NOC2";
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return NOC2Map;
	}

}
