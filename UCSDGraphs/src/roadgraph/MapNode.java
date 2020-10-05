package roadgraph;

import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;

// represents the nodes in the MapGraph
// each node is an intersection, and is represented by a unique Geographic point

public class MapNode {
	
	private GeographicPoint vertex;
	private List<MapEdge> edges;
	
	
	public MapNode(GeographicPoint point) {
		vertex = point;
		edges = new LinkedList<MapEdge>();
	}
	
	public GeographicPoint getVertex() {
		return vertex;
	}
	
	public void setVertex(GeographicPoint point) {
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
	
	public String toString() {
		return vertex.toString();
	}

}
