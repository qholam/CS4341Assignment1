
public class Robot {
	/*Directions of robot on terrain*/
	final String NORTH = "north";
	final String SOUTH = "south";
	final String EAST = "east";
	final String WEST = "west";
	
	String direction; /*direction that robot on terrain is facing(north, south, west, or east)*/
	
	public Robot(){
		direction = NORTH;
	}
	
	/**
	 * Get the direction the robot is facing
	 * @return
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Set the direction of the robot to be north
	 */
	public void setDirectionNorth(){
		direction = NORTH;
	}
	
	/**
	 * Set the direction of the robot to be south
	 */
	public void setDirectionSouth(){
		direction =SOUTH;
	}
	
	/**
	 * Set the direction of the robot to be east
	 */
	public void setDirectionEast(){
		direction = EAST;
	}
	
	/**
	 * Set the direction of the robot to be west
	 */
	public void setDirectionWest(){
		direction = WEST;
	}
}
