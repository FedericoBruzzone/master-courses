import java.util.Vector;

class Edge<T, M> {
	T from;
	M m;
	T to;
	
	public Edge(T from, M m, T to) {
		this.from = from;
		this.m = m;
		this.to = to;
	}
	
	public String toString() {
		return from.toString() + " ---" + m.toString() + "--> " + to.toString();
	}
}

public class Graph<T, M> {
	Vector<Edge<T, M>> edges;
	
	public Graph() {
		edges = new Vector<Edge<T, M>>();
	}
	
	public void addNode(T from, M m, T to) {
		Edge<T, M> node = new Edge<T, M>(from, m, to);
		edges.add(node);
	}
	
	public String toString() {
		String res = new String();
		for (Edge<T, M> n : edges) {
			res += n + "\n";
		}
		return res;
	}
}
