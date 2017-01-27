import java.util.LinkedList;

public class Node implements Comparable{
	Terrain root;
	LinkedList<Node> children;
	Node parent;
	int costToGetHere;
	int heuristicCost;
	int totalCost;
	LinkedList<String> actions; //the actions needed to get from parent node to this node
	
	public Node(Terrain t){
		root = t;
		actions = new LinkedList<String>();
		children = new LinkedList<Node>();
	}

	@Override
	public int compareTo(Object arg0) {
		Node other = (Node) arg0;
		
		return this.totalCost - other.totalCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		return true;
	}
}
