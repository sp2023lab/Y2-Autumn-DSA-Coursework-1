package Project; //Defines the package name

public class Edge implements Comparable<Edge> { //Declares the Edge class implementing Comparable interface
 
	int src, dest, weight; //Declares source, destination, and weight variables for the edge

    public int compareTo(Edge compareEdge) { //Compares the weight of two edges
        return this.weight - compareEdge.weight; //Returns difference in weight for comparison
    }
} 