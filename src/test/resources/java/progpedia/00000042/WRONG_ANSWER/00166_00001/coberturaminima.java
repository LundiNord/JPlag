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
		return 0;
	}
	
}



class coberturaminima{
	public static void main(String args[]) {
		
		Scanner in = new Scanner (System.in);
		
		int m = in.nextInt(); //segmento [o,m] que quero cobrir
		int n = in.nextInt(); //numero de segmentos
		Intervalo v[] = new Intervalo [n];
		int dif[] = new int [n];
		int l=0,r=0;
		
		for (int i = 0;i<n;i++){ //ler n segmentos
			v[i] = new Intervalo(in.nextInt(),in.nextInt());
			dif[i] = m - v[i].coordenadar;
		}
		Arrays.sort(v);
		
		/*for (int i = 0;i<n;i++){ 
			System.out.println(v[i].coordenadal +" " +v[i].coordenadar);
		}
		*/

		int end = 0;
		int numsegmentos = 0;
		int i = 0 ; 
		while (end < m){
				if (v[i].coordenadal <= end && end < v[i].coordenadar && dif[i] < dif[i+1]){
					end = v[i].coordenadar;
					//System.out.println(v[i].coordenadal + " /// "+ v[i].coordenadar + " end " +end);
				}
				else end = v[i+1].coordenadar;
			
			numsegmentos++;
			i++;
		}
		System.out.println(numsegmentos);
	}
}	
			
