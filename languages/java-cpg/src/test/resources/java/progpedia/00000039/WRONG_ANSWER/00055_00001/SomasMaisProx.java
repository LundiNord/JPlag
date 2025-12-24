import java.util.*;
import java.math.*;

public class SomasMaisProx {
	
	public static int[] encontrarSoma(int n, int p, int[] v){
		int dif; //diferenca mais pequena
		int difp; //diferenca provisoria
		int soma; //soma provisoria
		int soma1=-1; //soma mais proxima
		int soma2=-1; //soma mais proxima
		int res[] = new int[2]; //vetor com a/as soma/somas mais proxima/proximas
		res[0] = -1; 
		res[1] = -1;
		int i=0;
		
		if(p<v[0]) //se o numero for menor que o primeiro elemento
			res[0]=v[0]+v[1]; //retorna a soma dos dois primeiros elementos
		
		else{
			while(v[i]<=p){ //enquanto o elemento do vetor for menor que a pergunta
				i++; //incrementa
				if(i>=n-1)
					break;
			}
			
			if(p<v[n-1]) //porque?
				i--;
			
			dif=Math.abs(p-(v[i]+v[0])); //menor diferenca actual
			soma1=v[i]+v[0]; //soma mais proxima actual
			
			for(int j=1; j<i; j++){
				soma=v[i]+v[j]; //soma o ultimo elemento mais proximo da pergunta com um dos anteriores
				difp = Math.abs(p-soma); //calcula a diferenca
				if(difp<dif && soma2!=-1){ //se a diferenca for menor e ja houver outro valor na soma2
					soma1=soma;
					dif=difp;
					soma2=-1;
				}
				else if(difp<dif){ //se a diferenca for menor
					soma1=soma;
					dif=difp;
				}
				else if(difp==dif) //se a diferenca for igual
					soma2=soma;
			}
			
			res[0] = soma1;
			res[1] = soma2;
		
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
