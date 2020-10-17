package roadgraph;

import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;

// represents the nodes in the MapGraph
// each node is an intersection, and is represented by a unique Geographic point

public class MapNode implements Comparable<MapNode>{
	
	private GeographicPoint vertex;
	private List<MapEdge> edges;			// all edges from that location/vertex
	private Double startDistance;			// distance to the node from starting point
	private Double goalDistance;			// distance from node to the goal
	
	
	public MapNode(GeographicPoint point) {
		vertex = point;
		edges = new LinkedList<MapEdge>();
		startDistance = Double.POSITIVE_INFINITY;
		goalDistance = Double.POSITIVE_INFINITY;
	}
	
	public GeographicPoint getLocation() {
		return vertex;
	}
	
	public void setLocation(GeographicPoint point) {
		vertex = point;
	}
	
	// returns a list of edges/neighbours
	public List<MapEdge> getEdges(){
		return edges;
	}
	
	// adds a new edge by creating a new MapEdge object 
	public void addEdge(GeographicPoint end1, GeographicPoint end2, String roadName, String roadType, double length) {
		MapEdge newEdge = new MapEdge(end1, end2, roadName, roadType, length);
		edges.add(newEdge);
	}
	
	public Double getStartDistance() {
		return startDistance;
	}
	
	public void setStartDistance(Double dist) {
		startDistance = dist;
	}
	
	public Double getGoalDistance() {
		return goalDistance;
	}
	
	public void setGoalDistance(Double dist) {
		goalDistance = dist;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (!(o instanceof MapNode || o == null)) return false;
		MapNode node = (MapNode) o;
		return node.vertex.equals(this.vertex);
		
	}
	
	@Override
	public int hashCode() {
		return vertex.hashCode();
	}
	
	// this method only takes into account the start distance
	@Override
	public int compareTo(MapNode nodeB) {
		
		if (this.startDistance < nodeB.startDistance) {
			return -1;
		}
		else if (this.startDistance > nodeB.startDistance) {
			return 1;
		}
		else return 0;
		
	}
	
	public String toString() {
		return vertex.toString();
	}

}
