import java.util.*;
import java.util.Arrays;

class Intervalo  implements Comparable<Intervalo>{
    public int coordenadal;
    public int coordenadar;
    
   
   Intervalo(int l, int r){
	   coordenadal = l;
	   coordenadar = r;
   }

      public int compareTo(Intervalo intervalo){
		if (coordenadal < intervalo.coordenadal) return -1;
		if (coordenadal > intervalo.coordenadal) return +1;
		if (coordenadar < intervalo.coordenadar) return -1 ;
		if (coordenadar > intervalo.coordenadar) return +1;
		return 0;
	}
/*
	static int bsearch(Intervalo [] v , int low , int high , int key ) {
		int middle=0; 
		while ( low < high ) {
			middle = low + ( high - low )/2 ;
			if ( key == v[middle].coordenadal) {
				return middle;
			}
			else if ( key < v[middle].coordenadal ) {
				high = middle;
			}
			else{ low = middle+1 ;
			} 
		}
		return middle ; 
		}*/
	}
	

class coberturaminima{
	public static void main(String args[]) {
		
		Scanner in = new Scanner (System.in);
		
		int m = in.nextInt(); //segmento [o,m] que quero cobrir
		int n = in.nextInt(); //numero de segmentos
		Intervalo v[] = new Intervalo [n];
		int tamanho[] = new int [n];
		int l=0,r=0;
		
		for (int i = 0;i<n;i++){ //ler n segmentos
			v[i] = new Intervalo(in.nextInt(),in.nextInt());
			//System.out.println(v[i].coordenadal + " /// "+ v[i].coordenadar );
		}
		Arrays.sort(v);
		
		for (int i = 0;i<n;i++){ //ler n segmentos
			//System.out.println(v[i].coordenadal + " /// "+ v[i].coordenadar );
		}
		
		for (int i = 0;i<n;i++){ 
			tamanho[i] = v[i].coordenadar - v[i].coordenadal;
			//System.out.println(tamanho[i]);
		}
		
		for ( int i = 0 ; i < n-1 ; i++) {
			if ( v[i].coordenadal == v[i+1].coordenadal){
				if ( tamanho[i] < tamanho[i+1]) {
					v[i].coordenadar = v[i+1].coordenadar;
				}
				else v[i+1].coordenadar = v[i].coordenadar;
			}
			//System.out.println(v[i].coordenadal + " /// "+ v[i].coordenadar );
		}
		
		int end = 0;
		int numsegmentos = 0;
		int max = 0 ; 
		int controlo = 0 ;
		int i = 0 ; 
		int x = 0 ; 
		while (end <= m && i < n ){
				//int valor = bsearch(v,0,m,end);
				//System.out.println(v[valor].coordenadal + " /// "+ v[valor].coordenadar + " end " +end);
				if (v[i].coordenadal == end && end < v[i].coordenadar){
					end = v[i].coordenadar;
					numsegmentos++;
					//System.out.println("Igual end " + v[i].coordenadal + " /// "+ v[i].coordenadar + " end " +end + " ----> " + i);
				}
				else if ( v[i].coordenadal < end && end < v[i].coordenadar ) {
					x=0;
					for (int j = 0  ; j < n ; j++) {
						if ( v[j].coordenadal == end ) {
							end = v[j].coordenadar;
							//System.out.println("Inferior end " + v[j].coordenadal + " /// "+ v[j].coordenadar + " end " +end + " ----> " + j);
							break;
						}
						else x++;
					}
					if ( x == n ) {
					end = v[i].coordenadar;
					//System.out.println("Inferior end " + v[i].coordenadal + " /// "+ v[i].coordenadar + " end " +end + " ----> " + i);
					}
					numsegmentos++;
				}
				
			i++;
			//System.out.println("----> " + i); 
		}
		System.out.println(numsegmentos);
	}
	
			
}
