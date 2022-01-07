package mästerprov1;
import java.util.Arrays;

public class mp22 {
    public static void main(String[] args) {
        int[] u = {1,0,4};
        print_all_ways(u, 2, 3);
    }

    public static void print_all_ways(int[] u, int x, int n){
        int[] calc = new int[n];

        //System.out.println(Arrays.toString(calc));
        for(int i = 0; i < n; i++){
            /*if(i>= n){
                System.exit(1);
            }*/

            while(calc[i] < u[i]){
                calc[i]++;
                //System.out.println(Arrays.toString(calc));

                if(arraySum(calc) == x){
                    print_array(calc);
                    break;
                }
                else if(i > 0){
                    i = 0;
                }
            }
            calc[i] = 0;
            //limit++;
        }
    }

    public static void print_array(int[] u){
        for(int i = 0; i < u.length-1; i++){
            System.out.print(u[i] + "+");
        }
        System.out.println(u[u.length-1]);
    }

    public static int arraySum(int[] a){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];
        }
        return sum;
    }
}
