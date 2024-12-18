package Project;

import java.util.Arrays; //Imports the Arrays class
import java.util.LinkedList; //Imports the LinkedList class
import java.util.List; //Imports the List class

public class DijkastrasTravelTimeAlgorithm {

    // Method to calculate travel time from distance using the given rules
    public int calculateTravelTime(int distance) {
        if (distance < 5) {
        	return distance * 60 / 5;  //Checks if the distance is less than 5 miles and returns the time in minutes
        }
        else if (distance <= 10) {
        	return distance * 60 / 20; //Checks if the distance is between 5 and 10 miles and returns the time in minutes
        }
        else { 
        	return distance * 60 / 30; //Otherwise, assumes the distance is greater than 10 miles and returns the time in minutes
        }
    }

    // Dijkstra's algorithm to find the shortest travel time from the source to all other nodes
    public void dijkstra(int source, int[][] distances, int n, int[] prev) {
        int[] shortestTimes = new int[n]; //Declares an array to store the shortest travel times for all nodes
        boolean[] visited = new boolean[n]; //Declares an array to track whether a node has been visited
        Arrays.fill(shortestTimes, Integer.MAX_VALUE); //Fills the shortestTimes array with infinity for all nodes initially
        Arrays.fill(prev, -1); //Fills the prev array with -1, indicating no previous node initially
        shortestTimes[source] = 0; //Sets the source node's travel time to 0
        prev[source] = source; //Sets the source node as its own previous node
        for (int count = 0; count < n - 1; count++) { //Parses through all nodes except the source
            int u = -1, minTime = Integer.MAX_VALUE; //Initializes u as -1 and minTime as infinity
            for (int i = 0; i < n; i++) { //Parses through all the nodes
                if (!visited[i] && shortestTimes[i] < minTime) { //Checks if the node i is not visited and its travel time is smaller than minTime
                    u = i; //Sets u to the current node i
                    minTime = shortestTimes[i]; //Updates the minimum travel time to the smallest value
                }
            }
            if (u == -1) break; //Checks if no node was found with a smaller travel time, implying all reachable nodes have been processed
            visited[u] = true; //Marks the selected node u as visited
            for (int v = 0; v < n; v++) { //Parses through the neighbors of node u
                if (!visited[v] && distances[u][v] != Integer.MAX_VALUE) { //Checks if the neighbor v is not visited and there is a valid distance from u to v
                    int time = calculateTravelTime(distances[u][v]); //Calculates the travel time to the neighbor v
                    if (shortestTimes[u] + time < shortestTimes[v]) { //Checks if the new calculated time through u is smaller than the current time to v
                        shortestTimes[v] = shortestTimes[u] + time; //Updates the shortest travel time to v
                        prev[v] = u; //Sets the previous node of v to u
                    }
                }
            }
        }
        // Collect the results in a list
        List<String> results = new LinkedList<>(); //Creates a new linked list to store the results
        for (int i = 0; i < n; i++) { //Parses through all the nodes
            if (i != source) { //Checks if the node i is not the source node
                results.add("To " + (char) (i + 65) + ": " + shortestTimes[i] + " minutes, Path: " + getPath(i, prev)); //Adds the travel time and path to the results list
            }
        }
        // Sort the results based on the travel times (from lowest to highest)
        results.sort((a, b) -> { //Parses through the results list and sorts it based on travel times
            int timeA = Integer.parseInt(a.split(" ")[2]); //Extracts the travel time from the first string
            int timeB = Integer.parseInt(b.split(" ")[2]); //Extracts the travel time from the second string
            return Integer.compare(timeA, timeB); //Compares the two travel times and sorts in ascending order
        });
        // Output the sorted results
        System.out.println("Shortest travel times and paths from " + (char) (source + 65) + ":"); //Prints the header of the output
        for (String result : results) { //Parses through the sorted results list
            System.out.println(result); //Prints each result (path and travel time)
        }
    }

    // Method to reconstruct the path from any node to the source using the prev array
    public String getPath(int target, int[] prev) {
        LinkedList<Integer> path = new LinkedList<>(); //Creates a new linked list to store the path
        int current = target; //Sets the current node to the target node
        while (current != prev[current]) { //Parses through the prev array to reconstruct the path
            path.addFirst(current); //Adds the current node to the beginning of the path
            current = prev[current]; //Moves to the previous node
        }
        path.addFirst(current);  //Adds the source node at the beginning of the path
        StringBuilder pathString = new StringBuilder(); //Creates a new StringBuilder to store the path as a string
        for (int i = 0; i < path.size(); i++) { //Parses through the path list
            pathString.append((char) (path.get(i) + 65)); //Converts the node index to its corresponding letter (A=0, B=1, ...)
            if (i < path.size() - 1) pathString.append(" -> "); //Adds an arrow between nodes, except for the last node
        }
        return pathString.toString(); //Returns the reconstructed path as a string
    }

    public static void main(String[] args) {
        //Define the distances from G (index 6) to A, B, C, D, E, F
        int[][] distances = {
            {0, 6, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 6},  //From A
            {Integer.MAX_VALUE, 0, 10, 33, Integer.MAX_VALUE, 16, 1}, //From B
            {21, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 18, Integer.MAX_VALUE, 29}, //From C
            {Integer.MAX_VALUE, 10, 14, 0, 16, 24, 1}, //From D
            {27, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 8}, //From E
            {6, Integer.MAX_VALUE, 18, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 17}, //From F
            {6, 1, 29, 1, 8, 17, 0}  //From G (to A, B, C, D, E, F)
        };
        DijkastrasTravelTimeAlgorithm dijkstra = new DijkastrasTravelTimeAlgorithm();
        int n = distances.length; //Number of vertices, including G
        int[] prev = new int[n]; //Array to store the previous node in the path
        dijkstra.dijkstra(6, distances, n, prev); //Start Dijkstra's algorithm from G (index 6)
    }
}
