





-----------------------------------------------------------------------------

This Project is implementation of basic graphs for real transportation data
using Google API

BFS, Dijkstra and A Star Algorithms are used in this implementation

Project is extended to mimic real life map applications by computing the 
shortest path based on 'time taken to reach destination' instead of 
'distance to destination'

project is extended to implement the TSP problem using two different 
algorithms i-e Greedy algorithm and Greedy 2opt algorithm

-----------------------------------------------------------------------------


Tasks:
=============
1. Examine the provided code in Graph.java, GraphAdjMatrix.java and GraphAdjList.java

2. Implement the degreeSequence method in the Graph class

3. Complete the implementation of MapGraph class
    . Implement bfs from starting location to destination and implement all 
      the required methods
      
4. Augment more classes to support the execution of Dijkstra's Algorithm to find the 
    to find the shortest path through a weighted graph and to support execution of the
    A star Search Algorithm which optimises search for the project
    
5. Implement Dijkstra's Algorithm using PriorityQueue

6. Implement A Star search

7. Extended the project to mimic real life map applications. It finds the shortest path 
    from one location to another location depending on the time it will take to reach the 
    destination. This application will ignore the distances and will find the quickest 
    route to destination considering the speed limits of the roads involved in the journey.
    No real life data for speed limits, so speed limits are assumed!

8. Extended the project to apply/solve the TSP(Travelling sales Person) problem with the help 
    of Greedy algorithm and Greedy 2opt algorithm using Dijkstra/A* Algorithms

----------------------------------------------------------------------------------


Files provided are as below:

Introduction to the course and graphs
==============================================
basicgraph.Graph.java
basicgraph.GraphAdjList.java
basicgraph.GraphAdjMatrix.java

Class design and simple graph search
==================================================
roadgraph.MapGraph.java
week3example.Maze.java
week3example.MazeLoader.java
week3example.MazeNode.java

Utility files
=============
geography.GeographicPoint.java
geography.RoadSegment.java
util.GraphLoader.java

--------------------------------------------------------------------------------
