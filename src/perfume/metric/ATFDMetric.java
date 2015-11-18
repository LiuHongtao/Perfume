package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;

import perfume.metric.visitor.AccessorCollectionVisitor;
import perfume.metric.visitor.VisitorUtil;

public class ATFDMetric extends AbstractMetric {
	
	AccessorCollectionVisitor mVisitor;
	
	public ATFDMetric(String projectPath) {
		mVisitor = new AccessorCollectionVisitor();
		VisitorUtil.startVisit(projectPath, mVisitor);
		System.out.println();
	}

	@Override
	public void beforeMetric(String projectPath, CompilationUnit compUnit) {
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
