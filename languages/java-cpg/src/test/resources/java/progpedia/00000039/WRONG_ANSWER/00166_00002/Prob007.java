import java.util.*;


class Prob007{
	
	public static void main(String args []){
		Scanner stdin = new Scanner(System.in);
		
		int TamanhoS = stdin.nextInt();
		
		int ConjS [] = new int [TamanhoS];
		
		int total [] = new int [TamanhoS*TamanhoS];
		
		for(int i=0; i < TamanhoS; i++){
			ConjS[i] = stdin.nextInt();
		}
		
		int j = 0;
		
		for(int i = 0; i<TamanhoS; i++){
			for(int c = 0; c<TamanhoS; c++){
				if(ConjS[i] != ConjS[c]){	//Não somar o mesmo numero
					total[j] = ConjS[i]+ConjS[c];	//Guardar todas as somas possiveis
					j++;
				}
			}
		}
		
		//Remover zeros
		// int [] total2 = clear(somas);
		
		int TamanhoP = stdin.nextInt();
		int [] total2 = clear(total);
		
		for(int i = 0; i<TamanhoP;i++){
			bs(stdin.nextInt() , total2); 
			
		}
		
		
		Arrays.sort(total); //Ordenar
		
		
		
	}
	
	 public static void bs(int p, int [] somas){
        int low=0;
        int high=somas.length-1;
        int mid=0;
         
        while(high - low > 1){
            mid= low + (high-low)/2;
     
            if( somas[mid] < p)
                low=mid;
            else
                high=mid;
        }
 
            if (p - somas[low] == somas[high] - p && somas[low] != p && somas[high]!= p)
                System.out.println(somas[low]+" "+somas[high]);
            else{
                if( p - somas[low] < somas[high] - p)
                    System.out.println(somas[low]);
                else
                    System.out.println(somas[high]);
             
        }
    }
 
 
    public static int [] clear(int [] v){
		
        List<Integer> l= new ArrayList<Integer>();
        
		int c=0;
		
		
        for( int i=0;  i < v.length;  i++ ){
            if (v[i] != 0){
                l.add(v[i]);
                c++;
            }
        }
		int ar [] = new int [c]; //array
 
        for (int i=0; i < c; i++)
            ar[i]=(Integer)l.get(i);
 
        return ar;
        
    }
	
	
}