/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	
	private HashMap<GeographicPoint, MapNode> vertices;			// map of GeographicPoints and MapNodes
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		vertices = new HashMap<GeographicPoint, MapNode>();
		
	}
	
	public HashSet<MapEdge> getEdges(){
		
		HashSet<MapEdge> edges = new HashSet<MapEdge>();
		for (MapNode k : vertices.values()) {
			List<MapEdge> currEdges = k.getEdges();
			for (MapEdge i : currEdges) {
				if (!edges.contains(i)) {
					edges.add(i);
				}
			}
		}
		return edges;
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return vertices.size();						// return the size of vertices
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVerticesSet()
	{
		//TODO: Implement this method in WEEK 3
		return new HashSet<GeographicPoint>(vertices.keySet());					// create and return HashSet of all the GeographicPoints in the map of vertices
	}
	
	public Map<GeographicPoint, MapNode> getVerticesMap(){
		return new HashMap<GeographicPoint, MapNode>(vertices);
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		int numEdges = 0;
		
		// loop through the vertices 
		for (MapNode k : vertices.values()) {
			numEdges += k.getEdges().size();											// add the edges of each vertex in numEdges
		}
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		
		if (vertices.containsKey(location) || location == null) {
			return false;
		}
		
		vertices.put(location, new MapNode(location));						// create a new MapNode object with the given location and add it to vertices(map of vertices)
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		
		if (from == null || to == null || roadName == null || roadType == null) {
			throw new IllegalArgumentException ("atleast one of the parameter for the edge is null");
		}
		
		//MapEdge newEdge = new MapEdge(from, to, roadName, roadType, length);	// create a MapEdge object with given parameters
		MapNode node = vertices.get(from);										// get the corresponding node from vertices (map of GeogrphicPoint and MapNodes)
		//node.edges.add(newEdge);												// add the newly created edge to the corresponding node
		node.addEdge(from, to, roadName, roadType, length);
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// initializtion
		MapNode startNode = vertices.get(start);							// create two new MapNode objects corresponding to start and goal
		MapNode endNode = vertices.get(goal);								// 
		Map<MapNode, MapNode> parentMap = new HashMap<MapNode,MapNode >();  // updates shortest path between nodes
		
		if (start == null || goal == null) {								// if any of the start or end point is null, return
			System.out.println("Start or goal is null, no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		// BFS search
		boolean found = bfsSearch(startNode, endNode, parentMap, nodeSearched);
		
		
		
		if (!found) {														// if path not found, return null
			System.out.println("Path not found");
			return null; //new LinkedList<GeographicPoint>();
		}
		
		LinkedList<GeographicPoint> path = constructPath(startNode, endNode, parentMap);
		return path;
	}
	
	// Construct Path
	/** constructs the path with the help of Parent Map(which stores shortest path between nodes)
	 * @param startNode
	 * @param endNode
	 * @param parentMap
	 * @return
	 */
	private LinkedList<GeographicPoint> constructPath(MapNode startNode, MapNode endNode, Map<MapNode, MapNode> parentMap){
		
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();			// create a new LinkedList to store the path to goal
		// path is from endNode/goalNode to startNode i-e start the path from goalNode and keep adding nodes infront of it until startNode
		MapNode curr = endNode;															// create a MapNode corresponding to endNode/goalNode
		//System.out.println("Construt Path: " + startNode.getVertex() + " : goal : " + curr.getVertex());
		while (!curr.equals(startNode)){
			path.addFirst(curr.getLocation());
			curr = parentMap.get(curr);
		}
		
		path.addFirst(startNode.getLocation());		
		return path;	
	}
	
	// BFS search
	/** performs the BFS search and returns true if goal found
	 * @param startNode
	 * @param endNode
	 * @param parentMap : keeps track of shortest path between nodes
	 * @param nodeSearched : visualization tool
	 * @return true if found, false otherwise
	 */
	private boolean bfsSearch(MapNode startNode, MapNode endNode, Map<MapNode, MapNode> parentMap, Consumer<GeographicPoint> nodeSearched){
		
		Queue<MapNode> queue = new LinkedList<MapNode>();
		Set<MapNode> visited = new HashSet<MapNode>();
		boolean found = false;
		
		//BFS search
		queue.add(startNode);												// add startNode to queue
		visited.add(startNode);												// add startNode to visited set
		
		while (!queue.isEmpty()) {
			MapNode curr = queue.remove();									// remove an element from queue to be explored
			
			// Hook for visualization.  See writeup.
			nodeSearched.accept(curr.getLocation());
			
			if (curr == endNode) {											// if curr is the goal node, break from loop
				found = true;
				break;
			}
			
			List<MapEdge> neighbors = new LinkedList<MapEdge>(curr.getEdges());			// create a new list of neighbors of curr
			ListIterator<MapEdge> it = neighbors.listIterator(neighbors.size());		// iterate through neighbors
			while (it.hasPrevious()) {
				MapEdge next = it.previous();											// create a MapEdge object from iterator - not necessary but helps readability
				MapNode nextNode = vertices.get(next.getEndVertex());					// get the MapNode for next
				if (!visited.contains(nextNode)) {										// if nextNode is not in the visited set
					visited.add(nextNode);												// add next to visited
					parentMap.put(nextNode, curr);										// map nextNode to curr Node
					queue.add(nextNode);												// add nextNode to queue
					
				}
			}
		}
		System.out.println("bfs " + parentMap);
		return found;			
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        //System.out.println("Start comsumer " + start);
        return dijkstra(start, goal, temp);

	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		
		if (start == null || goal == null) {
			//throw new NullPointerException("start point or goal is invalid ");
			System.out.println("start or goal location is invalid");
			return null;
		}
		
		// set distance to infinity for all nodes
		setDistanceInfinity();
		
		// Initialization
		MapNode startNode = vertices.get(start);
		// set initial distance to 0
		//System.out.println("Start Node " + startNode + start);
		startNode.setStartDistance(0.0);
		MapNode goalNode = vertices.get(goal);
		Map<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();
		
		if (!checkValidity(start, goal)) {
			System.out.println("Start or goal is null, no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		// Dijkstra search
		boolean found = dijkstraSearch(startNode, goalNode, parentMap, nodeSearched);
		
		if (!found) {														// if path not found, return null
			System.out.println("Path not found");
			return null; //new LinkedList<GeographicPoint>();	
		}
		
		// constructPath
		LinkedList<GeographicPoint> path = constructPath(startNode, goalNode, parentMap);
		
		return path;
	}
	
	private boolean dijkstraSearch(MapNode startNode, MapNode goalNode, Map<MapNode, MapNode> parentMap, Consumer<GeographicPoint> nodeSearched){

		PriorityQueue<MapNode> pQueue = new PriorityQueue<MapNode>();
		Set<MapNode> visited = new HashSet<MapNode>();
		boolean found = false;
		int i = 0;					// to check the number of nodes explored

		pQueue.add(startNode);
		
		while (!pQueue.isEmpty()) {
			//System.out.println("-------------------------------------");
			i++;
			
			MapNode curr = pQueue.poll();

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
				while (it.hasPrevious()) {														// iterate through neighbors
					MapEdge neighborEdge = it.previous();
					MapNode neighborNode = vertices.get(neighborEdge.getEndVertex());
					Double edgeLength = neighborEdge.getEdgeLength();							// edge length
					Double distFromStart = edgeLength + curr.getStartDistance();				// edge length + distance to current node

					if (!visited.contains(neighborNode)) {
						// if total distance is less than neighbor Node's current distance
						if (distFromStart < neighborNode.getStartDistance()) {
							neighborNode.setStartDistance(distFromStart);
							parentMap.put(neighborNode, curr);
							pQueue.add(neighborNode);
						}
					}
				}
			}
		}
		//System.out.println("No of Nodes searched in Dijkstra = " + i);
		return found;
	}
	
	/**
	 * @param start: start location
	 * @param goal: goal location
	 * @return if both locations are valid
	 */
	private boolean checkValidity(GeographicPoint start, GeographicPoint goal) {
		
		if (start == null || goal == null) {
			return false;
		}
		else return true;
	}
	
	/**
	 *  set the MapNode distances to infinity for all nodes
	 */
	private void setDistanceInfinity() {
		
		for (MapNode n : vertices.values()) {
			n.setStartDistance(Double.POSITIVE_INFINITY);
			n.setGoalDistance(Double.POSITIVE_INFINITY);
		}
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		
		// set distance to infinity for all nodes
		setDistanceInfinity();
		
		// Initialization
		new HashMap<GeographicPoint, MapNode>(vertices);
		MapNode startNode = vertices.get(start);
		MapNode goalNode = vertices.get(goal);
		// set initial distance to 0
		startNode.setStartDistance(0.0);
		startNode.setGoalDistance(startNode.getLocation().distance(goalNode.getLocation()));		// calculate and set goal distance for start node
		Map<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();


		if (!checkValidity(start, goal)) {
			System.out.println("Start or goal is null, no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		// A* search
		boolean found = aSearch(startNode, goalNode, parentMap, nodeSearched);
		
		if (!found) {														// if path not found, return null
			System.out.println("Path not found");
			return null; //new LinkedList<GeographicPoint>();
		}

		// constructPath
		LinkedList<GeographicPoint> path = constructPath(startNode, goalNode, parentMap);
		return path;
	}
	
	public boolean aSearch(MapNode startNode, MapNode goalNode, Map<MapNode, MapNode> parentMap, Consumer<GeographicPoint> nodeSearched) {

		NodeDistComparator comparator = new NodeDistComparator();					// function for the priority queue
		PriorityQueue<MapNode> pQueue = new PriorityQueue<MapNode>(11, comparator);
		Set<MapNode> visited = new HashSet<MapNode>();
		boolean found = false;
		int i = 0;

		pQueue.add(startNode);
		while (!pQueue.isEmpty()) {
			//System.out.println("-------------------------------------");
			MapNode curr = pQueue.poll();
			i++;

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
					Double edgeLength = neighborEdge.getEdgeLength();							// edge length
					Double distFromStart = edgeLength + curr.getStartDistance();
					neighborNode.setGoalDistance(neighborNode.getLocation().distance(goalNode.getLocation()));		// update goal distance to node
					Double distToGoal = neighborNode.getGoalDistance();												// 
					Double totalDist = distFromStart + distToGoal;													// update total distance

					if (!visited.contains(neighborNode)) {
						// if total distance is less than node's current distance
						if (totalDist < (neighborNode.getStartDistance() + neighborNode.getGoalDistance())) {
							neighborNode.setStartDistance(distFromStart);
							parentMap.put(neighborNode, curr);
							pQueue.add(neighborNode);
						}
					}
				}
			}
		}
		System.out.println("No of Nodes searched in A* = " + i);
		return found;		
	}
	
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstraTripDuration(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstraTripDuration(start, goal, temp);
	}
	
	
	/**
	 * @param start: start Location
	 * @param goal: goal Location
	 * @param nodeSearched: visualization Hook
	 * @return
	 */
	public List<GeographicPoint> dijkstraTripDuration(GeographicPoint start, 
			  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched){
		
		// set distance to infinity for all nodes
		setTimeInfinityDijkstra();

		// Initialization
		MapNode startNode = vertices.get(start);
		// set initial distance to 0
		startNode.setStartTime(0.0);
		MapNode goalNode = vertices.get(goal);
		Map<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();

		if (!checkValidity(start, goal)) {
			System.out.println("Start or goal is null, no path exists");
			return new LinkedList<GeographicPoint>();
		}

		// Dijkstra search
		boolean found = dijkstraSearchTripDuration(startNode, goalNode, parentMap, nodeSearched);

		if (!found) {														// if path not found, return null
			System.out.println("Path not found");
			return null; //new LinkedList<GeographicPoint>();	
		}

		// constructPath
		LinkedList<GeographicPoint> path = constructPath(startNode, goalNode, parentMap);

		return path;
	}
	
	/**
	 * set the start time of every MapNode to infinity for Dijkstra search wrt Trip duration
	 * and sets the goal time of every node 0
	 */
	private void setTimeInfinityDijkstra() {
		
		for (MapNode k : vertices.values()) {
			k.setStartTime(Double.POSITIVE_INFINITY);
			k.setGoalTime(0.0);
		}
	}
	
	/**
	 * perform Dijkstra serach wrt trip duration
	 * @param startNode
	 * @param goalNode
	 * @param parentMap
	 * @param nodeSearched
	 * @return
	 */
	private boolean dijkstraSearchTripDuration(MapNode startNode, MapNode goalNode, Map<MapNode, MapNode> parentMap, Consumer<GeographicPoint> nodeSearched){

		NodeTimeComparator comparator = new NodeTimeComparator();
		PriorityQueue<MapNode> pQueue = new PriorityQueue<MapNode>(11, comparator);
		Set<MapNode> visited = new HashSet<MapNode>();
		boolean found = false;
		int i = 0;					// to check the number of nodes explored

		pQueue.add(startNode);
		
		while (!pQueue.isEmpty()) {
			//System.out.println("-------------------------------------");
			i++;
			
			MapNode curr = pQueue.poll();
//			System.out.println("========================");
//			System.out.println("curr Node " + curr);
//			System.out.println("Start Time: " + curr.getStartTime());

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
				while (it.hasPrevious()) {														// iterate through neighbors
					MapEdge neighborEdge = it.previous();
					MapNode neighborNode = vertices.get(neighborEdge.getEndVertex());
					Double edgeTime = neighborEdge.getEdgeTime();								// edge time
					Double timeFromStart = edgeTime + curr.getStartTime();						// edge time + time duration to current node		

					if (!visited.contains(neighborNode)) {
						// if total time is less than neighbor Node's current time
						if (timeFromStart < neighborNode.getStartTime()) {
							neighborNode.setStartTime(timeFromStart);
							parentMap.put(neighborNode, curr);
							pQueue.add(neighborNode);
						}
					}
				}
			}
		}
		System.out.println("No of Nodes searched in Dijkstra Trip Duration = " + i);
		return found;
	}
	
	public List<GeographicPoint> aStarSearchTripDuration(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearchTripDuration(start, goal, temp);
	}
	
	/**
	 * performs the A* search with respect to trip duration
	 * @param start
	 * @param goal
	 * @param nodeSearched
	 * @return path
	 */
	public List<GeographicPoint> aStarSearchTripDuration(GeographicPoint start, 
			 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched){
		
		// set distance to infinity for all nodes
		setTimeInfinityDijkstra();

		if (!checkValidity(start, goal)) {
			System.out.println("Start or goal is null, no path exists");
			return new LinkedList<GeographicPoint>();
		}
		
		// A* search
		Astar aStar = new Astar(vertices);
		Map<MapNode, MapNode> parentMap = aStar.aStarSearchTripDuration(start, goal);				// call the method in A* class
		
		// if path not found, return null
		if (parentMap == null) {														
			System.out.println("Path not found");
			return null; 
		}

		// constructPath
		MapNode startNode = new MapNode(start);
		MapNode goalNode = new MapNode(goal);
		LinkedList<GeographicPoint> path = constructPath(startNode, goalNode, parentMap);
		return path;
	}
	
	/**
	 * solves the TSP(Trvelling sales person) problem by the greedy algorithm
	 * finds the next shortest path and takes that route and return to starting point at the end
	 * @param toVisit = set of locations to visit
	 * @return
	 */
	public List<GeographicPoint> tspGreedy(Set<GeographicPoint> toVisit){
		
		// Check Validity
		if (!checkTspValidity(toVisit)) {
			System.out.println("No valid Location");
			return null;
		}
		
		// Initialize
		List<GeographicPoint> bestPath = new LinkedList<GeographicPoint>();			// overall best path - the actual path, contains vertices which might not be in toVisit(set)
		List<GeographicPoint> sequence = new ArrayList<GeographicPoint>();			// retains the sequence of vertices visited in the greedy algorithm. contains only vertices passed to the method(toVisit)
		LinkedList<GeographicPoint> list = new LinkedList<GeographicPoint>(toVisit);		

		GeographicPoint start = list.remove();										// starting location, and the location to return to
		sequence.add(start);														// sequence of next closest vertex in the set
		bestPath.add(start);
		
		// TSP algorithm
		while (!list.isEmpty()) {													// iterate through the vertices/locations

			Queue<GeographicPoint> innerQ = new LinkedList<GeographicPoint>(list);
			Map<GeographicPoint, List<GeographicPoint>> closestLocation = greedySearch(start, innerQ); 
			start = getClosestLocation(closestLocation);
			sequence.add(start);
			bestPath.addAll(getClosestPath(closestLocation));						// add the path return by greedy search to bestPath
			list.remove(getClosestLocation(closestLocation));						// remove the closest Location returned by greedy search from the list
			

		}		
		sequence.add(sequence.get(0));												// add the starting position to complete TSP journey
		//System.out.println("Sequence "+ sequence);
		bestPath.addAll(returnToStart(start, sequence.get(0)));						// add the path from goal to start location
		return bestPath;
	}
	
	/**
	 * perform greedy search on a number of locations and return the nearest location to the start point 
	 * @return
	 */
	private Map<GeographicPoint, List<GeographicPoint>> greedySearch(GeographicPoint start, Queue<GeographicPoint> toSearch) {
		
		// Initialization
		Map<GeographicPoint, List<GeographicPoint>> result = new HashMap<GeographicPoint, List<GeographicPoint>>();		// return variable, returns location and pth to that location from start point
		List<GeographicPoint> currPath = new LinkedList<GeographicPoint>();
		List<GeographicPoint> path = new LinkedList<GeographicPoint>();
		Double bestDistance = Double.POSITIVE_INFINITY;
		GeographicPoint nearest = null;
		
		// Search
		while (!toSearch.isEmpty()) {
			GeographicPoint next = toSearch.remove();
			currPath = dijkstra(start, next);
			Double currDist = calculateDistance(currPath);
			if (currDist < bestDistance) {												// update distance, path and next
				bestDistance = currDist;
				path = currPath;
				nearest = next;
			}
		}
		path.remove(0);																	// to avoid duplicates of starting position in path
		result.put(nearest, path);
		return result;
	}
	
	/**
	 * finds the path from goal to start (return to hometown) and returns
	 * removes the first vertex in returnPath to avoid duplication
	 * @param goal
	 * @param start
	 * @return
	 */
	private List<GeographicPoint> returnToStart(GeographicPoint goal, GeographicPoint start){
		
		List<GeographicPoint> returnPath = dijkstra(goal, start);
		returnPath.remove(0);
		return returnPath;
	}
	
	/**
	 * check if all the locations are valid in the set
	 * @param toVisit
	 * @return
	 */
	public boolean checkTspValidity(Set<GeographicPoint> toVisit) {
		
		while (toVisit.contains(null)) {
			toVisit.remove(null);														// remove all the null elements
		}
		if (toVisit.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * return the location of nearest vertex
	 * @param map
	 * @return
	 */
	private GeographicPoint getClosestLocation(Map<GeographicPoint, List<GeographicPoint>> map) {
		
		for (GeographicPoint k : map.keySet()) {
			return k;
		}
		return null;
	}
	
	/**
	 * returns the path to the nearest vertex
	 * @param map
	 * @return
	 */
	private List<GeographicPoint> getClosestPath(Map<GeographicPoint, List<GeographicPoint>> map){
		
		for (GeographicPoint k : map.keySet()) {
			return map.get(k);
		}
		return null;
	}
	
	/**
	 * calculates the distance in a particular path
	 * @param path 
	 * @return distance in Double
	 */
	private Double calculateDistance(List<GeographicPoint> path) {
		
		ListIterator<GeographicPoint> it = path.listIterator();
		Double dist = 0.0;
		GeographicPoint first = ((LinkedList<GeographicPoint>) path).getFirst();
		while (it.hasNext()) {
			GeographicPoint curr = it.next();
			if (!curr.equals(first)){
				dist += first.distance(curr);
			}
			first = curr;
		}
		return dist;
	}
	
	
	/** greedy algorithm with 2opt solution
	 * @param toVisit
	 * @return
	 */
	public List<GeographicPoint> twoOpt(Set<GeographicPoint> toVisit){
		
		// Check Validity
		if (!checkTspValidity(toVisit)) {
			System.out.println("Invalid Cities");
			return null;
		}
		
		// Initialization
		List<GeographicPoint> path = tspGreedy(toVisit);
		List<GeographicPoint> sequence = getSequence(toVisit, path);
		List<GeographicPoint> newSequence = new LinkedList<GeographicPoint>(sequence);
		Double distance = calculateDistance(sequence);
		Double currDist = Double.POSITIVE_INFINITY;
		int comparisions = 0;
		int counter = 10;
		int improve = 0;
		int swaps = 0;
		
		// 2opt swaps
		while (counter != 0) {
			for (int i = 1; i < toVisit.size()-2; i++) {
				for (int j = i + 1; j < toVisit.size()-1; j++) {
					comparisions++;
					//					
					//					List<GeographicPoint> tempPath1 = dijkstra(sequence.get(i-1), sequence.get(i));
					//					tempPath1.addAll(dijkstra(sequence.get(j), sequence.get(+1)));
					//					Double tempDist1 = calculateDistance(tempPath1);
					//					
					//					List<GeographicPoint> tempPath2 = dijkstra(sequence.get(i-1), sequence.get(j));
					//					tempPath2.addAll(dijkstra(sequence.get(i), sequence.get(j+1)));
					//					Double tempDist2 = calculateDistance(tempPath2);
					//					
					//					if (tempDist1 > tempDist2) {
					//						Collections.swap(sequence, i, j);
					//				}
					Double dist1 = sequence.get(i-1).distance(sequence.get(i));
					Double dist2 = sequence.get(j+1).distance(sequence.get(j));
					if (dist2 < dist1) {
						Collections.swap(sequence, i, j);
						currDist = calculateDistance(sequence);
						swaps++;
					}

					if (currDist < distance) {
						distance = currDist;
						newSequence = sequence;
						improve++;
					}
				}
			}
			counter--;
		}
		System.out.println("comparisions: " + comparisions + " improves: " + improve + " swaps: " + swaps);
		return twoOptPath(newSequence);
	}
	
	/** returns the sequence/order in which vertices/cities are visited(returned by greedy algorithm) in TSP problem
	 * @param toVisit
	 * @return
	 */
	private List<GeographicPoint> getSequence(Set<GeographicPoint> toVisit, List<GeographicPoint> path){
		
		List<GeographicPoint> sequence = new LinkedList<GeographicPoint>();
		ListIterator<GeographicPoint> it = path.listIterator();
		while (it.hasNext()) {
			GeographicPoint curr = it.next();
			if (toVisit.contains(curr)) {
				sequence.add(curr);
			}
		}
		
		return sequence;
	}
	
	/** generates the path for the two Opt solution
	 * @param sequence
	 * @return
	 */
	private List<GeographicPoint> twoOptPath(List<GeographicPoint> sequence){
		
		List<GeographicPoint> path = new LinkedList<GeographicPoint>();
		ListIterator<GeographicPoint> it = sequence.listIterator();
		GeographicPoint start = it.next();
		path.add(start);
		
		while (it.hasNext()) {
			GeographicPoint next = it.next();
			List<GeographicPoint> currPath = dijkstra(start, next);
			currPath.remove(0);
			path.addAll(currPath);
			start = next;
		}
		return path;
	}
	
	
	public static void main(String[] args)
	{
		// Initial test
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
//		MapGraph simpleTestMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
//
//		GeographicPoint testStart = new GeographicPoint(1.0, 1.0); 
//		GeographicPoint	testEnd = new GeographicPoint(8.0, -1.0);
//
//		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5"); 
//		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
//		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
//		
		
		
		/*
		 * MapGraph testMap = new MapGraph();
		 * GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		 * 
		 * // A very simple test using real data testStart = new
		 * GeographicPoint(32.869423, -117.220917); testEnd = new
		 * GeographicPoint(32.869255, -117.216927); System.out.
		 * println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		 * testroute = testMap.dijkstra(testStart,testEnd); testroute2 =
		 * testMap.aStarSearch(testStart,testEnd);
		 * 
		 * 
		 * // A slightly more complex test using real data testStart = new
		 * GeographicPoint(32.8674388, -117.2190213); testEnd = new
		 * GeographicPoint(32.8697828, -117.2244506); System.out.
		 * println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		 * testroute = testMap.dijkstra(testStart,testEnd); testroute2 =
		 * testMap.aStarSearch(testStart,testEnd);
		 */
		 
		
		
		
		// Use this code in Week 3 End of Week Quiz 
		//MapGraph theMap = new MapGraph();
		//System.out.print("DONE. \nLoading the map...");
		//GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		//System.out.println("DONE.");

		//GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		//GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		//List<GeographicPoint> route = theMap.dijkstra(start,end);
		//List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		// various tests
//		GeographicPoint start = new GeographicPoint(1.0, 1.0);
//		GeographicPoint end = new GeographicPoint(8, -1);
//		MapGraph secondMap = new MapGraph();
//		System.out.print("DONE. \nLoading the map...");
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", secondMap);
//		System.out.println("DONE.");
//		List<GeographicPoint> res = secondMap.dijkstra(start, end);
//		System.out.println("Dijkstra: " + res);
		//List<GeographicPoint> resultBfs = secondMap.bfs(start, end);
		//GeographicPoint test = null;
		//System.out.println(firstMap.getNumVertices() + " num edges- " + firstMap.getNumEdges());
		//List<GeographicPoint> result = firstMap.bfs(start, end);
		//System.out.println("Path = " + result);
		//firstMap.addEdge(start, test, "test", "roadType", 5.0);
		//System.out.println(firstMap);
		
		
		// test Dijkstra
//		List<GeographicPoint> result = firstMap.dijkstra(start, end);
//		System.out.println("Path = " + result);
		
		
		
		// TSP search test
		GraphLoader.loadRoadMap("data/testdata/tsptest.map", firstMap);
		System.out.println("TSP TEST DONE.");
		Set<GeographicPoint> locations = new HashSet<GeographicPoint>();
		GeographicPoint p1 = new GeographicPoint(0.0, 0.0);
		GeographicPoint p2 = new GeographicPoint(1,0);
		GeographicPoint p3 = new GeographicPoint(2, 1);
		GeographicPoint p4 = new GeographicPoint(2, 3);
		locations.add(p1);
		locations.add(p2);
		locations.add(p3);
		locations.add(p4);
		List<GeographicPoint> path = firstMap.tspGreedy(locations);
		System.out.println("===========================");
		System.out.println(path);
		System.out.println("===========================");
		
		// Greedy Search Test
		//Queue<GeographicPoint> test = new LinkedList<GeographicPoint>();
		//test.add(p1);
		//test.add(p2);
		//test.add(p3);
		//test.add(p4);
		//Map<GeographicPoint, List<GeographicPoint>> result = firstMap.greedySearch(p1, test);
		//System.out.println(result);
		
		// Test calculate distance method
//		List<GeographicPoint> path = firstMap.dijkstra(p1, p4);
//		Double dist = firstMap.calculateDistance(path);
//		List<GeographicPoint> path2 = firstMap.dijkstra(p1, p3);
//		Double dist2 = firstMap.calculateDistance(path2);
		
		
		// test Two Opt Solution
		path = firstMap.twoOpt(locations);
		System.out.println(path);
		
		// Distance Test
//		GeographicPoint t1 = new GeographicPoint(2,3);
//		GeographicPoint t2 = new GeographicPoint(0,0);
//		GeographicPoint t3 = new GeographicPoint(0,1);
//		GeographicPoint t4 = new GeographicPoint(2,1);
//		System.out.println(t1.distance(t2));
//		System.out.println(t1.distance(t3));
//		System.out.println(t1.distance(t4));

	}
	
	
}
