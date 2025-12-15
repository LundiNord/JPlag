

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
    
    
    
    public static void analizar(Reta[] a1, int k){
        int max=a1[0].fi;
        int count=1;
        Arrays.sort(a1);
        for(int i=0; i<a1.length; i++){
            if(max<=a1[i].in && a1[i].fi<=k){
                count++;
                max=a1[i].fi;
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
       analizar(retas, fim);
         
    }
    
}
