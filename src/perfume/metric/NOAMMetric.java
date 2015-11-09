package perfume.metric;

import java.util.HashMap;

public class NOAMMetric extends AbstractMetricVisitor {
	private HashMap<String, Long> NOAMMap = new HashMap<>();

	@Override
	public HashMap<String, Long> getMetricResult() {
		return NOAMMap;
	}

}
