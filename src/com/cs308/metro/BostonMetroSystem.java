package com.cs308.metro;

import java.util.Scanner;
import java.util.ArrayList;

public class BostonMetroSystem {

	private MultiGraph multiGraph;
	private ArrayList<Node> route;

	public void execute() {
		//fields initialised with placeholder values
		String source = "";
		String destination = "";
		Node sourceNode = null;
		Node destinationNode = null;
		String filename = "bostonmetro.txt";

		try {
			MetroMapParser mmp = new MetroMapParser(filename);
			// create graph
			multiGraph = mmp.generateGraphFromFile();

			System.out.println("=================================");
			System.out.println("=\tBoston Metro System\t=");
			System.out.println("= G11\t\tWelcome!\t=");
			System.out.println("=================================\n\n");
			Scanner sc = new Scanner(System.in);

			do {
				// Get the first station name
				System.out.println("Please enter your departure station:\n");
				source = sc.nextLine();
				if (source.equals("quit")) {
					System.out.println("Exiting Application");
					System.exit(0);
				}

				sourceNode = multiGraph.getNodeFromStationName(source);
				// StPauls Handling
				if(source.equalsIgnoreCase("St.PaulStreet")){
				sourceNode = stPaulsDuplicate(sourceNode);
				}
				
				// Get the second station name
				System.out.println("Please enter your destination station:\n");
				destination = sc.nextLine();
				if (destination.equals("quit")) {
					System.out.println("Exiting Application");
					System.exit(0);
				}

				// If both are found then continue, otherwise prompt again
				if (multiGraph.getNodeFromStationName(source) != null
						&& multiGraph.getNodeFromStationName(destination) != null) {

					System.out.println("Calculating route...");

					// Calculate the route and display it
					destinationNode = multiGraph
							.getNodeFromStationName(destination);

					// StPauls Handling
					if(destination.equalsIgnoreCase("St.PaulStreet")){
					destinationNode = stPaulsDuplicate(destinationNode);
					}
					route = multiGraph.findRoute(sourceNode, destinationNode);
					if (route != null) {
						for (int i = 0; i < route.size(); i++) {
							System.out.println(i + ": "
									+ route.get(i).getNodeLabel());
						}
					} else
						System.out.println("There is no route to show!");

				} else {
					System.out.println("Please enter two valid station names. ");
				}
			} while (source != "quit" || destination != "quit");
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*Method to deal with the situation where user enters St.PaulStreet as a station name.
	 * This method determines which exact St.PaulStreet station the user means by giving
	 * a prompt. The relevant nodeID based on the user's input is assigned to the node*/
	private Node stPaulsDuplicate(Node check) {
		if (check.getNodeLabel().equals("St.PaulStreet")) {
			char spChoice = ' ';
			Scanner sp = new Scanner(System.in);
			System.out.println("Do you mean \"St.PaulStreet\" on Line B or C? (b|c)");
			//spChoice is assigned to the character the user enters
			spChoice = sp.nextLine().charAt(0);
			if (spChoice == 'b') {
				check.setNodeID(38);
			} else if (spChoice == 'c') {
				check.setNodeID(61);
			} else {
				System.out.println("You did not select a valid station. Assuming Line B");

			}
		}
		return check;
	}
}
