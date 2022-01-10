/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
    Kattio io;
    
    /*void readBipartiteGraph() {
	// Läs antal hörn och kanter
	int x = io.getInt();
	int y = io.getInt();
	int e = io.getInt();
	
	// Läs in kanterna
	for (int i = 0; i < e; ++i) {
	    int a = io.getInt();
	    int b = io.getInt();
	}
    }*/
    
    
    void writeFlowGraph() {
	int x = io.getInt();		//hämta antal hörn i x-delen
	int y = io.getInt();		//hämta antal hörn i y-delen
	int e = io.getInt();		//hämta antal kanter
	int v = (x+y+2), edges = (e+x+y), s = 1, t = v;
	
	// Skriv ut antal hörn och kanter samt källa och sänka
	io.println(v);
	io.println(s + " " + t);
	io.println(edges);

	//skriv ut alla kanter från källan till alla hörn i x
	for(int i = 2; i <= x+1; i++){
		io.println(1 + " " + i + " " + 1);
	}

	for (int i = 0; i < e; ++i) {
	    int a = io.getInt() +1, b = io.getInt()+1;
	    // Kant från a till b med kapacitet c
	    io.println(a + " " + b + " " + 1);
	}

	for(int i = x+2; i <=v-1; i++){
		io.println(i + " " + t + " " + 1);
	}
	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
	io.flush();
	
	v = io.getInt();
	s = io.getInt();
	t = io.getInt();
	int totflow = io.getInt();
	e = io.getInt();

	io.println(x + " " + y);
	io.println(totflow);

	for(int i = 0; i <e;i++){
		int a = io.getInt();
		int b = io.getInt();
		int c = io.getInt();
		if(a != s && b != t){
			io.println((a-1) + " " + (b-1));
		}
	}

	io.flush();

	// Debugutskrift
	System.err.println("Skickade iväg flödesgrafen");
    }
    
    
    void readMaxFlowSolution() {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int totflow = io.getInt();
	int e = io.getInt();
	
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int f = io.getInt();
	}
    }
    
    
    void writeBipMatchSolution() {
	int x = 17, y = 4711, maxMatch = 0;
	
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);
	
	for (int i = 0; i < maxMatch; ++i) {
	    int a = 5, b = 2323;
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}
	
    }
    
    BipRed() {
	io = new Kattio(System.in, System.out);
	
	//readBipartiteGraph();
	
	writeFlowGraph();
	
	//readMaxFlowSolution();
	
	//writeBipMatchSolution();

	// debugutskrift
	System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }
    
    public static void main(String args[]) {
	new BipRed();
    }
}

