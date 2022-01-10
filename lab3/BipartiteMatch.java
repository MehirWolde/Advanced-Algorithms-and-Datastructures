package kattis;
/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */
import java.util.*;

public class BipartiteMatch {
    Kattio io = new Kattio(System.in, System.out);
    int bipX, bipY, edges, source = 1, sink, flowEdges = 0;
    Node[] graph;
    int maxFlow = 0;


    void readBipartiteGraph() {
        // Läs antal hörn och kanter
        bipX = io.getInt();
        bipY = io.getInt();
        edges = io.getInt();
        sink = bipX + bipY + 2;

        graph = new Node[sink + 1];

        // skapar varje punkt i grafen
		for (int i = 1; i <= sink; i++)
            graph[i] = new Node();

        // Läs in kanterna
        for (int i = 0; i < edges; i++) {
            int u = io.getInt();
            int v = io.getInt();

            // skapar en kant mellan u och v med kapacitet v
			Edge a = new Edge(u+1, v+1, 0, 1);

			//skapar en kant åt motsatta hållet som används vid restflödesberäkning
			Edge b = new Edge(v+1, u+1, 0, 0);

			//hjälper att hålla koll på (u,v) och (v,u)
			a.setReverse(b);
			b.setReverse(a);

			//lägg till kanterna i punkternas kantlista
			graph[u+1].edges.add(a);
			graph[v+1].edges.add(b);
        }
    }


    void writeFlowGraph() {
        
        for (int i = 2; i <= bipX+1; i++) {
            // skapar en kant mellan u och v med kapacitet v
			Edge a = new Edge(source, i, 0, 1);
			Edge b = new Edge(i, 1, 0, 0);

            //hjälper att hålla koll på (u,v) och (v,u)
			a.setReverse(b);
			b.setReverse(a);

            graph[source].edges.add(a);
			graph[i].edges.add(b);
        }

        for (int i = bipX+2; i < sink; i++) {
            // skapar en kant mellan u och v med kapacitet v
			Edge a = new Edge(i, sink, 0, 1);
			Edge b = new Edge(sink, i, 0, 0);

            //hjälper att hålla koll på (u,v) och (v,u)
			a.setReverse(b);
			b.setReverse(a);

            graph[i].edges.add(a);
			graph[sink].edges.add(b);
        }
        

        // Debugutskrift
        System.err.println("Skickade iväg flödesgrafen");
    }


    void readMaxFlowSolution() {
		while (true) {
			//array som används för att lagra vilken kant man tagit för att komma till nod x i parent[x]
			Edge[] parent = new Edge[sink+1];

			//skapa kö till bfs och lägg till källan som första element
			LinkedList<Node> q = new LinkedList<>();
			q.add(graph[source]);

			// BFS
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
    }


    void writeBipMatchSolution() {
        io.println();
        io.println(bipX + " " + bipY);
		io.println(maxFlow);
		//io.println(source + " " + sink + " " + maxFlow);
		for (Node n : graph) {
			if(n == null){
				continue;
			}
			for (Edge f : n.edges) {
				if (f.flow > 0 && f.a != source && f.b != sink) {
					io.println((f.a-1) + " " + (f.b-1) + " ");
				}
			}
		}
        // Var noggrann med att flusha utdata när matchningen skrivits ut!
        io.flush();
    }

    BipartiteMatch() {
        io = new Kattio(System.in, System.out);

        readBipartiteGraph();

        writeFlowGraph();

        readMaxFlowSolution();

        writeBipMatchSolution();

        // debugutskrift
        System.err.println("Bipred avslutar\n");

        // Kom ihåg att stänga ner Kattio-klassen
        io.close();
    }

    public static void main(String args[]) {
        new BipartiteMatch();
    }
}

class Node {

	// List of edges also includes reverse edges that
	// are not in original given graph (for push-back flow)
	LinkedList<Edge> edges = new LinkedList<>();

}

//kant-klass för att skapa kant-objekt med hörn, flöde, kapacitet och en bakåtkant
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