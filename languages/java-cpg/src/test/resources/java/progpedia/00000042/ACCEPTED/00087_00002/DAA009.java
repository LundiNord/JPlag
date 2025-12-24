import java.util.*;
import java.io.*;

//------------------------------------------------------------------------------

class Pares implements Comparable<Pares>{
    public int li, ri;
  
    Pares(int l, int r){
		li = l;
		ri = r;
    }

    @Override
    public int compareTo(Pares p) {
		if(li < p.li) return -1;
		if(li > p.li) return 1;
		return 0;
    }
}

//------------------------------------------------------------------------------

public class DAA009 {

	public static int m; //tamanho da distância
    public static int n; //número de segmentos a considerar
     
//------------------------------------------------------------------------------
	
	static void minCover(Pares v [],int m){
		int end = 0;
		int conta = 0;
		int index = 0;
		int best = 0;
		
		while(end < m){
			for(int i=index;i<n;i++){
				if(v[i].li <= end){
					if(v[i].ri > best){	
						best = v[i].ri;			
						index = i;						
					}
				}	
			}
			end = v[index].ri;
			conta ++;
		}
		System.out.println(conta);
	}

	//---------------------------------------------------------------

    static void readInput(){
		Scanner stdin = new Scanner(System.in);
	
		m = stdin.nextInt(); //tamanho da distância

    	n = stdin.nextInt(); //número de segmentos a considerar

		//segmentos
		Pares v[] = new Pares[n];
	
		for(int i=0;i<n;i++)
	    	v[i] = new Pares(stdin.nextInt(), stdin.nextInt());   

		Arrays.sort(v);
	
	    minCover(v,m);

    }
   
//------------------------------------------------------------------------------
    
    public static void main(String args[]) {
	
		readInput();

    }
}
