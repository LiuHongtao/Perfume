package analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class LineOfCode {
	private int[] result= new int[3];
	public int[] CountLineOfCode(String sourceText) throws InvalidInputException {
		//			String sourceText = getSource(source).trim();
		//			String sourceText = unit.getSource();
			
					final Set<Integer> lineSet = new HashSet<Integer>();
					int commentLines = 0;
					int totalLines = 0;
					IScanner scanner = ToolFactory.createScanner(true, false, true,
							true);
					scanner.setSource(sourceText.toCharArray());

					try {
						while (true) {
							
							int token = scanner.getNextToken();

							if (token == ITerminalSymbols.TokenNameEOF) {
								int startPosition = scanner
										.getCurrentTokenStartPosition();
								totalLines = scanner.getLineNumber(startPosition);
								break;
							}
		
							if ((token == ITerminalSymbols.TokenNameCOMMENT_LINE)
									|| (token == ITerminalSymbols.TokenNameCOMMENT_BLOCK)
									|| (token == ITerminalSymbols.TokenNameCOMMENT_JAVADOC)) {
								commentLines++;
							} else {
								int startPosition = scanner
										.getCurrentTokenStartPosition();
								int lineNumber = scanner
										.getLineNumber(startPosition);
								lineSet.add(new Integer(lineNumber));
							}
						}
					} catch (InvalidInputException e) {
	
						e.printStackTrace();
						
					}
//					System.out.println(lineSet.size());
//					System.out.println(commentLines);
//					System.out.println(totalLines);
					result[0]=lineSet.size();
					result[1]=commentLines;
					result[2]=totalLines;
					return result;
					
					
	}
	
	 public  String BufferedReaderDemo(String path) throws IOException{
         File file=new File(path);
         if(!file.exists()||file.isDirectory())
             throw new FileNotFoundException();
         BufferedReader br=new BufferedReader(new FileReader(file));
         String temp=null;
         StringBuffer sb=new StringBuffer();
         temp=br.readLine();
         while(temp!=null){
             sb.append(temp+"\n");
             temp=br.readLine();
         }
         
         
         return sb.toString();
     }
	
	public  LineOfCode() throws InvalidInputException{
		String a = "";
		try {
			a = BufferedReaderDemo("./src/analyzer/LineOfCode.java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CountLineOfCode(a);
		
		
			
				
	}

	
	public LineOfCode(String sourceText) throws InvalidInputException{
		CountLineOfCode(sourceText);
	}
//	private String getSource(AbstractMetricElement source)
//			throws JavaModelException {
//		ASTNode astNode = source.getASTNode();
//		int start = astNode.getStartPosition();
//		int length = astNode.getLength();
//		ICompilationUnit unit = source.getCompilationUnit();
//		String s = unit.getSource();
//		return s.substring(start, start + length);
//	}
	
}
