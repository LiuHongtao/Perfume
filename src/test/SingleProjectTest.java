package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;

import util.CompliationUnitUtil;
import util.XlsOutput;
import visitor.AverageMcCabeVisitor;
import visitor.CompilationUnitVisitor;
import visitor.MRCVisitor;
import visitor.McCabeVisitor;
import visitor.MethodParameterCounter;
import visitor.ClassCountVisitor;

public class SingleProjectTest {

	private ASTVisitor visitor;
	private ArrayList<List<String>> output = new ArrayList<List<String>>();
	private HashMap<Integer, Integer> outMap= new HashMap<Integer, Integer>();
private String path ="E:\\waitingTest\\FreeCol";
	public SingleProjectTest() {
		long start = System.currentTimeMillis();
		try {
			 visitor = new AverageMcCabeVisitor(path,outMap);
			 output.addAll(((AverageMcCabeVisitor)visitor).getDetailResult());
//			McCabeVisitor visitor = new McCabeVisitor("E:\\waitingTest\\FreeCol");
//			MethodCounter visitor = new MethodCounter("E:\\Dropbox\\workspace-juno\\MethodCounter")C:\\Users\\Administrator\\Desktop\\MethodCounter\\src\\util;
//			MethodVisitor visitor = new MethodVisitor("C:\\Users\\XIANneo\\Desktop\\MethodCounter\\src");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (outMap.size() > 0) {
			for (int i = 0; i <= 200; i++) {
				if (outMap.containsKey(i)) {
					System.out.println(i + "\t" + outMap.get(i));
				} else {
					System.out.println(i + "\t" + "0");
				}
			}
		}
		if (output.size() != 0) {
			XlsOutput.generateExcel2003(path.substring(15), output);
			output.clear();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	

}
