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
		if (coordenadar < intervalo.coordenadar) return -1;
		if (coordenadar > intervalo.coordenadar) return +1;
		return 0;
	}	
}



class coberturaminima{
	public static void main(String args[]) {
		
		Scanner in = new Scanner (System.in);
		
		int m = in.nextInt(); //segmento [o,m] que quero cobrir
		int n = in.nextInt(); //numero de segmentos
		Intervalo v[] = new Intervalo [n];
		int l=0,r=0;
		
		for (int i = 0;i<n;i++){ //ler n segmentos
			v[i] = new Intervalo(in.nextInt(),in.nextInt());
		}
		
		int menor = 10000;
		int maior = 0;
		int numsegmentos = 0;
		
		/*for (int i=0;i<n;i++){
			if (v[i].coordenadal < menor){
				menor = v[i].coordenadal;
				maior = v[i].coordenadar;
			}
			if (v[i].coordenadal==menor){
				if (v[i].coordenadar>maior){
					maior = v[i].coordenadar;
				}		
			}
		}
		numsegmentos++;*/
		
		while (maior < m){
			int melhor = maior;
			for (int i = 0;i<n;i++){
				if (v[i].coordenadal <= maior && melhor < v[i].coordenadar){
					melhor = v[i].coordenadar;
	//				System.out.println(v[i].coordenadal+" "+v[i].coordenadar);
				}
			}
		maior = melhor;
		numsegmentos++;
		}
		System.out.println(numsegmentos);
	}
}

