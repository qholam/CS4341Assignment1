import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class astar {
	/* Possible actions that can be done */
	String TURN_LEFT = "turn left";
	String TURN_RIGHT = "turn right";
	String FORWARD = "forward";
	String LEAP = "leap";

	/* Directions robot can face */
	String NORTH = "north";
	String SOUTH = "south";
	String WEST = "west";
	String EAST = "east";

	/**
	 * Runs the simulation with given heuristic
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		astar a = new astar();

		args = new String[1];
		args[0] = "test.txt";
		NewWorld world = new NewWorld(args);

		world.toString();
		
		a.astarSearch(world);
	}

	public void astarSearch(NewWorld world) {
		int nodesExpanded = 0;// number of expanded nodes

		PriorityQueue<Node> pq = new PriorityQueue<Node>(); // Priority queue of
															// unexpanded nodes
															// ordered from
															// least total cost
															// to greatest total
															// cost so far
		LinkedList<Node> closed = new LinkedList<Node>(); // list of all nodes
															// that have been
															// already expanded
		Node cur; // current node we are examining

		/* Create the starting node */
		Terrain startTerrain = world.getAllTerrains().get(world.startRow).get(world.startCol);
		Node start = new Node(startTerrain);
		start.parent = null;
		start.costToGetHere = 1;
		start.heuristicCost = 0;
		start.totalCost = start.costToGetHere + start.heuristicCost;
		start.actions.add("");
		start.root.setDirection(NORTH);
		pq.add(start);

		/* Search through paths until goal is reached */
		cur = start;
		while (!cur.root.getGoal()) {
			/* get the top node in the priority queue */
			cur = pq.poll();

			/*
			 * If cur has not been expanded yet, expand it and create the
			 * children node
			 */
			if (!closed.contains(cur)) {
				/*
				 * List of terrains that we can potentially move to from the
				 * current terrain we are on
				 */
				LinkedList<Terrain> potentialTerrains = new LinkedList<Terrain>();
				/* HashMap where the key is a terrain t and the value is the set of actions need to
				 * go from the current terrain to t
				 */
				HashMap<Terrain, LinkedList<String>> actionsToGetTo = new HashMap<Terrain, LinkedList<String>>();
				/* HashMap where the key is a terrain t and the value is the cost to get there from the current terrain 
				 * we are
				 */
				HashMap<Terrain, Integer> costToGetTo = new HashMap<Terrain, Integer>();
						
				/*CHECK WHAT POTENTIAL MOVES YOU CAN MAKE*/
				//remember direction we are facing
				String facing = cur.root.getDirection();
				//leap 
				Terrain t1 = doLeap(world, cur.root);
				if(t1 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t1);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("leap");
					actionsToGetTo.put(t1, a);
					/*determine cost to go from cur node to this node*/
					int cost = 20;
					costToGetTo.put(t1, cost);
					t1.setDirection(cur.root.getDirection());
				}
				//forward
				Terrain t2 = doForwardMove(world, cur.root);
				if(t2 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t2);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("move forward");
					actionsToGetTo.put(t2, a);
					/*determine cost to go from cur node to this node*/
					int cost = t2.getComplexity();
					costToGetTo.put(t2, cost);
					t2.setDirection(cur.root.getDirection());
				}
				//turn right, leap
				cur.root.setDirection(facing);//make sure we are facing right direction
				doTurn(cur.root, "right");
				Terrain t3 = doLeap(world, cur.root);
				if(t3 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t3);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("turn right");
					a.add("leap");
					actionsToGetTo.put(t3, a);
					/*determine cost to go from cur node to this node*/
					int cost = cur.root.getComplexity()/3 + 20;
					costToGetTo.put(t3, cost);
					t3.setDirection(cur.root.getDirection());
				}
				//turn right, forward
				cur.root.setDirection(facing);//make sure we are facing right direction
				doTurn(cur.root, "right");
				Terrain t4 = doForwardMove(world, cur.root);
				if(t4 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t4);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("turn right");
					a.add("move forward");
					actionsToGetTo.put(t4, a);
					/*determine cost to go from cur node to this node*/
					int cost = cur.root.getComplexity()/3 + t4.getComplexity();
					costToGetTo.put(t4, cost);
					t4.setDirection(cur.root.getDirection());
				}	
				//turn left, leap
				cur.root.setDirection(facing);//make sure we are facing right direction
				doTurn(cur.root, "left");
				Terrain t5 = doLeap(world, cur.root);
				if(t5 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t5);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("turn left");
					a.add("leap");
					actionsToGetTo.put(t5, a);
					/*determine cost to go from cur node to this node*/
					int cost = cur.root.getComplexity()/3 + 20;
					costToGetTo.put(t5, cost);
					t5.setDirection(cur.root.getDirection());
				}
				//turn left, forward
				cur.root.setDirection(facing);//make sure we are facing right direction
				doTurn(cur.root, "left");
				Terrain t6 = doForwardMove(world, cur.root);
				if(t6 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t6);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("turn left");
					a.add("move forward");
					actionsToGetTo.put(t6, a);
					/*determine cost to go from cur node to this node*/
					int cost = cur.root.getComplexity()/3 + t6.getComplexity();
					costToGetTo.put(t6, cost);
					t6.setDirection(cur.root.getDirection());
				}
				//turn right, turn right(basically doing a 180,) leap
				cur.root.setDirection(facing);//make sure we are facing right direction
				doTurn(cur.root, "right");
				doTurn(cur.root, "right");
				Terrain t7 = doLeap(world, cur.root);
				if(t7 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t7);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("turn right");
					a.add("turn right");
					a.add("leap");
					actionsToGetTo.put(t7, a);
					/*determine cost to go from cur node to this node*/
					int cost = 2*(cur.root.getComplexity()/3) + 20;
					costToGetTo.put(t7, cost);
					t7.setDirection(cur.root.getDirection());
				}
				//turn right, turn right, forward
				cur.root.setDirection(facing);//make sure we are facing right direction
				doTurn(cur.root, "right");
				doTurn(cur.root, "right");
				Terrain t8 = doForwardMove(world, cur.root);
				if(t8 != null){
					/*add t1 to potential nodes we can land on*/
					potentialTerrains.add(t8);
					/*list actions we must take to get here*/
					LinkedList<String> a = new LinkedList<String>();
					a.add("turn right");
					a.add("turn right");
					a.add("move forward");
					actionsToGetTo.put(t8, a);
					/*determine cost to go from cur node to this node*/
					int cost = 2*(cur.root.getComplexity()/3) + t8.getComplexity();
					costToGetTo.put(t8, cost);
					t8.setDirection(cur.root.getDirection());
				}
				
				/*
				 * Go through create children nodes using each Terrain in
				 * potentialTerrains
				 */
				for (int i = 0; i < potentialTerrains.size(); i++) {
					/* n is a child node of cur node */
					Node n = new Node(potentialTerrains.get(i));

					/* Set values for n */
					n.parent = cur;
					n.costToGetHere = n.parent.costToGetHere + costToGetTo.get(n.root);
					//TODO: have user specify heuristic to use, currently it uses heuristic 1 by default
					n.heuristicCost = calculateHeuristic(world, n.root, 1);
					n.totalCost = n.costToGetHere + n.heuristicCost;
					n.actions = actionsToGetTo.get(n.root);
					
					/*add n to our priority queue*/
					pq.add(n);

					cur.children.add(n);
				}

				/* cur node has been fully expanded, add it to closed */
				closed.add(cur);
				nodesExpanded++;
			}
		}
		
		/*After we exit the while loop, cur is set to be the goal node*/
		/*We need to traverse it back to the starting node and keep track of the actions we took to get to each node*/
		int numActions = 0;
		Stack<LinkedList<String>> actions = new Stack<LinkedList<String>>();
		while(cur != null){
			actions.push(cur.actions);
			numActions += cur.actions.size();
			cur = cur.parent;
		}
		
		/*print out the actions taken*/
		System.out.println("Number Actions Taken: " + numActions);
		while(true){
			LinkedList<String> a = actions.pop();
			for(int i = 0; i < a.size(); i++){
				System.out.println(a.get(i));
			}
			
			if(actions.empty()){
				break;
			}
		}
	}

	/**
	 * Returns the heuristic value of the given Terrain t on the given world
	 * 
	 * @param world
	 * @param t
	 * @return
	 */
	public int calculateHeuristic(NewWorld world, Terrain t, int num) {
		int h = 0;
		int terrainRow = t.getRow();
		int terrainCol = t.getCol();
		int goalRow = world.goalRow;
		int goalCol = world.goalColumn;

		switch (num) {
		case 1:
			h = 0;
			break;
		case 2:
			h = Math.min(Math.abs(terrainRow - goalRow), Math.abs(terrainCol - goalCol));
			break;
		case 3:
			h = Math.max(Math.abs(terrainRow - goalRow), Math.abs(terrainCol - goalCol));
			break;
		case 4:
			h = Math.abs(goalRow - terrainRow) + Math.abs(goalCol - terrainCol);
			break;
		case 5:
			h = Math.abs(goalRow - terrainRow) + Math.abs(goalCol - terrainCol) + t.getComplexity() / 3;
			break;
		case 6:
			h = 3 * (Math.abs(goalRow - terrainRow) + Math.abs(goalCol - terrainCol) + t.getComplexity() / 3);
			break;

		}

		return h;
	}

	/**
	 * Attempts to move the robot 3 spaces forward.
	 * 
	 * @return The terrain robot will be on after leaping from t, null if move cant be made
	 */
	public Terrain doLeap(NewWorld world, Terrain t) {
		/* row column of terrain */
		int row = t.getRow();
		int col = t.getCol();

		/* Direction of robot */
		String dir = t.getDirection();

		/* max dimensions of world */
		int worldRow = world.row;
		int worldCol = world.column;
		
		Terrain terrain = null;

		/* check if you can leap */
		if (dir.equals(NORTH)) {
			if (row >= 3) {
				int newrow = row - 3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				terrain = aterrain.get(col);
			}
		} else if (dir.equals(EAST)) {
			if (worldCol - col >= 3) {
				int newcol = col + 3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				terrain = aterrain.get(newcol);
			} 
		} else if (dir.equals(SOUTH)) {
			if (worldRow - row >= 3) {
				int newrow = row + 3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				terrain = aterrain.get(col);
			}
		} else if (dir.equals(WEST)) {
			if (row >= 3) {
				int newcol = col - 3;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				terrain = aterrain.get(newcol);
			}
		} 

		return terrain;
	}

	/**
	 * Attempts to move the robot 1 space forward
	 * 
	 * @return terrain robot will be on after moving forward from t, null if move cant be made
	 */
	public Terrain doForwardMove(NewWorld world, Terrain t) {
		/* row column of terrain */
		int row = t.getRow();
		int col = t.getCol();

		/* Direction of robot */
		String dir = t.getDirection();

		/* max dimensions of world */
		int worldRow = world.row;
		int worldCol = world.column;

		Terrain terrain = null;
		
		/* check if you can move forward one */
		if (dir.equals(NORTH)) {
			if (row >= 1) {
				int newrow = row - 1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				terrain = aterrain.get(col);
			}
		} else if (dir.equals(EAST)) {
			if (worldCol - col >= 1) {
				int newcol = col + 1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				terrain = aterrain.get(newcol);
			}
		} else if (dir.equals(SOUTH)) {
			if (worldRow - row >= 1) {
				int newrow = row + 1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(newrow);
				terrain = aterrain.get(col);
			}
		} else if (dir.equals(WEST)) {
			if (row >= 1) {
				int newcol = col - 1;
				HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
				HashMap<Integer, Terrain> aterrain = terrains.get(row);
				terrain = aterrain.get(newcol);
			} 
		}

		return terrain;
	}

	/**
	 * Attempts to turn the robot to face a different direction
	 * 
	 * @return true cost of turning
	 */
	public int doTurn(Terrain t, String dir) {
		int cost = -1;

		String direction = t.getDirection();

		if (direction.equals(NORTH)) {
			if (dir.equals("right")) {
				t.setDirection(EAST);
				cost = t.getComplexity()/3;
			} else if (dir.equals("left")) {
				t.setDirection(WEST);
				cost = t.getComplexity()/3;
			}
		} else if (direction.equals(EAST)) {
			if (dir.equals("right")) {
				t.setDirection(SOUTH);
				cost = t.getComplexity()/3;
			} else if (dir.equals("left")) {
				t.setDirection(NORTH);
				cost = t.getComplexity()/3;
			}
		} else if (direction.equals(SOUTH)) {
			if (dir.equals("right")) {
				t.setDirection(WEST);
				cost = t.getComplexity()/3;
			} else if (dir.equals("left")) {
				t.setDirection(EAST);
				cost = t.getComplexity()/3;
			}
		} else if (direction.equals(WEST)) {
			if (dir.equals("right")) {
				t.setDirection(NORTH);
				cost = t.getComplexity()/3;
			} else if (dir.equals("left")) {
				t.setDirection(SOUTH);
				cost = t.getComplexity()/3;
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
		// array of moves

		return success;
	}
}
