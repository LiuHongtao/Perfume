package perfume.test.visitor;

import perfume.metric.visitor.ProjectInfoVisitor;
import perfume.metric.visitor.VisitorUtil;

public class ProjectInfoTest {

	public ProjectInfoTest(String projectPath) {
		ProjectInfoVisitor visitor = new ProjectInfoVisitor();
		VisitorUtil.startVisit(projectPath, visitor);
		visitor.print();
	}	
}
