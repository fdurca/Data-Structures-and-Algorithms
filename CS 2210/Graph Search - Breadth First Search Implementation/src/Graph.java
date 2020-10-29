import java.util.Iterator;
import java.util.*;

/*
 * This holds the graph object and its methods
 * @author Filip Durca
 */
public class Graph implements GraphADT {

	// Instance Variables
	Edge[][] adjMatrix;
	Node[] nodes;
	int size;

	/*
	 * This method is the constructor
	 * 
	 * @param int n is the amount of nodes
	 */
	Graph(int n) {
		// Initialize the variables
		adjMatrix = new Edge[n][n];
		nodes = new Node[n];
		size = n;

		// Initialize the nodes
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
	}

	/*
	 * This method inserts an edge into the array of nodes, throwing an exception if
	 * the node already exists
	 * 
	 * @param Node nodeu is the first end point of the edge
	 * 
	 * @param Node nodev is the second endpoint of the edge
	 */
	public void insertEdge(Node nodeu, Node nodev, int edgeType) throws GraphException {
		// Check if the nodes exist
		if (exists(nodeu) && exists(nodev)) {

			// Check if there is space for the edge to be added
			if (adjMatrix[nodeu.getName()][nodev.getName()] != null) {
				throw new GraphException();
			}
			// If there is space, add the node
			else {
				Edge edge = new Edge(nodeu, nodev, edgeType);
				adjMatrix[nodeu.getName()][nodev.getName()] = edge;
				adjMatrix[nodev.getName()][nodeu.getName()] = edge;
			}
		} else
			// Throw exception if nodes not found in graph
			throw new GraphException();
	}

	/*
	 * This method returns the node at the given place
	 * 
	 * @param int Name is the int value of the node
	 * 
	 * @return Node is the node at position name
	 */
	public Node getNode(int name) throws GraphException {
		// Make sure the value is within the bounds
		if (name >= 0 && name < size)
			return nodes[name];

		// Throw exception if out of bounds
		throw new GraphException();
	}

	/*
	 * This method returns an iterator of the edges around the node
	 * 
	 * @param Node u is the node being checked
	 * 
	 * @return Iterator an iterator of the adjacent node
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		// First check if the node exists
		if (exists(u)) {

			// Create a stack to hold them
			Stack<Edge> iteration = new Stack<Edge>();

			// Loop through every node
			for (int i = 0; i < size; i++) {
				// Check if adjacent
				if (areAdjacent(u, nodes[i])) {

					// Add to stack if adjacent
					iteration.push(adjMatrix[u.getName()][i]);
				}
			}

			// Return the iterator
			return iteration.iterator();

		} else
			// Throw an exception if the nodes do not exist
			throw new GraphException();
	}

	/*
	 * This method returns the edge between 2 nodes
	 * 
	 * @param node u is the first node
	 * 
	 * @param node v is the second node
	 * 
	 * @return Edge is the edge that lies between them
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		// Check if the nodes exist
		if (exists(u) && exists(v)) {

			// Check if there is a edge
			if (adjMatrix[u.getName()][v.getName()] != null) {

				return adjMatrix[u.getName()][v.getName()];
			}
			// If there is no edge, throw exception
			else
				throw new GraphException();
		} else
			// Throw exception if nodes not found in graph
			throw new GraphException();
	}

	/*
	 * This method checks if 2 nodes are adjacent
	 * 
	 * @param node u is the first node
	 * 
	 * @param node v is the second node
	 * 
	 * @return Boolean true if they are adjacent
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		// Check if the nodes exist first
		if (!(exists(u) && exists(v)))
			throw new GraphException();

		// Try getting the edge, if an error is thrown there is no edge
		try {
			getEdge(u, v);
			return true;
		} catch (GraphException e) {
			return false;
		}
	}

	/*
	 * This method checks if the nodes are in the graph
	 * 
	 * @param node u is the node being checked
	 * 
	 * @return boolean true if they exist, false if they dont
	 */
	private boolean exists(Node u) {
		// Try getting the node, if no error is thrown, return true
		try {
			getNode(u.getName());
			return true;
		} catch (GraphException e) {
			return false;
		}
	}

}
