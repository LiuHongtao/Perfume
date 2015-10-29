package visitor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.internal.utils.Convert;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;

import analyzer.SumJavaCode;
import util.FileUtil;
import util.JdtAstUtil;
import dto.MethodParam;

public class AverageMcCabeVisitor extends ASTVisitor {

	private String unitPath;
	private CompilationUnit unit;
	private SumJavaCode sjc;
	private int cyclomatic = 1;
	private String source;
	private File file;
	private HashMap<Integer, Integer> outMap;
	private MethodParam param;
	private HashMap<Integer, ArrayList<String>> cyclomaticCounter = new HashMap<Integer, ArrayList<String>>();
	private ArrayList<List<String>> output;
	private boolean isInterface;

	public AverageMcCabeVisitor(String dirPath, HashMap<Integer, Integer> outMap)
			throws Exception {
		super();
		this.outMap = outMap;
		FileUtil util = new FileUtil();
		ArrayList<String> pathList = util.getAllJavaFilePath(dirPath);
		sjc = new SumJavaCode();
		output = new ArrayList<List<String>>();
		for (String path : pathList) {
			// System.out.println(path);
			unitPath = path;
			unit = JdtAstUtil.getCompilationUnit(unitPath);
			source = JdtAstUtil.getSource(unitPath);
			// source = unit.getSource();
			unit.accept(this);

			// System.out.println(cyclomatic);

			source = "";
		}
	}

	public boolean visit(TypeDeclaration node) {
		MethodDeclaration[] mds = node.getMethods();
		for (MethodDeclaration md : mds) {
			md.accept(new MethodVisitor(md));
			param.setMethodCyclomatic(cyclomatic);
			Integer cyc = cyclomatic;
			double codeLine = param.getEndLineNum() - param.getStartLineNum();
			double rusult = (cyc / (double) param.getNoCommentCodeLine());
			// double rusult = (cyc / (double) codeLine);
//			int hehe = (int) (rusult * 100);
			int hehe = cyclomatic; 
			if(cyclomatic ==1245){
				System.out.println(node.getName().getIdentifier()+" "+unitPath);
			}
			if (cyclomatic != 1) {
				if (outMap.containsKey(hehe)) {
					Integer tempInt = outMap.get(hehe);
					tempInt = tempInt + 1;
					outMap.put(hehe, tempInt);
				} else {
					outMap.put(hehe, (Integer) 1);
				}
				String s = rusult + "";
				List<String> tempStr = new ArrayList<String>();
				tempStr.add(param.getMethodName());
				tempStr.add(((Integer) cyclomatic).toString());
				tempStr.add(((Integer) param.getNoCommentCodeLine()).toString());
				tempStr.add(s);
				tempStr.add(param.getPath());
				
				output.add(tempStr);
				
			}
			
		}

		return true;

	}

	class MethodVisitor extends ASTVisitor {
		private MethodDeclaration node;

		public MethodVisitor(MethodDeclaration node) {
			cyclomatic = 1;
			param = new MethodParam();
			param.setPath(unitPath);
			param.setMethodName(node.getName().toString());
			param.setStartLineNum(unit.getLineNumber(node.getStartPosition()));
			param.setEndLineNum(unit.getLineNumber(node.getStartPosition()
					+ node.getLength()));
			int[] result = sjc.LineOfCode(JdtAstUtil.getFile(unitPath),
					param.getStartLineNum(), param.getEndLineNum());
			param.setNoCommentCodeLine(result[0]);
			// param.setNoCommentCodeLine(param.getEndLineNum()
			// - param.getStartLineNum()+1);
			param.setLineOfCodeComment(result[1]);

		}

		public boolean visit(TypeDeclaration node) {

			isInterface = node.isInterface();

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

		public int getCyclomatic() {
			return cyclomatic;
		}

		/**
		 * Count occurrences of && and || (conditional and or) Fix for BUG
		 * 740253
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

		
	}

	public ArrayList<List<String>> getDetailResult() {
		return output;

	}

}
