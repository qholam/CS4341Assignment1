import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NewWorld {

	int row, column;
	int goalRow, goalColumn;
	int startRow, startCol;
	HashMap<Integer, HashMap<Integer, Terrain>> terrains = new HashMap<Integer, HashMap<Integer, Terrain>>();
	String heuristic;
	
	/**
	 * Initialize the world
	 * @param args
	 */
	public NewWorld(String[] args) {
		String array[]; /* Array of elements in the file */
		String filename = args[0];/* filename to read */
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
					/*
					 * hashmap of terrains on one line which is going to be
					 * added to the terrains hashmap
					 */
					HashMap<Integer, Terrain> terrainOnALine = new HashMap<Integer, Terrain>();
					/* For going through each element in the array */
					for (int i = 0; i < array.length; i++) {
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
								
								startRow = thisLine;
								startCol = thisCol;
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
							thisCol = thisCol + 1;// moving on columns
						}

					}
					terrains.put(thisLine, terrainOnALine);
					column = thisCol;
					thisLine = thisLine + 1;// moved on a line
				} else {
					break;
				}
			}

			row = thisLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		row = terrains.size() - 1;
		column = terrains.get(0).size() - 1;
		System.out.println("max row: " + row + " max col: " + column);
	}
	
	public HashMap<Integer, HashMap<Integer, Terrain>> getAllTerrains(){
		return terrains;
	}
	
	public String toString(){
		String str = null;
		
		for(int i = 0; i < row +1; i++){
			HashMap<Integer, Terrain> currow = terrains.get(i);
			for(int j = 0; j < column + 1; j++){
				Terrain t = currow.get(j);
				System.out.print(t.toString() + " ");
			}
			System.out.println();
		}
		
		return str;
		
	}
}
