public class lab4 {
    public static void main(String[] args) {
       Kattio io = new Kattio(System.in, System.out);

       int v = io.getInt();
       int e = io.getInt();
       int c = io.getInt();
        
       //optimering ifall fler färger än hörn är angivna, ger enklaste fallet för en ja instans
       if(v <= c || e == 0){
           io.println("3 \n2 \n3");
           io.println("1 1\n1 2\n1 3");
           io.println("2 1 3");
           io.println("2 2 3");
           io.flush();
           return;
       }
       //låt hörn representera roller, lägg till två extra roller som spelas av divorna
       //varje kant representerar en scen mellan två hörn, lägg till 2*v kanter för att ha en scen med divorna och alla i v
       //vi låter antal färger representera skådespelare, lägg till två extra skådespelare för divorna
       int roles = v + 2;           
       int scenes = e + 2*v;
       int actors = c + 2;

       io.println(roles + "\n" + scenes + "\n" + actors);

       for(int i = 1; i <= v; i++)
       {
           io.print(c + " ");
           for(int j = 3; j <= actors; j++){
               io.print(j + " ");
           }
           io.println();
       }

       //sista två rollerna kan blir fast till diva 1 och 2
       io.println("1 1 \n1 2");

       //skapa en scen mellan divorna och alla hörn i grafen för att undvika isolerade roller
       for(int i = 1; i <= v; i++){
           io.println("2 " + (roles-1) + " " + i + " \n2 " + roles + " " + i);
       }

       //skapa scener mellan hörn i indata grafen
       for(int i = 0; i < e; i++){
           int a = io.getInt();
           int b = io.getInt();

           io.println("2 " + a + " " + b);
       }

       io.flush();

    }
}