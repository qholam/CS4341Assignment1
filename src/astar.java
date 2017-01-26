import java.util.HashMap;

public class astar {
	/*
	 * Potentially store heuristic values in a hashmap where the key is the Terrain Object and the associated value is the heuristic cost for that Terrain.
	 * If we store it this way, we will need functions to generate these hashmaps. 
	 */
	HashMap<Terrain, Integer> heuristic1;
	HashMap<Terrain, Integer> heuristic2;
	HashMap<Terrain, Integer> heuristic3;
	HashMap<Terrain, Integer> heuristic4;
	HashMap<Terrain, Integer> heuristic5;
	HashMap<Terrain, Integer> heuristic6;
	
	/**
	 * Runs the simulation with given heuristic
	 * @param args
	 */
	public static void main(String[] args) {
		NewWorld world = new NewWorld(args);
	}

	/**
	 * Determines if the robot has reached the goal state
	 * @return
	 */
	public boolean complete() {
		boolean complete = false;

		// TODO: implement this

		return complete;
	}

	/**
	 * Attempts to move the robot 3 spaces forward.
	 * @return True if the leap was able to be executed, and false otherwise
	 */
	public boolean doLeap() {
		boolean success = false;

		// TODO: implement this

		return success;
	}

	/**
	 * Attempts to move the robot 1 space forward
	 * @return true if robot was moved forward
	 */
	public boolean doForwardMove() {
		boolean success = false;

		// TODO: implement this

		return success;
	}

	/**
	 * Attempts to turn the robot to face a different direction
	 * @return true if turn was made
	 */
	public boolean doTurn() {
		boolean success = false;

		// TODO: implement this

		return success;
	}

	/**
	 * Makes the best move currently available to the robot
	 */
	public boolean doBestMove() {
		boolean success = false;

		// TODO: implement this, this should call doForwardMove(), doTurn(), or
		// doLeap()

		return success;
	}
}
