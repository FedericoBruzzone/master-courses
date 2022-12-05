import java.util.Vector;

class Node<T, T2> {
	T from;
	T2 m;
	T to;
	
	public Node(T from, T2 m, T to) {
		this.from = from;
		this.m = m;
		this.to = to;
	}
	
	public String toString() {
		return from.toString() + " ---" + m.toString() + "--> " + to.toString();
	}
}

public class GraphCFG<T, T2> {
	Vector<Node<T, T2>> nodes;
	
	public GraphCFG() {
		nodes = new Vector<Node<T, T2>>();
	}
	
	public void addNode(T from, T2 m, T to) {
		Node<T, T2> node = new Node<T, T2>(from, m, to);
		nodes.add(node);
	}
	
	public String toString() {
		String res = new String();
		for (Node<T, T2> n : nodes) {
			res += n + "\n";
		}
		return res;
	}
}
