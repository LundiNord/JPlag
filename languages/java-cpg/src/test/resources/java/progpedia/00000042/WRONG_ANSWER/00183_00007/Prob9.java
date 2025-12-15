
//package prob9;
import java.util.*;


class Reta implements Comparable<Reta> {
    Integer in;
    Integer fi;
    public Reta(Integer in, Integer fi){
        this.in= in;
        this.fi=fi;
    }
@Override
        public int compareTo(Reta q) {
            if (q.in.equals(in))
		return q.fi.compareTo(fi);
	
            else
                return in.compareTo(q.in);
    }
    
}
public class Prob9 {
    
    
    
    public static void analizar(Reta[] a1, int k, int p){
        Arrays.sort(a1);
        int max=0;
        int count=0;
        for(int i=0; i<p; i++){
            if(a1[i].in==max && a1[i].fi<=k){    // da mal apartir deste if!!!
                max=a1[i].fi;
                count++;
            
        }
        }
          System.out.println(count);
    }
    
    public static void main(String[] args) {
       Scanner le= new Scanner(System.in);
       int fim= le.nextInt();
       int n= le.nextInt();
       Reta retas[]= new Reta[n];
       for(int i=0; i<n; i++){
            retas[i]=new Reta (le.nextInt(), le.nextInt());
       }
       analizar(retas, fim, n);
         
    }
    
}
