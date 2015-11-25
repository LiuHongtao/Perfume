package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * <ul>
 * <li>Name: NOP, number of Properties</li>
 * <li>Description: number of fields</li>
 * <li>Granularity: Class</li>
 * </ul>
 */
public class NOPMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOPMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {	
		setPkgClassName(node);
		
		long result = 0;
		FieldDeclaration[] fields = node.getFields();
		for (FieldDeclaration field: fields) {
			result += field.fragments().size();
		}
		
		NOPMap.put(getPkgClassName(), result);
		
		return true;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOPMap;
	}

}
