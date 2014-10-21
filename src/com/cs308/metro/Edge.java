package com.cs308.metro;
public interface Edge {
	/*Getters and setters*/
   public String getLabel();
   
   public void setLabel(String label);

   
   public void setNextNodeID(int value);
   
   public int getNextNodeID();
   
   public void setPreviousNodeID(int value);
   
   public int getPreviousNodeID();
   
   }
