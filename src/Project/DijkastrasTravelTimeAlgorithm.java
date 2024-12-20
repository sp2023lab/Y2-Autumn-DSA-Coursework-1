package Project;

import java.util.Arrays;

public class DijkastrasTravelTimeAlgorithm {

    // Method to calculate travel time from distance using the given rules
    public int calculateTravelTime(int distance) {
        if (distance < 5) {
            return distance * 60 / 5;  // Travel time in minutes (5 mph)
        } else if (distance <= 10) {
            return distance * 60 / 20; // Travel time in minutes (20 mph)
        } else { 
            return distance * 60 / 30; // Travel time in minutes (30 mph)
        }
    }

    // Dijkstra's algorithm to find the shortest path and calculate travel time from the source to all other nodes
    public void dijkstra(int source, int[][] cost, int[] dist, int n, int[] prev) {
        boolean[] S = new boolean[n]; // Tracks which vertices are in the MST
        Arrays.fill(S, false); // Initialize all vertices as not included in MST
        Arrays.fill(prev, -1); // Initialize the previous node array to -1 (no previous node initially)
        
        for (int i = 0; i < n; i++) { // Parses through the vertices
            dist[i] = cost[source][i]; // Initializes the distance array with the direct distance from the source to each vertex
            if (dist[i] != Integer.MAX_VALUE && i != source) { // Checks if the distance is not set to infinity and that it is not the source
                prev[i] = source; // Checks if the vertex is reachable and sets the previous node to the source
            }
        }
        
        S[source] = true; // Mark the source vertex as included in MST
        dist[source] = 0; // Set the distance to the source vertex as 0
        
        for (int j = 1; j < n - 1; j++) { // Parses through all remaining vertices
            int u = -1; // Variable to track the vertex with the minimum distance
            int minDist = Integer.MAX_VALUE; // Initialize the minimum distance as infinity
            
            for (int i = 0; i < n; i++) { // Parses through the number of vertices
                if (!S[i] && dist[i] < minDist) { // Checks if vertex i is not in MST and has a smaller distance
                    minDist = dist[i]; // Update the minimum distance
                    u = i; // Set vertex u to the current vertex i
                }
            }
            if (u == -1) { // Checks if no vertex is found (i.e., all remaining vertices are disconnected)
                break;
            }
            S[u] = true; // Mark the vertex u as included in the MST
            for (int w = 0; w < n; w++) { // Parses through the number of vertices
                if (!S[w] && cost[u][w] != Integer.MAX_VALUE) { // Checks if vertex w is not in MST and there is an edge between u and w
                    if (dist[u] + cost[u][w] < dist[w]) { // Checks if the distance through u to w is shorter than the current distance
                        dist[w] = dist[u] + cost[u][w]; // Updates the distance to vertex w
                        prev[w] = u; // Sets the previous vertex of w to be u
                    }
                }
            }
        }
    }

    // Recursive method to get the path from the source to a given node
    public String getPath(int node, int[] prev) {
        if (prev[node] == -1) { // Checks if the node has no previous node (i.e., it's the source)
            return String.valueOf((char) (node + 65)); // Returns the node as a character (A=0, B=1, etc.)
        }
        return getPath(prev[node], prev) + " -> " + (char) (node + 65); // Recursively gets the path and appends the current node
    }

    public static void main(String[] args) {
        // Corrected distance matrix representing the graph, including vertex G
        int[][] corrected_distances = {
            {0, 6, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 6},  // From A
            {Integer.MAX_VALUE, 0, 10, 33, Integer.MAX_VALUE, 16, 1},  // From B
            {16, 10, 0, Integer.MAX_VALUE, 14, 18, 29},  // From C (corrected)
            {Integer.MAX_VALUE, 33, Integer.MAX_VALUE, 0, 16, 24, 1},  // From D
            {27, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 8},  // From E
            {6, Integer.MAX_VALUE, 18, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 17},  // From F
            {6, 1, 29, 1, 8, 17, 0}  // From G
        };
        
        int n = corrected_distances.length; // Number of vertices, including G
        int[] dist = new int[n]; // Array to store the shortest distance from the source to each vertex
        int[] prev = new int[n]; // Array to store the previous vertex on the shortest path to each vertex
        
        int source = 6; // Source vertex (G in this case)
        
        DijkastrasTravelTimeAlgorithm dijkstra = new DijkastrasTravelTimeAlgorithm();
        dijkstra.dijkstra(source, corrected_distances, dist, n, prev); // Calls Dijkstra's algorithm to calculate the shortest paths
        
        // Outputs the shortest distances, travel times, and paths from the source vertex
        System.out.println("Shortest travel times and paths from vertex " + (char) (source + 65) + ":");
        for (int i = 0; i < n; i++) {
            if (i != source) { // Checks if the vertex is not the source vertex itself
                System.out.print("To vertex " + (char) (i + 65) + ": ");
                
                if (dist[i] == Integer.MAX_VALUE) { // Checks if there is no path to vertex i
                    System.out.println("No path"); // Outputs that there is no path
                } else {
                    // Calculate the travel time for each path
                    int travelTime = dijkstra.calculateTravelTime(dist[i]);
                    System.out.println(travelTime + " minutes via path " + dijkstra.getPath(i, prev)); // Outputs the travel time and the path
                }
            }
        }
    }
}
