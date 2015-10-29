package main;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import analyzer.LineOfCode;
import test.SingleFileTest;
import test.SingleProjectTest;
import test.ProjectUtilTest;

public class Entrance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			new MethodCounterTest();
//			new SingleProjectTest();
			new ProjectUtilTest();
//			new SingleFileTest();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			new LineOfCode();
//		} catch (InvalidInputException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
