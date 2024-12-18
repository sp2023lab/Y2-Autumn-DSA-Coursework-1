package Project; //Defines the package name
import java.util.*; //Imports necessary classes from the Java utility package

public class Graph { //Defines Graph class to represent a graph
 
	int vertices; //Declares the number of vertices in the graph
    ArrayList<Edge> edges; //Declares an ArrayList to store edges of the graph

    public Graph(int numVertices) { //Constructor to initialize the graph with a specified number of vertices
        this.vertices = numVertices; //Sets the number of vertices
        edges = new ArrayList<>(); //Initializes the list to store edges
    }

    public void addEdge(int src, int dest, int weight) { //Adds an edge to the graph
        Edge edge = new Edge(); //Creates a new Edge object
        edge.src = src; //Sets the source vertex of the edge
        edge.dest = dest; //Sets the destination vertex of the edge
        edge.weight = weight; //Sets the weight of the edge
        edges.add(edge); //Adds the edge to the edges list
    }

    public ArrayList<Edge> getEdges() { //Returns the list of edges
        return edges; //Returns the edges
    }
} 