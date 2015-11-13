package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class TCCMetric extends AbstractMetric {
	//紧密类内聚：设一个类中有Ⅳ个公有方法；NP是共有方法对
	//的最大值，NP=[Ⅳ×(Ⅳ一1)]／2；NDc表示共有方法之间直
	//接关联的方法对数目，则Tcc=NDc／NP
	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterMetric() {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
