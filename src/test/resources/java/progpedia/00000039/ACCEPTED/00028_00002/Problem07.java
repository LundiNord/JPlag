import java.util.*;
import java.math.*;


public class Problem07 {

	static int factorial(int n){ 
		if(n == 0) 
			return 1; 
		else 
			return n * factorial(n-1); 
	}

	static void bsearch(int l, int h, int[] p, int d){
		while(l<h){
			int m= l+(h-l)/2;
			int dif=0;
			if(d==p[m]){
				System.out.println(p[m]);
				break;
			}
			if(d < p[m]){
				h=m;
				if(h-l<=1){
					//System.out.println(l + " " + p[l] + " " + h + " "+ p[h] + " " + d);
					if(Math.abs(d-p[l])>Math.abs(d-p[h])){
						System.out.println(Math.abs(p[h]));
						break;
					}
					if(Math.abs(d-p[l])==Math.abs(d-p[h])){
						System.out.println(Math.abs(p[l])+" "+Math.abs(p[h]));
						break;
					}
					else{
						System.out.println(Math.abs(p[l]));
						break;
					}
				}
				
			}
			else{
				l=m;
				if(h-l<=1){
					//System.out.println(l + " " + p[l] + " " + h + " "+ p[h] + " " + d);
					if(Math.abs(d-p[l])>Math.abs(d-p[h])){
						System.out.println(Math.abs(p[h]));
						break;
					}
					if(Math.abs(d-p[l])==Math.abs(d-p[h])){
						System.out.println(Math.abs(p[l])+" "+Math.abs(p[h]));
						break;
					}
					else{
						System.out.println(Math.abs(p[l]));
						break;
					}
				}
				
			}
			
		}
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tam = 0;
		tam = in.nextInt();
		int[] vetor = new int[tam];
		for(int i=0; i<tam;i++){
			vetor[i]=in.nextInt();
		}


		int[] resultado = new int[tam*(tam-1)/2];
		int c = 0;
		for(int i=0; i<tam; i++){
			for(int j=i+1; j<tam; j++){
				resultado[c]=vetor[i]+vetor[j];				
				c++;
			}
		}
		Arrays.sort(resultado);

		for(int i = 0; i<resultado.length; i++){
		}

		int nperg = in.nextInt();
		for(int i=0; i<nperg; i++){
			int temp = in.nextInt();
			bsearch(0, resultado.length-1, resultado, temp);
		}

	}

}
