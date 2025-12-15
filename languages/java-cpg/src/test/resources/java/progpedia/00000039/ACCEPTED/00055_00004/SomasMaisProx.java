import java.util.*;
import java.math.*;

public class SomasMaisProx {
	
	public static int binarySearch(int[] v, int p, int low, int high){
		int middle = low + (high-low)/2;
		if(high-low==1){
			int pr = p-v[low];
			int se = v[high]-p;
			if(pr<se)
				return low;
			else if(se<pr)
				return high;
			else
				return high;
		}
		else if(v[middle]==p)
			return middle;
		else if(v[middle]<p)
			return binarySearch(v, p, middle, high);
		else 
			return binarySearch(v, p, low, middle);
	}
	
	public static int[] encontrarSoma(int n, int p, int[] v){
		int dif; //diferenca mais pequena
		int difp; //diferenca provisoria
		int dife; //diferenca estimada
		int soma; //soma provisoria
		int soma1=-1; //soma mais proxima
		int soma2=-1; //soma mais proxima
		int res[] = new int[2]; //vetor com a/as soma/somas mais proxima/proximas
		res[0] = -1; 
		res[1] = -1;
		int contador=0;
		
		if(p<=v[1]) //se o numero for menor que o primeiro elemento
			res[0]=v[0]+v[1]; //retorna a soma dos dois primeiros elementos
		
		else{
			int indice = binarySearch(v, p, 0, n-1);	
			
			res[0]=v[indice]+v[0]; //soma mais proxima actual
			dif=Math.abs(p-soma1); //menor diferenca actual

			for(int j=0; j<=indice; j++){
				for(int k=j+1; k<=indice; k++){
					if(k==n)
						break;
						
					soma=v[j]+v[k]; //soma o ultimo elemento mais proximo da pergunta com um dos anteriores
					difp = Math.abs(p-soma); //calcula a diferenca
					dife = p-soma;
					
					if(dife<0 && difp>dif)
						break;
					
					if(difp==0){
						//System.out.println("ENCONTREI A PERGUNTA!");
						res[0]=soma;
						res[1]=-1;
						return res;
					}

					if(difp==dif && soma!=res[0]){ //se a diferenca for igual
						//System.out.println(difp + "=" + dif);
						res[1]=soma;
					}
					else if(difp<dif){//se a diferenca for menor
						//System.out.println(difp + "<" + dif);
						res[0]=soma;
						dif=difp;
						res[1]=-1;
					}
					
				}
				contador=0;
			}
			
			if(res[0]>res[1] && res[1]!=-1){
				int aux = res[1];
				res[1]=res[0];
				res[0]=aux;
			}
		}	
	
		return res;	
	}	
		
	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		int n = inp.nextInt();
		int[] v = new int[n];
		int[] res = new int[2];
		
		for(int i=0; i<n; i++)
			v[i]=inp.nextInt();
		
		int np = inp.nextInt();
		
		Arrays.sort(v);
		
		for(int i=0; i<np; i++){
			res=encontrarSoma(n, inp.nextInt(), v);
			if(res[1]==-1) //se nao houver dois resultados
				System.out.println(res[0]); //imprime um
			else
				System.out.println(res[0] + " " + res[1]); //se nao imprime os dois
		}
		
	}
}
	
