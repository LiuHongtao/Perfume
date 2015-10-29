package test;

import util.CompliationUnitUtil;
import visitor.FieldVisitorTest;
import visitor.McCabeVisitor;
import visitor.MethodLineCode;

public class SingleFileTest {
	public SingleFileTest() {
	long start = System.currentTimeMillis();
	try {
		CompliationUnitUtil t = new CompliationUnitUtil("./src/visitor/ClassCountVisitor.java");
//		t.getResult();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long end = System.currentTimeMillis();
	System.out.println(end - start);
	}
}
