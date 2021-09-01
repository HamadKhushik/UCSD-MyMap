package roadgraph;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import geography.GeographicPoint;

public class TspGreedyAlgorithm {
	
	private HashMap<GeographicPoint, MapNode> vertices;			// map of vetices and locations
	
	public TspGreedyAlgorithm(HashMap<GeographicPoint, MapNode> locations) {
		vertices = locations;
	}
	
	
	
	/**
	 * @param toVisit: set of vertices that need to be visited atleast once - TSP problem
	 * @return path which visits every vertex atleast once
	 */
	public List<GeographicPoint> greedyAlgorithm(Set<GeographicPoint> toVisit){
		
		
		
		return null;
	}

}
