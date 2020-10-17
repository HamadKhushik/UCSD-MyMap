package roadgraph;

import java.util.Comparator;

/**
 * @author Hammad
 * this class provides the comparator function for the priority queue in MapGraph class - specifically for A* search
 * it compares two MapNode objects, depending on the total distane of the node from starting point and goal point
 */
/**
 * @author Hammad
 * compares two MapNode objets depending on their distance from starting location to goal location
 */
public class NodeDistComparator implements Comparator<MapNode>{

	@Override
	public int compare(MapNode node1, MapNode node2) {
		// TODO Auto-generated method stub
		// compare the total distance of the two nodes and return
		
		Double distance1 = node1.getStartDistance() + node1.getGoalDistance();
		Double distance2 = node2.getStartDistance() + node2.getGoalDistance();
		
		if (distance1 < distance2)	return -1;
		
		else if (distance1 > distance2) return 1;
		
		return 0;
	}

}
