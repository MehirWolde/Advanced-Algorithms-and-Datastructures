import java.util.*;
/*
   * Java Implementation of Edmonds-Karp Algorithm *
   * By: Pedro Contipelli                          *

 Input Format:                                     (Sample Input)

 N , E         | (N total vertices , E total edges) |  4 5
 u1 , v1 , c1  |                                 |  0 1 1000
 u2 , v2 , c2  | Each line u , v , c represents  |  1 2 1
 u3 , v3 , c3  | an edge in the graph from node  |  0 2 1000
  ...          | u to node v with capacity C     |  1 3 1000
 uE , vE , cE  |                                 |  2 3 1000
 
 Nodes 0 and N-1 are assumed to be the source and sink (respectively).
*/

public class FlowSolution {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int vertices = io.getInt();
		int source = io.getInt();
		int sink = io.getInt();
		int edges = io.getInt();

		Node[] graph = new Node[vertices+1];

		// Initialize each node
		for (int i = 1; i <= vertices; i++)
			graph[i] = new Node();

		// Initialize each edge
		for (int i = 0; i < edges; i++) {
			int u = io.getInt();
			int v = io.getInt();
			int c = io.getInt();
			// io.println(u + " " + v + " " + c);
			// Note edge "b" is not actually in the input graph
			// It is a construct that allows us to solve the problem
			Edge a = new Edge(u, v, 0, c);
			Edge b = new Edge(v, u, 0, 0);

			// Set pointer from each edge "a" to
			// its reverse edge "b" and vice versa
			a.setReverse(b);
			b.setReverse(a);

			graph[u].edges.add(a);
			graph[v].edges.add(b);
		}
		io.println();
		int maxFlow = 0;
		while (true) {
			// Parent array used for storing path
			// (parent[i] stores edge used to get to node i)
			Edge[] parent = new Edge[vertices+1];

			LinkedList<Node> q = new LinkedList<>();
			q.add(graph[source]);

			// BFS finding shortest augmenting path
			while (!q.isEmpty()) {
				Node curr = q.remove(0);

				// Checks that edge has not yet been visited, and it doesn't
				// point to the source, and it is possible to send flow through it.
				for (Edge e : curr.edges)
					if (parent[e.b] == null && e.b != source && e.capacity > e.flow) {
						parent[e.b] = e;
						q.add(graph[e.b]);
					}
			}

			// If sink was NOT reached, no augmenting path was found.
			// Algorithm terminates and prints out max flow.
			if (parent[sink] == null)
				break;

			// If sink WAS reached, we will push more flow through the path
			int pushFlow = Integer.MAX_VALUE;

			// Finds maximum flow that can be pushed through given path
			// by finding the minimum residual flow of every edge in the path
			for (Edge e = parent[sink]; e != null; e = parent[e.a])
				pushFlow = Math.min(pushFlow, e.capacity - e.flow);

			// Adds to flow values and subtracts from reverse flow values in path
			for (Edge e = parent[sink]; e != null; e = parent[e.a]) {
				e.flow += pushFlow;
				e.reverse.flow -= pushFlow;
			}

			maxFlow += pushFlow;
		}
		int count = 0;
		io.println(vertices);
		io.println(source + " " + sink + " " + maxFlow);
		for (Node n : graph) {
			if(n == null){
				continue;
			}
			for (Edge f : n.edges) {
				if (f.flow > 0) {
					count++;
				}
			}
		}
		io.println(count);
		for (Node n : graph) {
			if(n == null){
				continue;
			}
			for (Edge f : n.edges) {
				if (f.flow > 0) {
					io.println(f.a + " " + f.b + " " + f.flow);
				}
			}
		}

		io.flush();
		io.close();
	}
}

// No explicit constructor is necessary :P

class Node {

	// List of edges also includes reverse edges that
	// are not in original given graph (for push-back flow)
	LinkedList<Edge> edges = new LinkedList<>();

}

class Edge {

	int a, b, flow, capacity;
	Edge reverse;

	public Edge(int a, int b, int flow, int capacity) {
		this.a = a;
		this.b = b;
		this.flow = flow;
		this.capacity = capacity;
	}

	public void setReverse(Edge e) {
		reverse = e;
	}

}
