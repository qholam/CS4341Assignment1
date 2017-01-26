
public class Terrain {	
	Boolean start; /*Determine if this terrain is a starting point*/
	Boolean navigable; /*Determine if this terrain is navigable*/
	Boolean goal; /*Determine if this terrain is a goal*/
	int complexity; /*complexity of the terrain*/
	int row, col; /*position of the terrain within the World grid*/
	Robot robot;
	int cost; /*cost of terrain*/ 
	
	/*Initialize a Terrain, values need to be set, they are initially set to be either false or -1*/
	public Terrain(){
		start = false;
		navigable = false;
		goal = false;
		complexity = -1;
		row = -1;
		col = -1;
		robot = null;
		cost = -1;
	}

	/**
	 * Get the robot on this terrain.
	 * @return Robot on terrain or null if there is no robot on the terrain
	 */
	public Robot getRobot() {
		return robot;
	}

	/**
	 * Place the given robot on this terrain
	 * @param robot
	 */
	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	/**
	 * Determines if this terrain is a starting terrain
	 * @return
	 */
	public Boolean getStart() {
		return start;
	}

	/**
	 * Sets this terrain to be a start tile or not
	 * @param start
	 */
	public void setStart(Boolean start) {
		this.start = start;
		if(start){
			goal = false;
			navigable = true; 
		}
	}
	
	/**
	 * Determines if this terrain is a goal terrain
	 * @return
	 */
	public Boolean getGoal() {
		return goal;
	}

	/**
	 * Sets this terrain to be a goal or not
	 * @param goal
	 */
	public void setGoal(Boolean goal) {
		this.goal = goal;
		
		if(goal){
			start = false;
			navigable = true;
		}
	}
	
	/**
	 * Determines if this terrain is navigable.
	 * @return
	 */
	public Boolean getNavigable() {
		return navigable;
	}

	public void setNavigable(Boolean navigable) {
		this.navigable = navigable;
		
		if(!navigable){
			start = false;
			goal = false;
		}
	}

	/**
	 * Set whether this terrain is navigable or not
	 * @return
	 */
	public int getComplexity() {
		return complexity;
	}

	/**
	 * Set the complexity of this terrain(1-9)
	 * @param complexity
	 */
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	/**
	 * Get the row in the World this terrain is located in
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set the row of this terrain relative to the World
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Get the column in the World this terrain is located in
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Set the column of this terrain relative to the World
	 * @param row
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Get the cost of moving into this terrain
	 * @return
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Set the cost of moving into this terrain
	 * @param cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Generate the hashcode for a given Terrain Object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + complexity;
		result = prime * result + row;
		return result;
	}

	/**
	 * Determines whether this Terrain Object is equal to the given Terrain Object
	 */
	@Override
	public boolean equals(Object obj) {
		Terrain other = (Terrain) obj;
		if (this.col == other.getCol() && this.row == other.getRow() && this.cost == other.getCost())
			return true;
		return false;
	}
}
