import java.util.*;
public class prob10{
    
    public static int n;
    public static Encomenda enc[];
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        enc = new Encomenda[n];
        for(int i = 0; i<n; i++) enc[i] = new Encomenda(in.nextInt(), in.nextInt(), i);
        Arrays.sort(enc);
        for(int i = 0; i<n-1; i++)  System.out.print(enc[i].index + " ");
        System.out.println(enc[n-1].index);
    }
    
}

class Encomenda implements Comparable<Encomenda>{
        double dur;
        double mul;
        double racio;
        int index;
        
        Encomenda(double d, double m, int i){
            dur = d;
            mul = m;
            racio = mul/dur;
            index = i + 1;
        }
        
        @Override
        public int compareTo(Encomenda e){
            if(racio < e.racio) return 1;
            else if(racio == e.racio){
                if(index < e.index) return -1;
                else return 1;
            }
            else return -1;
        }
    }