package roadgraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author Hammad
 * tester class to test Dijkstra and A* wrt trip duration
 */
public class Tester {
	
	public static void main(String[] args) {
		
//		MapGraph theMap = new MapGraph();
//		System.out.print("DONE. \nLoading the map...");
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
//		System.out.println("DONE.");
		
		
		// calculate distance and time of each edge in the file
		
//		int numEdges = theMap.getNumEdges();
//		System.out.println("No of Edges = " + numEdges);
//		HashSet<MapEdge> edges = theMap.getEdges();
//		System.out.println("size of HashSet = " + edges.size());
//		
//		for (MapEdge k : edges) {
//			System.out.println("=====================================");
//			System.out.println("Distance between " + k.getStartVertex() + " and " + k.getEndVertex() + " is : " + k.getStartVertex().distance(k.getEndVertex()));
//			System.out.println("Approximate time for above edge is = " + k.getEdgeTime());
//		}
		
		
		// Basic Test - simpletest.map
		//GeographicPoint start = new GeographicPoint(1,1);
		//GeographicPoint end = new GeographicPoint(8, -1);
		
		//List<GeographicPoint> route = theMap.dijkstraTripDuration(start, end);					// dijkstra search
		//List<GeographicPoint> route = theMap.aStarSearchTripDuration(start, end);				// A* search
		//System.out.println(route);
		
		
		// real data tests
		
		  MapGraph testMap = new MapGraph();
		  GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		  
		  // A very simple test using real data 
		  GeographicPoint testStart = new GeographicPoint(32.869423, -117.220917); 
		  GeographicPoint testEnd = new GeographicPoint(32.869255, -117.216927); 
		  System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		  List<GeographicPoint> testroute = testMap.dijkstraTripDuration(testStart,testEnd); 
		  List<GeographicPoint> testroute2 = testMap.aStarSearch(testStart,testEnd);
		  
		  
		  // A slightly more complex test using real data 
		  testStart = new GeographicPoint(32.8674388, -117.2190213); 
		  testEnd = new GeographicPoint(32.8697828, -117.2244506); 
		  System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		  testroute = testMap.dijkstraTripDuration(testStart,testEnd); testroute2 =
		  testMap.aStarSearchTripDuration(testStart,testEnd);
		 
	}

}
