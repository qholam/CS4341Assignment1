import java.util.LinkedList;

public class Node {
	Terrain root;
	LinkedList<Node> children;
	Node parent;
	int costToGetHere;
	int heuristicCost;
	int totalCost;
	LinkedList<String> actions; //the actions needed to get from parent node to this node
	public Node(Terrain t){
		root = t;
	}
}
