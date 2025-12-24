import java.util.Scanner;
import java.util.HashMap;

/**
 *
 * @author Miguel
 */
public class Cigarras {

    public static void main(String lala[]){
        Scanner in = new Scanner(System.in);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        int inicial = in.nextInt();
        int a = inicial,b = in.nextInt();
        

        while(true){
            map.put(a,b);
            a = b;
            b = in.nextInt();
            if(b == 0) break;            
        }
        
        map.put(a,b);

        b = map.get(inicial);
        System.out.println(inicial);

        while(true){
            System.out.printf("%d\n", b);
            b = map.get(b);
            if(b == 0) break;
        }
    }
}