package roadgraph;

import java.util.Comparator;

/**
 * @author Hammad
 * to compare two MapNode objects for Dijkstra search wrt trip duration
 */
public class DijkstraTimeComparator implements Comparator<MapNode>{
	
	public int compare(MapNode node1, MapNode node2) {
		
		if (node1.getStartTime() == null || node2.getStartTime() == null) {
			System.out.println("Start time is null");
			return 0;
		}
		
		if (node1.getStartTime() < node2.getStartTime()) return -1;
		else if (node1.getStartTime() > node2.getStartTime()) return 1;
		else return 0;
		
	}
}
