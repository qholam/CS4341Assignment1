
public class Robot {
	/*Directions of robot on terrain*/
	final String NORTH = "north";
	final String SOUTH = "south";
	final String EAST = "east";
	final String WEST = "west";
	final String NONE = "none"; /*used when there is no robot on this terrain*/
	
	String direction; /*direction that robot on terrain is facing(north, south, west, or east)*/
	
	public Robot(){
		direction = NONE;
	}
	
	/*Getters and setters for direction*/
	public String getDirection() {
		return direction;
	}
	
	public void setDirectionNorth(){
		direction = NORTH;
	}
	
	public void setDirectionSouth(){
		direction =SOUTH;
	}
	
	public void setDirectionEast(){
		direction = EAST;
	}
	
	public void setDirectionWest(){
		direction = WEST;
	}
	
	public void setDirectionNone(){
		direction = NONE;
	}
}
