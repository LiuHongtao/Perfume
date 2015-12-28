package main.resources.perfume.metric.visitor;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.SwitchCase;

import main.resources.perfume.metric.AbstractMetricVisitor;

public class NOC2Visitor extends AbstractMetricVisitor {
	private Long result  = new Long(0);;

	public boolean visit(SwitchCase node) {

		result++;
		return true;
	}

	public Long getResult(){
		Long tmp =result;
		result =(long) 0;
		return tmp;
	}
	@Override
	public String getMetricName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
