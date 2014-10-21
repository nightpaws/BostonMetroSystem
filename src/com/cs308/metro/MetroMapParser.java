package com.cs308.metro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class MetroMapParser {


	private BufferedReader fileInput;

	/**
	 * @effects: creates a new parser that will read from the file filename
	 *           unless the file does not exist. The filename should specify the
	 *           exact location of the file. This means it should be something
	 *           like /mit/$USER/6.170/ex3/bostonmetro.txt
	 *
	 * 
	 * @throws java.io.IOException
	 *             if there <tt>filename</tt> cannot be read
	 *
	 * @returns a new MetroMapParser that will parse the file filename
	 */

	public MetroMapParser(String filename) throws IOException {
		// a buffered reader reads line by line, returning null when file is
		// done
        try{
            fileInput = new BufferedReader(new FileReader(filename));
        }
        catch (Exception e){
            BadFileException noFile = new BadFileException(filename); //Error for incorrect file
        }
	}

	/**
	 * @effects: parses the file, and generates a graph from it, unless there is
	 *           a problem reading the file, or there is a problem with the
	 *           format of the file.
	 *
	 * @throws java.io.IOException
	 *             if there is a problem reading the file
	 * @throws
	 *             if there is a problem with the format of the file
	 *
	 * @returns the Graph generated by the file
	 */

	public MultiGraph generateGraphFromFile() throws IOException, BadFileException {

		String line = fileInput.readLine();
		StringTokenizer st;
		String stationID;
		String stationName;
		String lineName;
		String outboundID, inboundID;
		MultiGraph graph = new MultiGraph();
		



		while (line != null) {

			// STUDENT :
			//
			// in this loop, you must collect the information necessary to
			// construct your graph, and you must construct your graph as well.
			// how and where you do this will depend on the design of your
			// graph.
			//

			// StringTokenizer is a java.util Class that can break a string into
			// tokens
			// based on a specified delimiter. The default delimiter is
			// " \t\n\r\f" which
			// corresponds to the space character, the tab character, the
			// newline character,
			// the carriage-return character and the form-feed character.
			st = new StringTokenizer(line);

			// We want to handle empty lines effectively, we just ignore them!
			if (!st.hasMoreTokens()) {
				line = fileInput.readLine();
				continue;
			}

			// from the grammar, we know that the Station ID is the first token
			// on the line
			stationID = st.nextToken();

			if (!st.hasMoreTokens()) {
				throw new BadFileException("no station name");
			}

			// from the grammar, we know that the Station Name is the second
			// token on the line.
			stationName = st.nextToken();

			if (!st.hasMoreTokens()) {
				throw new BadFileException("station is on no lines");
			}
			
			Station tempStation = new Station(Integer.parseInt(stationID), stationName);
			graph.addNode(tempStation);
			

			while (st.hasMoreTokens()) {
				lineName = st.nextToken();

				if (!st.hasMoreTokens()) {
					throw new BadFileException("poorly formatted line info");
				}

				outboundID = st.nextToken();

				if (!st.hasMoreTokens()) {
					throw new BadFileException(
							"poorly formatted adjacent stations");
				}

				inboundID = st.nextToken();
				
				Track outboundTrack = new Track(Integer.parseInt(outboundID), Integer.parseInt(stationID), lineName);
				Track inboundTrack = new Track(Integer.parseInt(stationID), Integer.parseInt(inboundID), lineName);
				
				graph.addEdge(outboundTrack);
				graph.addEdge(inboundTrack);
			
			}

			line = fileInput.readLine();
		}



        return graph;

	}
	
	

}
