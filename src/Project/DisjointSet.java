package Project; //Defines the package name

public class DisjointSet { //Defines DisjointSet class for union-find structure

	private int[] parent, rank; //Declares arrays for parent and rank of each element

    public DisjointSet(int n) { //Constructor to initialize the disjoint set with 'n' elements
        parent = new int[n]; //Initializes the parent array with 'n' elements
        rank = new int[n]; //Initializes the rank array with 'n' elements
        for (int i = 0; i < n; i++) { //Parses through all the elements in the set
            parent[i] = i; //Sets each element's parent to itself
        }
    }

    public int find(int i) { //Finds the representative of the set containing 'i'
        if (parent[i] != i) //Checks if the element is its own parent
            parent[i] = find(parent[i]); //Recursively finds the parent and compresses the path
        return parent[i]; //Returns the parent of the element
    }

    public void union(int x, int y) { //Union operation to merge the sets containing 'x' and 'y'
        int xRoot = find(x); //Finds the root of set containing 'x'
        int yRoot = find(y); //Finds the root of set containing 'y'

        if (xRoot == yRoot) return; //Checks if both elements are already in the same set, in which case do nothing

        if (rank[xRoot] < rank[yRoot]) //Checks if the rank of 'xRoot' is less than 'yRoot'
            parent[xRoot] = yRoot; //Makes 'yRoot' the parent of 'xRoot'
        else if (rank[xRoot] > rank[yRoot]) //Checks if the rank of 'xRoot' is greater than 'yRoot'
            parent[yRoot] = xRoot; //Makes 'xRoot' the parent of 'yRoot'
        else { //Handles case where ranks are equal
            parent[yRoot] = xRoot; //Makes 'xRoot' the parent of 'yRoot'
            rank[xRoot]++; //Increments the rank of 'xRoot'
        }
    }
}