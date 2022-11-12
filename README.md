





-----------------------------------------------------------------------------

Outline:
==========
* This is a Map Application implemented to find the shortest path from source to destination using Google API

* BFS, Dijkstra and A Star Algorithms are used in this implementation to find the shortest paths

* Project is extended to mimic real-life map applications by computing the 
The shortest path is based on 'time taken to reach the destination - taking speed limits into account' instead of just 'distance to destination.

* project is extended to implement the TSP(Travelling Sales Person) problem using two different algorithms i-e Greedy algorithm and Greedy 2opt algorithm

-----------------------------------------------------------------------------


Tasks:
=============
- Examine the provided code in Graph.java, GraphAdjMatrix.java and GraphAdjList.java

- Implement the degreeSequence method in the Graph class

- Complete the implementation of MapGraph class
    . Implement bfs from starting location to the destination and implement all 
      the required methods
      
- Augment more classes to support the execution of Dijkstra's Algorithm to find the 
    to find the shortest path through a weighted graph and to support the execution of the
    A star Search Algorithm which optimises search for the project
    
- Implement Dijkstra's Algorithm

- Implement A Star Search

- Extended the project to mimic real-life map applications. It finds the shortest path 
    from one location to another, depending on the time it will take to reach the 
    destination. This application will ignore the distances and find the quickest 
    route to the destination, considering the speed limits of the roads involved in the journey.
    No real-life data for speed limits, so speed limits are assumed!

- Extended the project to apply/solve the TSP(Travelling sales Person) problem with the help 
    of Greedy algorithm and Greedy 2opt algorithm using Dijkstra/A* Algorithms

----------------------------------------------------------------------------------


The files provided are as below:

Introduction to the course and graphs
--------------------------------------
1. basicgraph.Graph.java
2. basicgraph.GraphAdjList.java
3. basicgraph.GraphAdjMatrix.java

Class design and simple graph search
-------------------------------------
1. roadgraph.MapGraph.java
2. week3example.Maze.java
3. week3example.MazeLoader.java
4. week3example.MazeNode.java

Utility files
---------------
1. geography.GeographicPoint.java
2. geography.RoadSegment.java
3. util.GraphLoader.java

--------------------------------------------------------------------------------

This is a UCSD course project for Advanced Data Structures and Algorithms.

The files provided were skeleton code, as well as grading previews and 
testing files to be used in completing the course programming 
assignments.

----------------------------------------------------------------------------------
