package com.cs308.metro;
/*Stations are used to represent Nodes within the MultiGraph in the Boston Metro System */
public class Station implements Node{


	public Station(int stationID, String stationName) {
		nodeID = stationID;
		nodeLabel = stationName;
	}
	
	private int nodeID;
	private String nodeLabel;
   
	/*Getters and setters*/
	public void setNodeID(int id) {
		nodeID = id;
	}

	
	public int getNodeID() {
		return nodeID;
	}


	public void setNodeLabel(String label) {
		nodeLabel = label;
		
	}


	public String getNodeLabel() {
		return nodeLabel;
	}

	public String toString(){
		return ("Node ID " + nodeID + " Station Name: " + nodeLabel);
	}

}
