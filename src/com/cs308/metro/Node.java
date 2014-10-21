package com.cs308.metro;

public interface Node {
/**
    * <pre>
    *           0..*     0..*
    * Node ------------------------- GraphADT
    *           node        &gt;       graphADT
    * </pre>
    */
     
	/*Getters and setters*/
	public void setNodeID(int id);


	public int getNodeID();


	public void setNodeLabel(String label);


	public String getNodeLabel();
}
