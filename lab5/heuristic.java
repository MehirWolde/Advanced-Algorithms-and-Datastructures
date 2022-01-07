import java.util.ArrayList;

public class heuristic{
    static Kattio io = new Kattio(System.in, System.out);

    static int roles;
    static int scenes;
    static int actors;
    static ArrayList<Integer>[] rolesV;
    static ArrayList<Integer>[] scenesV;
    static ArrayList<Integer>[] actorsV;

    @SuppressWarnings("unchecked")

    //Läser indata från rollbesättningsproblemet och lagrar dessa i variabler och arralist vektorer
    public static void input(){
        roles = io.getInt();
        scenes = io.getInt();
        actors = io.getInt();
        //+1 på allt eftersom vi vill börja på index 1
        rolesV = new ArrayList[roles+1];
        scenesV = new ArrayList[scenes+1];
        
        //vi får max ha n-1 superskådisar men med 1 indexering måste vi ha n+1 extra platser
        actorsV = new ArrayList[actors+roles+1];

        //läser in alla roller och vilka skådespelare som kan spela dem
        for(int i = 1; i <= roles; i++){
            int x = io.getInt();
            rolesV[i] = new ArrayList<Integer>();
            for(int j = 0; j < x; j++){
                int y = io.getInt();
                rolesV[i].add(y);
            }
        }
        
        //läser in alla scener och vilka roller som ingår i dem
        for(int i = 1; i <= scenes; i++){
            int x = io.getInt();
            scenesV[i] = new ArrayList<Integer>();
            for(int j = 0; j < x; j++){
                int y = io.getInt();
                scenesV[i].add(y);
            }
        }
        io.flush();
    }

    //funktion som returnerar false ifall 2 roller är i samma scen
    public static boolean sameScene(int a, int b){
        for(int i = 1; i < scenesV.length; i++){
            if(scenesV[i].contains(a) && scenesV[i].contains(b)){
                return false;  
            }  
        }
        return true;
    }

    //kollar om en actor har en roll som spelar i samma scen som role, checkar även extra krav för divorna
    public static boolean checkActor(int actor, int role){
        if(actor == 1 || actor == 2){
            for(int i = 0; i < actorsV[1].size(); i++){
                if(!sameScene(actorsV[1].get(i), role)){
                    return false;
                }
            }
            for(int i = 0; i < actorsV[2].size(); i++){
                if(!sameScene(actorsV[2].get(i), role)){
                    return false;
                }
            }
        }
        else{
            for(int i = 0; i < actorsV[actor].size(); i++){
                //System.out.println("a = " + actor + " role: " + role);
                if(!sameScene(actorsV[actor].get(i), role)){
                    return false;
                }
            }
        }
        return true;
    }

    //skapar en lista för varje actors roller
    public static void initActors (){
        for (int i = 0; i < actorsV.length; i++) {
            actorsV[i] = new ArrayList<Integer>();
        }
    }

    //löser indata instansen genom att använda en första bästa heuristik
    public static void solution(){
        ArrayList <Integer> firstDiva = new ArrayList<Integer>();
        ArrayList <Integer> secondDiva = new ArrayList<Integer>();
        initActors();

        for (int i = 1; i < rolesV.length; i++) {
            if(rolesV[i].contains(1)){ 
                firstDiva.add(i);
            }
            if(rolesV[i].contains(2)){
                secondDiva.add(i);
            } 
        }

        for (int i = 0; i < firstDiva.size(); i++) {  
            for (int j = 0; j < secondDiva.size(); j++) { 
                if(sameScene((firstDiva.get(i)), (secondDiva.get(j)))){
                    actorsV[1].add(firstDiva.get(i));
                    actorsV[2].add(secondDiva.get(j)); 
                    rolesV[firstDiva.get(i)].clear();
                    rolesV[secondDiva.get(j)].clear(); 
                    break;
                }
            }
            if(!actorsV[1].isEmpty()){
                break;
            }
        }

        //hittar den första bästa skådespelaren för möjliga roller
        for (int i = 1; i < rolesV.length; i++) {
            for (int j = 0; j < rolesV[i].size(); j++) {
                int currActor = rolesV[i].get(j);     
                if(checkActor(currActor, i)){
                    actorsV[currActor].add(i);
                    rolesV[i].clear(); 
                }
            }
        }

        //stoppar in superskådisar på dem roller som inte är besatta än
        int superActors = actors+1;
        for (int i = 1; i <= roles; i++) {
            if(!rolesV[i].isEmpty()){
                actorsV[superActors++].add(i);
            }
        }

    }

    //printar ut lösningen enligt formatet givet i uppgiften
    public static void printSolution(){
        int totActors = 0; 
        for (int i = 1; i < actorsV.length; i++) {
            if(!actorsV[i].isEmpty()){
                totActors++;
            }
        }
        System.out.println(totActors);
        for (int i = 1; i < actorsV.length; i++) {
            if(!actorsV[i].isEmpty()){
                System.out.print(i + " " + actorsV[i].size());
                for (int j = 0; j < actorsV[i].size(); j++) {
                    System.out.print(" " + (actorsV[i].get(j)));
                }
                System.out.println();
            }
        }
    }



    public static void main(String[] args) {
        input();
        //System.out.println("Solution:");
        solution();
        printSolution();
    }
}