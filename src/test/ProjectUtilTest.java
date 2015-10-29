package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;
import org.eclipse.jdt.core.dom.ASTVisitor;

import util.ProjectUtil;
import util.XlsOutput;
import visitor.*;

public class ProjectUtilTest {
	private ArrayList<List<String>> output = new ArrayList<List<String>>();
	private ASTVisitor visitor;
	private HashMap<Integer, Integer> outMap;

	public ProjectUtilTest() throws Exception {
		String t ="";
		boolean select =  false;
		if(select){
			t = "E:\\waitingTest\\";
			
		}else{
			t = "E:\\joss-1\\";
		}
		 
		
		ProjectUtil pj = new ProjectUtil(t);
		ArrayList<String> projectsPath = pj.getJavaProjects();
		outMap = new HashMap<Integer, Integer>();
		long start = System.currentTimeMillis();
		int count = 0;
		for (String path : projectsPath) {
			System.out.println(count + "%");

			count++;

			try {
//				visitor = new AverageMcCabeVisitor(path, outMap);

				 visitor = new LineOfCodeVisitor(path);
				output.addAll(((LineOfCodeVisitor) visitor)
						.getDetailResult());

				// visitor.getResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (select && (output.size() != 0)) {
			 XlsOutput.generateExcel2003(path.substring(15), output);
			 output.clear();
			 }
		}
		System.out.println("total :" + output.size());

		if (!select&&output.size() != 0) {
			XlsOutput.generateExcel2003("result", output);
			output.clear();
		}
		Set set = outMap.keySet();
		for (Object i : set) {

			System.out.println((Integer) i + "\t" + outMap.get(i));

		}

		long end = System.currentTimeMillis();
		System.out.println("total time : " + (end - start) + "ms");
	}
}




