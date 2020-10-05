package roadgraph;

import geography.GeographicPoint;

// outlines the edges in the Map
public class MapEdge {
	
	private GeographicPoint start;
	private GeographicPoint end;
	private String roadName;
	private String roadType;
	private double length;
	
	
	public MapEdge(GeographicPoint from, GeographicPoint to, String name, String type, double len) {
		start = from;
		end = to;
		roadName = name;
		roadType = type;
		length = len;
	}
	
	public GeographicPoint getStartVertex() {
		return start;
	}
	
	public GeographicPoint getEndVertex() {
		return end;
	}
	
	public String toString() {
		String edge = start.toString() + "->" + end.toString() + ": " + roadName + ": " + roadType + ": length = ";
		edge += start.distance(end);
		return edge;
	}
	

}
