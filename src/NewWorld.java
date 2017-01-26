import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NewWorld {

	int row, column;
	int goalRow, goalColumn;
	HashMap<Integer, HashMap<Integer, Terrain>> terrains = new HashMap<Integer, HashMap<Integer, Terrain>>();
	String heuristic;
	
	/**
	 * Initialize the world
	 * @param args
	 */
	public NewWorld(String[] args) {
		String array[]; /* Array of elements in the file */
		String filename = args[0];/* filename to read */
		heuristic = args[1];
		/* for opening up the file and reading contents */
		try {
			FileReader file = new FileReader(filename);
			BufferedReader reader = new BufferedReader(file);
			/* the line we are reading to determine rows */
			int thisLine = 0;
			/* The line that we read in from the file */
			String line;
			boolean var = true;
			while (var) {
				line = reader.readLine();
				/* If the line is equal to something then decompose it */
				if (line != null) {
					array = line.split("\t");
					int thisCol = 0;
					thisLine = thisLine + 1;// moved on a line
					/*
					 * hashmap of terrains on one line which is going to be
					 * added to the terrains hashmap
					 */
					HashMap<Integer, Terrain> terrainOnALine = new HashMap<Integer, Terrain>();
					/* For going through each element in the array */
					for (int i = 0; i < array.length; i++) {
						thisCol = thisCol + 1;// moving on columns
						/*
						 * Check if array equals nothing and it equals something
						 * go through it
						 */
						if (!array[i].equals("")) {
							Terrain temporary = new Terrain();

							if (array[i].equals("#")) {
								temporary.setNavigable(true);
							} else if (array[i].equals("S")) {
								temporary.setStart(true);
								
								/*create a robot and add it to this terrain*/
								Robot robot = new Robot();
								temporary.setRobot(robot);
							} else if (array[i].equals("G")) {
								goalRow = thisLine;
								goalColumn = thisCol;
								temporary.setGoal(true);
							} else {
								int complexity = Integer.parseInt(array[i]);
								temporary.setComplexity(complexity);
							}
							temporary.setRow(thisLine);
							temporary.setCol(thisCol);
							terrainOnALine.put(thisCol, temporary);
						}

					}
					terrains.put(thisLine, terrainOnALine);
					column = thisCol;
				} else {
					break;
				}
			}

			row = thisLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, HashMap<Integer, Terrain>> getAllTerrains(){
		return terrains;
	}
}
