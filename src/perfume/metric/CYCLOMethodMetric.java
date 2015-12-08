package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTNode;
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

import perfume.metric.model.MethodParam;
import perfume.metric.visitor.CYCLOVisitor;
import perfume.util.StringUtil;
import perfume.util.ast.JdtAstUtil;

public class CYCLOMethodMetric extends AbstractMetricVisitor {

	private HashMap<String, Long> CYCLOMethodMetric = new HashMap<>();
	private String javaPath;
	private CompilationUnit compUnit;
	private long cyclomatic = 1;
	private String source;
	private MethodParam param;
	private CYCLOVisitor cycloVisitor;
	public boolean visit(TypeDeclaration node){
		setPkgClassName(node);
		MethodDeclaration[] mds = node.getMethods();
		for (MethodDeclaration md : mds) {
			String methodName = getPkgClassName()+"."+md.getName().toString();
//			
			try{
				md.accept(cycloVisitor);
				cyclomatic = cycloVisitor.getMetricResult();
				
				CYCLOMethodMetric.put(methodName, cyclomatic);
			}catch(Exception e){
				System.out.println(node.getName().toString()+" "+methodName);
				System.out.println(CYCLOMethodMetric);
				e.printStackTrace();
			}
			
			
		}
//		getMetricResult();
		return true;
		
	}
	@Override
	public void beforeMetric(String javaPath, CompilationUnit compUnit) {
		super.beforeMetric(javaPath, compUnit);
		this.javaPath = javaPath;
		
		this.compUnit = compUnit;
		cycloVisitor = new CYCLOVisitor();
		cycloVisitor.beforeMetric(javaPath, compUnit);
	}

	@Override
	public void afterMetric() {
		source = "";
		cyclomatic = 1;
	}

	@Override
	public HashMap<String, Long> getMetricResult() {

		return CYCLOMethodMetric;
	}

	@Override
	public String getMetricName() {
		return "CYCLO";
	}

}

