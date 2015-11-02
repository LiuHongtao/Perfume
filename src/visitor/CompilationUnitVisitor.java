package visitor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WhileStatement;

import perfume.metric.analyzer.LineOfCode;
import perfume.metric.analyzer.SumJavaCode;
import perfume.metric.model.MethodParam;
import perfume.util.FileUtil;
import perfume.util.ast.JdtAstUtil;
import perfume.util.ast.JdtAstUtilP;

public class CompilationUnitVisitor extends ASTVisitor {
	private boolean isInterface = false, visited = false;
	private String unitPath, minPath,projectName;
	private CompilationUnit unit;
	private String className;
	private int fieldFragmentsCount;
	private int normerClassCount, interfaceCount, classCount;
	private int methodCount;
	private int maxTotalLine, maxNoCommentCodeLine;
	private int minCodeLine = 1000, minNoCommentCodeLine = 1000;
	private int totalNoCommentCodeLine, totalCommentLines, totalWhiteLines;
	private int noCommentCodeLine, commentLines, whiteLines, totalLines;
	private MethodParam param;
	private SumJavaCode sjc;
	private int cyclomatic = 1;
	private String source, outPut;
	private File file;
	private List<MethodParam> methodList;
	private List<List<String>> output;

	public CompilationUnitVisitor(String dirPath) throws Exception {
		super();
		projectName= dirPath.substring(8);
		output = new ArrayList<List<String>>();
		
		ArrayList<String> pathList = FileUtil.getAllJavaFilePath(dirPath);
		// System.out.println(dirPath);
		sjc = new SumJavaCode();
		for (String path : pathList) {
			
			source = "";
			unitPath = path;
			methodList = new ArrayList<MethodParam>();
			JdtAstUtilP jaup = new JdtAstUtilP();
			unit = JdtAstUtil.getCompilationUnit(unitPath);
			source = JdtAstUtil.getSource(unitPath);
			unit.accept(this);

			/*
			 * �ж��Ƿ�Ϊ�ӿ�
			 */
			if (isInterface == false) {
				// System.out.println(path);
				int[] temp = sjc.LineOfCode(JdtAstUtil.getFile(unitPath));
				noCommentCodeLine = temp[0];
				commentLines = temp[1];
				whiteLines = temp[2];
				totalLines = noCommentCodeLine + commentLines;
				getOutput();
				/*
				 * ������ͨ��Ĵ�������ͳ��
				 */
				// totalNoCommentCodeLine += noCommentCodeLine;
				// totalCommentLines += commentLines;
				// totalWhiteLines += whiteLines;

				/*
				 * ���������С���������
				 */
				// if (totalLines >= maxTotalLine) {
				// maxTotalLine = totalLines;
				// }
				// if (noCommentCodeLine >= maxNoCommentCodeLine) {
				// maxNoCommentCodeLine = noCommentCodeLine;
				// //��ʾ�����С���������ļ�λ��
				// // minPath = unitPath;
				// }
				//
				// if (totalLines <= minCodeLine) {
				// minCodeLine = totalLines;
				// }
				// if (noCommentCodeLine <= minNoCommentCodeLine) {
				// minNoCommentCodeLine = noCommentCodeLine;
				//
				// }

			}
			isInterface = false;
			visited = false;
			methodCount = 0;
			fieldFragmentsCount = 0;
			outPut = "";
			maxTotalLine = 0;
			maxNoCommentCodeLine = 0;
			minCodeLine = 1000;
			minNoCommentCodeLine = 1000;
			/*
			 * ��ʾ���������
			 */
			// System.out.println("��������: "+ temp[0]);
			// System.out.println("ע������: "+ temp[1]);
			// System.out.println("�հ�����: " + whiteLines);
			// // LOCsum = unit.getLineNumber(unit.getLength() -1) ;

		}
		/*
		 * ��ʾȫ�����������
		 */
		// System.out.println(maxTotalLine + "\t" + maxNoCommentCodeLine + "\t"
		// + minCodeLine + "\t" + minNoCommentCodeLine+"\t"+minPath);

		/*
		 * ��ʾ�����
		 */
		// System.out.println(classCount+"\t"+normerClassCount+"\t"+interfaceCount);
//		XlsOutput.generateExcel2003(projectName, output);
	}

	public boolean visit(TypeDeclaration node) {
		if (node.isInterface()) {
			isInterface = true;
			interfaceCount++;
		} else if (!visited) {
			className = node.getName().toString();
			FieldDeclaration[] fd = node.getFields();
			for (FieldDeclaration fdTemp : fd) {
				// ���Modifiers
				// int md =fdTemp.getModifiers();
				// ���Type����
				Type tp = fdTemp.getType();
				// ������е�fragments
				List fg = fdTemp.fragments();
				// ��ʾfield
				// for (Object fgTemp : fg){
				// VariableDeclarationFragment vdf =
				// (VariableDeclarationFragment) fgTemp;
				// System.out.println(md+"\t"+tp.toString()
				// +"\t"+vdf.getName().toString());
				// }
				fieldFragmentsCount += fg.size();
			}

			normerClassCount++;
			visited = true;
		}
		classCount++;
		
		return true;

	}

	public boolean visit(MethodDeclaration node) {

		methodCount++;
		param = new MethodParam();
		param.setPath(unitPath);
		param.setMethodName(node.getName().toString());
		param.setStartLineNum(unit.getLineNumber(node.getStartPosition()));
		param.setEndLineNum(unit.getLineNumber(node.getStartPosition()
				+ node.getLength()));
		int[] result = sjc.LineOfCode(JdtAstUtil.getFile(unitPath),
				param.getStartLineNum(), param.getEndLineNum());
		param.setNoCommentCodeLine(result[0]);
		param.setLineOfCodeComment(result[1]);
		methodList.add(param);

		// return super.visit(node);
		return true;
	}

	public boolean visit(CatchClause node) {
		cyclomatic++;
		return true;
	}

	public boolean visit(ConditionalExpression node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(DoStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(ForStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(IfStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(SwitchCase node) {
		if (!node.isDefault())
			cyclomatic++;
		return true;
	}

	public boolean visit(WhileStatement node) {
		cyclomatic++;
		inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(ExpressionStatement exs) {
		inspectExpression(exs.getExpression());
		return false;
	}

	/**
	 * Count occurrences of && and || (conditional and or) Fix for BUG 740253
	 * 
	 * @param ex
	 */
	private void inspectExpression(Expression ex) {
		if (ex != null) {
			int start = ex.getStartPosition();
			int end = ex.getStartPosition() + ex.getLength();
			String expression = source.substring(start, end);
			// System.out.print(expression);
			char[] chars = expression.toCharArray();
			for (int i = 0; i < chars.length - 1; i++) {
				char next = chars[i];
				if ((next == '&' || next == '|') && (next == chars[i + 1])) {

					cyclomatic++;
				}
			}
		}
	}

	public boolean preVisit2(ASTNode node) {
		if ((node instanceof MethodDeclaration) && (param != null)) {
			// param.setMethodCyclomatic(cyclomatic);
			// System.out.println(param.getMethodName()+"\t"+param.getMethodCyclomatic());
			cyclomatic = 1;
		}
		return true;

	}

	public void endVisit(MethodDeclaration node) {

		param.setMethodCyclomatic(cyclomatic);
		// System.out.println(param.getMethodName()+"\t"+param.getMethodCyclomatic());
		// cyclomatic = 1;

	}

	public void getOutput() {

		
		List<String> tempStr = new ArrayList<String>();
		tempStr.add(className);
		tempStr.add(((Integer)methodCount).toString());
		tempStr.add(((Integer)fieldFragmentsCount).toString());
		tempStr.add(((Integer)totalLines).toString());
		tempStr.add(((Integer)noCommentCodeLine).toString());
		int noCommentCodeLineSum = 0, totalLinesSum = 0, totalCyclomatic = 0, maxCyclomatic = 0, minCyclomatic = 100;

		for (MethodParam param : methodList) {
			noCommentCodeLine = param.getNoCommentCodeLine();
			totalLines = param.getLineOfCodeComment() + noCommentCodeLine;
			noCommentCodeLineSum += noCommentCodeLine;
			totalLinesSum += totalLines;
			if (totalLines >= maxTotalLine) {
				maxTotalLine = totalLines;
			}
			if (noCommentCodeLine >= maxNoCommentCodeLine) {
				maxNoCommentCodeLine = noCommentCodeLine;
			}

			if (totalLines <= minCodeLine) {
				minCodeLine = totalLines;
			}
			if (noCommentCodeLine <= minNoCommentCodeLine) {
				minNoCommentCodeLine = noCommentCodeLine;

			}
			/*
			 * ���ȫ���Ӷ�
			 */
			if (param.getMethodCyclomatic() >= maxCyclomatic)
				maxCyclomatic = param.getMethodCyclomatic();
			if (param.getMethodCyclomatic() <= minCyclomatic)
				minCyclomatic = param.getMethodCyclomatic();
			totalCyclomatic += param.getMethodCyclomatic();

		}
		tempStr.add(((Integer)totalLinesSum).toString());
		tempStr.add(((Integer)noCommentCodeLineSum).toString());
		tempStr.add("");
		tempStr.add("");
		tempStr.add(((Integer)maxTotalLine).toString());
		tempStr.add(((Integer)maxNoCommentCodeLine).toString());
		tempStr.add(((Integer)minCodeLine).toString());
		tempStr.add(((Integer)minNoCommentCodeLine).toString());
		tempStr.add(((Integer)totalCyclomatic).toString());
		tempStr.add("");
		tempStr.add(((Integer)maxCyclomatic).toString());
		tempStr.add(((Integer)minCyclomatic).toString());
		tempStr.add(unitPath);
		
		output.add(tempStr);
		
//		outPut = className + "\t";
//		// outPut += unitPath + "\t";
//		outPut += methodCount + "\t";
//		outPut += fieldFragmentsCount + "\t";
//		outPut += totalLines + "\t" + noCommentCodeLine + "\t";
//		int noCommentCodeLineSum = 0, totalLinesSum = 0, totalCyclomatic = 0, maxCyclomatic = 0, minCyclomatic = 100;
//
//		for (MethodParam param : methodList) {
//			noCommentCodeLine = param.getNoCommentCodeLine();
//			totalLines = param.getLineOfCodeComment() + noCommentCodeLine;
//			noCommentCodeLineSum += noCommentCodeLine;
//			totalLinesSum += totalLines;
//			if (totalLines >= maxTotalLine) {
//				maxTotalLine = totalLines;
//			}
//			if (noCommentCodeLine >= maxNoCommentCodeLine) {
//				maxNoCommentCodeLine = noCommentCodeLine;
//			}
//
//			if (totalLines <= minCodeLine) {
//				minCodeLine = totalLines;
//			}
//			if (noCommentCodeLine <= minNoCommentCodeLine) {
//				minNoCommentCodeLine = noCommentCodeLine;
//
//			}
//			/*
//			 * ���ȫ���Ӷ�
//			 */
//			if (param.getMethodCyclomatic() >= maxCyclomatic)
//				maxCyclomatic = param.getMethodCyclomatic();
//			if (param.getMethodCyclomatic() <= minCyclomatic)
//				minCyclomatic = param.getMethodCyclomatic();
//			totalCyclomatic += param.getMethodCyclomatic();
//
//		}
//		outPut += totalLinesSum + "\t" + noCommentCodeLineSum + "\t\t\t";
//
//		outPut += maxTotalLine + "\t" + maxNoCommentCodeLine + "\t"
//				+ minCodeLine + "\t" + minNoCommentCodeLine + "\t";
//
//		outPut += totalCyclomatic + "\t\t" + maxCyclomatic + "\t"
//				+ minCyclomatic;
//
//		System.out.println(outPut);

	}

}
