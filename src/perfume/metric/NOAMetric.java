package perfume.metric;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * <ul>
 * <li>Name: NOA, Number Of Attributes</li>
 * <li>Description: number of fields or properties</li>
 * <li>Granularity: Class</li>
 * </ul>
 */
public class NOAMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOAMap = new HashMap<>();
	
	@Override
	public boolean visit(TypeDeclaration node) {	
		setPkgClassName(node);
		
		long result = 0;
		FieldDeclaration[] fields = node.getFields();
		for (FieldDeclaration field: fields) {
			result += field.fragments().size();
		}
		
		NOAMap.put(getPkgClassName(), result);
		
		return true;
	}
	
	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOAMap;
	}

}
