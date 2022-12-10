import java.util.Vector;

class Edge {
	String from;
	String m;
	String to;

	public Edge(String from, String m, String to) {
		this.from = from;
		this.m = m;
		this.to = to;
	}

	public String toString() {
		return from.toString() + " ---" + m.toString() + "--> " + to.toString() + "\n";
	}
}

public class Graph {
	static Vector<Edge> edges = new Vector<Edge>();

	public static void addEdge(String from, String m, String to) {
		Edge edge = new Edge(from, m, to);
		edges.add(edge);
	}

	public String toString() {
		String res = new String();
		for (Edge e : edges) {
			res += e + "\n";
		}
		return res;
	}
}
