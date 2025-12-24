import java.util.*;
import java.math.*;
public class prob2{
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int v[] = new int[n];
        for(int i = 0; i<n; i++) v[i] = in.nextInt();
        System.out.println(Maxima_Soma(v));
    }
    
    public static int Maxima_Soma(int[] v) {
        int maximo_atual = 0;
        int maximo_total = 0;
        if(v.length==1) maximo_total = v[0];
        else{
            for (int i = 0; i< v.length; i++) {
                maximo_atual = maximo_atual + v[i];
                if (maximo_atual > 0)
                    maximo_total = Math.max(maximo_total, maximo_atual);
                else
                    maximo_atual = 0;
            }
        }
        return maximo_total;
    }
}