package mästerprov1;
public class mp21 {

    public static void main(String[] args) {
        int[] u = {4,2,3};
        int x = 2;
        int f = count_ways(u,x,3);
        System.out.println(f);
    }

    public static int count_ways(int[] u, int x, int n){
        //if(arraySum(u) < x) return 0;
        //if(arraySum(u) == x) return 1;
        int[][] m = new int[n][x+1];
        int count = 0;
        
        //antal sätt att bilda 0 med vilken mängd som helst är = 1
        for(int i = 0; i < n; i++){
            m[i][0] = 1;
        }

        //antal sätt att bilda talet i enda upp till x med första elementet i u är antingen 1 eller 0
        for(int i = 0; i <= x; i++){
            if(u[0] >= i){
                m[0][i] = 1;
            }
            else{
                m[0][i] = 0;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j <= x; j++){
                int total = 0;
                if(u[i] >= j){
                    for(int p = j; p >= 0; p--){
                        total += m[i-1][p];
                    }
                }
                else{
                    for(int p = 0; p <= u[i]; p++){
                        total += m[i-1][j-p];
                    }
                }
                
                m[i][j] = total;
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<=x; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

        count = m[n-1][x];
        return count;
    }

    public static int arraySum(int[] a){
        int sum = 0;
        for(int i = 0; i<a.length; i++){
            sum += a[i];
        }
        return sum;
    }
}
