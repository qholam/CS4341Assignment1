
public class Terrian {	
	Boolean start; /*Determine if this terrain is a starting point*/
	Boolean navigable; /*Determine if this terrain is navigable*/
	Boolean goal; /*Determine if this terrain is a goal*/
	int complexity; /*complexity of the terrain*/
	int row, col; /*position of the terrain within the World grid*/
	Robot robot;
	int cost; /*cost of terrain*/
	
	/*Initialize a Terrain, values need to be set, they are initially set to be either false or -1*/
	public Terrian(){
		start = false;
		navigable = false;
		goal = false;
		complexity = -1;
		row = -1;
		col = -1;
		robot = null;
		cost = -1;
	}

	public Boolean getStart() {
		return start;
	}

	public void setStart(Boolean start) {
		this.start = start;
		if(start){
			goal = false;
			navigable = true; 
		}
	}
	
	public Boolean getGoal() {
		return goal;
	}

	public void setGoal(Boolean goal) {
		this.goal = goal;
		
		if(goal){
			start = false;
			navigable = true;
		}
	}
	
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

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	/*Getters and setters for cost*/
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
