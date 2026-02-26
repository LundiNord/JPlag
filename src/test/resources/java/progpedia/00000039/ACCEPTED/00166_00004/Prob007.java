import java.util.*;


class Prob007{
	
	public static void main(String args []){
		Scanner stdin = new Scanner(System.in);
		
		int TamS = stdin.nextInt();
		
		int ConjS [] = new int [TamS];
		
		int somas [] = new int [TamS*TamS];
		
		for(int i=0; i < TamS; i++){
			ConjS[i] = stdin.nextInt();
		}
		
		int j = 0;
		
		for(int i = 0; i<TamS; i++){
			for(int c = 0; c<TamS; c++){
				if(ConjS[i] != ConjS[c]){	//Não somar o mesmo numero
					somas[j] = ConjS[i]+ConjS[c];	//Guardar todas as somas possiveis
					j++;
				}
			}
		}
		
		
		Arrays.sort(somas); //Ordenar
		
		//Remover zeros
		int [] somas2 = clear(somas);
		
		int TamP = stdin.nextInt();
		
		for(int i = 0; i<TamP;i++){
			bs(stdin.nextInt() , somas2); //Print com base no BS
			
		}
		
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
 
        int array [] = new int [c];
 
        for (int i=0; i < c; i++)
            array[i]=(Integer)l.get(i);
 
        return array;
    }
	
	
}