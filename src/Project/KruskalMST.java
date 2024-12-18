package Project; //Defines the package name
import java.util.ArrayList; //Imports the ArrayList class
import java.util.Collections; //Imports the Collections class for sorting

// KruskalMST class implementing Kruskal's algorithm
public class KruskalMST { //Defines KruskalMST class

	public void generateMST(Graph graph) { //Method to generate Minimum Spanning Tree (MST) using Kruskal's algorithm
        Collections.sort(graph.getEdges()); //Sorts edges by weight in ascending order
        DisjointSet set = new DisjointSet(graph.vertices); //Creates a disjoint set to manage the components of the graph
        ArrayList<Edge> result = new ArrayList<>(); //ArrayList to store the edges of the MST
        int totalWeight = 0; //Variable to store the total weight of the MST
        for (Edge edge : graph.getEdges()) { //Parses through all edges in the graph
            int x = set.find(edge.src); //Finds the root of the set containing 'src'
            int y = set.find(edge.dest); //Finds the root of the set containing 'dest'

            if (x != y) { //Checks if the source and destination are in different sets
                result.add(edge); //Adds the edge to the MST
                totalWeight += edge.weight; //Increments the total weight of the MST by the weight of the edge
                set.union(x, y); //Merges the two sets containing 'src' and 'dest'
            }
        }
        //Print the resulting MST
        System.out.println("Edges in the MST:"); //Prints the heading for the MST edges
        for (Edge e : result) { //Parses through the edges of the MST
            System.out.println((char) ('A' + e.src) + " - " + (char) ('A' + e.dest) + " : " + e.weight); //Prints each edge in the MST
        }
        System.out.println("Total weight of MST: " + totalWeight); //Prints the total weight of the MST
    }

    public static void main(String[] args) { //Main method to execute the Kruskal algorithm
        Graph graph = new Graph(7); //Creates a graph with 7 vertices (A to G)
        //Add all the edges of the graph by weights
        graph.addEdge(0, 1, 10); //A - B
        graph.addEdge(0, 5, 6);  //A - F
        graph.addEdge(0, 3, 8);  //A - D
        graph.addEdge(1, 2, 18); //B - C
        graph.addEdge(1, 5, 16); //B - F
        graph.addEdge(1, 3, 33); //B - D
        graph.addEdge(2, 4, 14); //C - E
        graph.addEdge(2, 5, 16); //C - F
        graph.addEdge(3, 5, 24); //D - F
        graph.addEdge(3, 6, 1);  //D - G
        graph.addEdge(4, 5, 27); //E - F
        graph.addEdge(4, 6, 8);  //E - G
        graph.addEdge(5, 6, 17); //F - G
        KruskalMST mst = new KruskalMST(); //Creates an instance of KruskalMST
        mst.generateMST(graph); //Generates and prints the MST
    }
}