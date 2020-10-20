package roadgraph;

import java.util.Comparator;

/**
 * @author Hammad
 * compare two MapNode objects wrt time duration of the trip
 */
public class NodeTimeComparator implements Comparator<MapNode> {
	
	/**
	 * compares two MapNode objects wrt toal time taken for the trip duration
	 */
	@Override
	public int compare(MapNode node1, MapNode node2) {
		
		Double time1 = node1.getStartTime() + node1.getGoalTime();
		Double time2 = node2.getStartTime() + node2.getGoalTime();
		
		if (time1 < time2) return -1;
		else if (time1 > time2) return 1;
		else return 0;
	}

}
