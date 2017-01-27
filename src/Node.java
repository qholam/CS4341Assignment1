import java.util.LinkedList;

public class Node {
	int cost; 
	Terrain parent;
	LinkedList<Terrain> children;
	int row, col;
	Terrain root; 
	boolean goal;
	public Node(Terrain t){
		root = t;
		children = new LinkedList<Terrain>();
		row = t.getRow();
		col = t.getCol();
		cost = t.getComplexity();
		goal = t.getGoal();
	}
}
