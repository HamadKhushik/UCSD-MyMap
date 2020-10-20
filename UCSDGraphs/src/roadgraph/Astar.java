package roadgraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;

/**
 * @author Hammad
 * for A* search in the map
 */
public class Astar {
	
	final Double infinity;			// constant infinity
	private Map<GeographicPoint, MapNode> vertices;
	
	public Astar(HashMap<GeographicPoint, MapNode> locations) {
		infinity = Double.POSITIVE_INFINITY;
		vertices = locations;			// Map of locations and corresponding MapNodes

	}
	
	public Map<MapNode, MapNode> aStarSearchTripDuration(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms - visualization tool
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearchTripDuration(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public Map<MapNode, MapNode> aStarSearchTripDuration(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)	{
		
		// set distance to infinity for all nodes
		setTimeInfinity();
		
		// Initialization
		MapNode startNode = vertices.get(start);
		MapNode goalNode = vertices.get(goal);
		// set initial distance to 0
		startNode.setStartTime(0.0);
		startNode.setGoalTime(startNode.approxTimeToGoal(goalNode));		// set the estimated time to goal for startNode
		Map<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();
		
		// A* search
		boolean found = aSearch(startNode, goalNode, parentMap, nodeSearched);
		
		// if path not found, return null
		if (!found) {														
			return null; 
		}
		
		return parentMap;
	}
	
	public boolean aSearch(MapNode startNode, MapNode goalNode, Map<MapNode, MapNode> parentMap, Consumer<GeographicPoint> nodeSearched) {

		NodeTimeComparator comparator = new NodeTimeComparator();					// function for the priority queue
		PriorityQueue<MapNode> pQueue = new PriorityQueue<MapNode>(11, comparator);
		Set<MapNode> visited = new HashSet<MapNode>();
		boolean found = false;
		int i = 0;

		pQueue.add(startNode);
		while (!pQueue.isEmpty()) {

			MapNode curr = pQueue.poll();
			i++;			// to count the number of nodes explored

			// Hook for visualization.  See writeup.
			nodeSearched.accept(curr.getLocation());

			if (!visited.contains(curr)) {
				visited.add(curr);

				if (curr.equals(goalNode)) {
					found = true;
					break;
				}

				List<MapEdge> neighbors = curr.getEdges();
				ListIterator<MapEdge> it = neighbors.listIterator(neighbors.size());
				while (it.hasPrevious()) {
					MapEdge neighborEdge = it.previous();
					MapNode neighborNode = vertices.get(neighborEdge.getEndVertex());
					Double edgeTime = neighborEdge.getEdgeTime();													// edge time = time to cover edge distance
					Double timeFromStart = edgeTime + curr.getStartTime();
					neighborNode.setGoalTime(neighborNode.approxTimeToGoal(goalNode));								// update estimated time to goal
					Double timeToGoal = neighborNode.getGoalTime();												// 
					Double totalTime = timeFromStart + timeToGoal;													// update total distance

					if (!visited.contains(neighborNode)) {
						// if total distance is less than node's current distance
						if (totalTime < (neighborNode.getStartTime() + neighborNode.getGoalTime())) {
							neighborNode.setStartTime(timeFromStart);
							parentMap.put(neighborNode, curr);
							pQueue.add(neighborNode);
						}
					}
				}
			}
		}
		System.out.println("No of Nodes searched in A* Trip Duration = " + i);
		return found;		
	}
	
	public void setTimeInfinity() {
		
		for (MapNode n : vertices.values()) {
			n.setStartTime(infinity);
			n.setGoalTime(infinity);
		}
	}
}
