import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class astar {
	static NewWorld world;
	
	/**
	 * Runs the simulation with given heuristic
	 * @param args
	 */
	public static void main(String[] args) {
		world = new NewWorld(args);
	}

	public void astarSearch(NewWorld world){
		/*Possible actions that can be done*/
		String TURN_LEFT = "turn left";
		String TURN_RIGHT = "turn right";
		String FORWARD = "forward";
		String LEAP = "leap";
		
		/*Directions robot can face*/
		String NORTH = "north";
		String SOUTH = "south";
		String WEST = "west";
		String EAST = "east";
		
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(); //Priority queue of unexpanded nodes ordered from least total cost to greatest total cost so far
		LinkedList<Node> closed = new LinkedList<Node>(); //list of all nodes that have been already expanded
		Node cur; //current node we are examining 
		
		/*Create the starting node*/
		Terrain startTerrain = world.getAllTerrains().get(world.startRow).get(world.startCol);
		Node start = new Node(startTerrain);
		start.parent = null;
		start.costToGetHere = 1;
		start.heuristicCost = 0;
		start.totalCost = start.costToGetHere + start.heuristicCost;
		start.actions.add("");
		pq.add(start);
		
		/*Search through paths until goal is reached*/
		cur = start;
		while(!cur.root.getGoal()){
			/*get the top node in the priority queue*/
			cur = pq.poll();
			
			
		}
		
	}
	
	/**
	 * Attempts to move the robot 3 spaces forward.
	 * @return True if the leap was able to be executed, and false otherwise
	 */
	public int doLeap(Terrain t) {
		int cost = -1;
		/*row column of terrain*/
		int row = t.getRow();
		int col = t.getCol();
		
		/*Direction of robot*/
		Robot r = t.getRobot();
		String dir = r.getDirection();
		
		/*max dimensions of world*/
		int worldRow = world.row;
		int worldCol = world.column;
		
		/*check if you can leap*/
		if(dir.equals("north")){
			if(row >= 3){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newrow = row-3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				Terrain terrain = aterrain.get(col);
				terrain.setRobot(r);
				
				cost = 20;
			}
			else
				cost = -1;
		}
		else if(dir.equals("east")){
			if(worldCol - col >= 3){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newcol = col + 3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				Terrain terrain = aterrain.get(newcol);
				terrain.setRobot(r);
				
				cost = 20;
			}
			else 
				cost = -1;
		}
		else if(dir.equals("south")){
			if(worldRow - row >= 3){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newrow = row+3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				Terrain terrain = aterrain.get(col);
				terrain.setRobot(r);
				cost = 20;
			}
			else
				cost = -1;
		}
		else if(dir.equals("west")){
			if(row >=3){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newcol = col - 3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				Terrain terrain = aterrain.get(newcol);
				terrain.setRobot(r);
				
				cost = 20;
			}
			else
				cost = -1;
		}
		else{
			 cost = -1;
		}
		
		return cost;
	}

	/**
	 * Attempts to move the robot 1 space forward
	 * @return true if robot was moved forward
	 */
	public int doForwardMove(Terrain t) {
		int cost = -1;
		
		/*row column of terrain*/
		int row = t.getRow();
		int col = t.getCol();
		
		/*Direction of robot*/
		Robot r = t.getRobot();
		String dir = r.getDirection();
		
		/*max dimensions of world*/
		int worldRow = world.row;
		int worldCol = world.column;
		
		/*check if you can leap*/
		if(dir.equals("north")){
			if(row >= 1){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newrow = row-1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				Terrain terrain = aterrain.get(col);
				terrain.setRobot(r);
				
				cost = terrain.getComplexity();
			}
			else
				cost = -1;
		}
		else if(dir.equals("east")){
			if(worldCol - col >= 1){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newcol = col + 1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				Terrain terrain = aterrain.get(newcol);
				terrain.setRobot(r);
				
				cost = terrain.getComplexity();
			}
			else 
				cost = -1;
		}
		else if(dir.equals("south")){
			if(worldRow - row >= 1){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newrow = row+1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				Terrain terrain = aterrain.get(col);
				terrain.setRobot(r);
				cost = terrain.getComplexity();
			}
			else
				cost = -1;
		}
		else if(dir.equals("west")){
			if(row >= 1){
				/*removes robot from given terrain*/
				t.setRobot(null);
				/*move robot*/
				int newcol = col - 1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				Terrain terrain = aterrain.get(newcol);
				terrain.setRobot(r);
				
				cost = terrain.getComplexity();
			}
			else
				cost = -1;
		}
		else{
			cost = -1;
		}
		
		return cost;
	}

	/**
	 * Attempts to turn the robot to face a different direction
	 * @return true if turn was made
	 */
	public int doTurn(Terrain t, String dir) {
		int cost = -1;

		Robot r = t.getRobot();
		String direction = r.getDirection();
		
		if(direction.equals("north")){
			if(dir.equals("right")){
				r.setDirectionEast();
				cost = Math.round(t.getComplexity() / 3);
			}
			else if(dir.equals("left")){
				r.setDirectionWest();
				cost = Math.round(t.getComplexity() / 3);
			}
		}
		else if(direction.equals("east")){
			if(dir.equals("right")){
				r.setDirectionSouth();
				cost = Math.round(t.getComplexity() / 3);
			}
			else if(dir.equals("left")){
				r.setDirectionNorth();
				cost = Math.round(t.getComplexity() / 3);
			}
		}
		else if(direction.equals("south")){
			if(dir.equals("right")){
				r.setDirectionWest();
				cost = Math.round(t.getComplexity() / 3);
			}
			else if(dir.equals("left")){
				r.setDirectionEast();
				cost = Math.round(t.getComplexity() / 3);
			}
		}
		else if(direction.equals("west")){
			if(dir.equals("right")){
				r.setDirectionNorth();
				cost = Math.round(t.getComplexity() / 3);
			}
			else if(dir.equals("left")){
				r.setDirectionSouth();
				cost = Math.round(t.getComplexity() / 3);
			}
		}

		return cost;
	}

	/**
	 * Makes the best move currently available to the robot
	 */
	public boolean doBestMove(Terrain t) {
		boolean success = false;

		// TODO: implement this, this should call doForwardMove(), doTurn(), or
		// doLeap()
		//array of moves 
		

		return success;
	}
}
