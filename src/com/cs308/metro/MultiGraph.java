package com.cs308.metro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MultiGraph implements GraphADT {

	private List<Node> nodes;
	private List<Edge> edges;

	public MultiGraph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}

	@Override
	public void addNode(Node n) {
		nodes.add(n);

	}

	@Override
	/*
	 * Method to find the shortest possible route between two nodes in
	 * the graph using the Breadth-First Search Algorithm.
	 * Returns: ArrayList of Nodes in order which they should be traversed
	 * in order to reach destination.
	 */
	public ArrayList<Node> findRoute(Node sourceNode, Node destinationNode) {
		// if start = end. stop. return null
		if (sourceNode.getNodeID() == destinationNode.getNodeID()) {
			System.out
					.println("Error: Source station identical to destination station");
			return null;
		}

		// To store directions to show the user
		ArrayList<Node> directionOutput = new ArrayList<Node>();
		Queue<Node> unvisited = new LinkedList<Node>(); 
		// route holds a node and the previous node connected to it
		HashMap<Node, Node> route = new HashMap<Node, Node>();
		List<Node> visited = new LinkedList<Node>();

		Node currentStation = sourceNode;

		unvisited.add(currentStation);
		visited.add(currentStation);

		while (!unvisited.isEmpty()) {
			currentStation = unvisited.remove();
			if (currentStation.equals(destinationNode)) {
				//destination reached, exit body of while loop
				break;
			} else {
				// for each of the nodes connected to n...
				for (Node n : getAdjacentNodes(currentStation)) {
					// if unvisited...
					if (!visited.contains(n)) {
						// mark node as visited
						visited.add(n);
						// add node to queue
						unvisited.add(n);
						route.put(n, currentStation);
					}
				}
			}
		}

		// If there is no route
		if (currentStation.equals(destinationNode) == false) {
			System.out.println("A route was not found");
			return null;
		} else {
			// retrace route though hashmap, add nodes to the directions arraylist
			for (Node n = destinationNode; n != null; n = route.get(n)) {
				directionOutput.add(n);
			}
			// have to reverse to put directions in chronological order
			Collections.reverse(directionOutput);
			return directionOutput;
		}
	}

	@Override
	public void addEdge(Edge e) {
		edges.add(e);
	}

	@Override
	public Node getNode(int nodeID) {
		Node node = null;
		for (Node n : nodes) {
			if (n.getNodeID() == nodeID) {
				node = n;
			}
		}
		return node;
	}
	/*returns a node object associated with a station name*/
	public Node getNodeFromStationName(String stationName) {
		for (Node n : nodes) {
			//If name associated with node match the stationName, return the node
			if (n.getNodeLabel().toLowerCase()
					.compareTo(stationName.toLowerCase()) == 0) {
				return n;
			}
		}
		System.out.println("Error: station not found");
		return null;
	}
	/*Method determines if there is an edge connecting 2 specified nodes*/
	public Boolean isEdge(String firstNode, String secondNode) {
		for (Edge e : edges) {
			if ((e.getPreviousNodeID() == Integer.parseInt(firstNode))
					&& e.getNextNodeID() == (Integer.parseInt(secondNode))) {
				return true;
			}

		}
		return false;
	}
	/* Returns an ArrayList of adjacent nodes 
	 * i.e. nodes that are connected to a particular node
	 * A node is adjacent to another node if an edge exists between them
	 * method uses the isEdge() helper method
	 * */
	public ArrayList<Node> getAdjacentNodes(Node n) {
		ArrayList<Node> adjacentNodes = new ArrayList<Node>();
		for (Node n2 : nodes) {
			if (isEdge(Integer.toString(n.getNodeID()),
					Integer.toString(n2.getNodeID()))) {
				adjacentNodes.add(n2);
			} else if (isEdge(Integer.toString(n2.getNodeID()),
					Integer.toString(n.getNodeID()))) {
				adjacentNodes.add(n2);
			}
		}
		return adjacentNodes;
	}

}
