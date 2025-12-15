
/**
 *
 * @author Bonifacio
 */
import java.util.*;

public class Inversoes {
    static int n, k;
    static int v[];
    static int contador;
   
    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);
        n = stdin.nextInt();
        int v[] = new int[n];
        for (int i = 0; i < n; i++) {
            k = stdin.nextInt();
            v[i] = k;
        }
        contador = 0;
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++){
                if (v[i] > v[j]){
                    contador++;
                }
            }
        }
        System.out.println(contador);
    }
}
