package main.resources.perfume.test.visitor;

import main.resources.perfume.metric.visitor.ProjectInfoVisitor;
import main.resources.perfume.metric.visitor.VisitorUtil;

public class ProjectInfoTest {

	public ProjectInfoTest(String projectPath) {
		ProjectInfoVisitor visitor = new ProjectInfoVisitor();
		VisitorUtil.startVisit(projectPath, visitor);
		visitor.print();
	}	
}
