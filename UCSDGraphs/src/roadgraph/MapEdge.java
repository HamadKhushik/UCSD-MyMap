package roadgraph;

import geography.GeographicPoint;

// outlines the edges in the Map
public class MapEdge{
	
	private GeographicPoint start;
	private GeographicPoint end;
	private String roadName;
	private String roadType;
	private Double length;				// length/distance of the edge
	private Double approxTime;			// approximate time to cover the edge distance
	
	
	public MapEdge(GeographicPoint from, GeographicPoint to, String name, String type, Double len) {
		start = from;
		end = to;
		roadName = name;
		roadType = type;
		length = len;
		approxTime = this.calculateTime();
	}
	
	public GeographicPoint getStartVertex() {
		return start;
	}
	
	public GeographicPoint getEndVertex() {
		return end;
	}
	
	public Double getEdgeLength() {
		return length;
	}
	
	/**
	 * @return calculates time to cover travel the edge i-e from start location to end location of edge
	 */
	public Double calculateTime() {
		return start.distance(end)/this.getSpeed();
	}
	
	/**
	 * @return 
	 */
	public Double getEdgeTime() {
		return approxTime;
	}
	
	/**
	 * @return evaluates speed limit wrt road type and returns 
	 */
	public int getSpeed() {
		
		if (roadType.equals("city street")) {
			return 50;
		}
		else if (roadType.equals("residential")) {
			return 30;
		}
		else if (roadType.equals("")) {
			return 300;
			
		}
		else return 70;
	}
	
//	@Override
//	public int compareTo(MapEdge edgeB) {
//		
//		if (this.length < edgeB.length ) return 1;
//		else if (this.length == edgeB.length) return 0;
//		else return -1;
//		
//	}
//	
	
	public String toString() {
		String edge = start.toString() + "->" + end.toString() + ": " + roadName + ": " + roadType + ": length = ";
		edge += start.distance(end);
		return edge;
	}
	

}
