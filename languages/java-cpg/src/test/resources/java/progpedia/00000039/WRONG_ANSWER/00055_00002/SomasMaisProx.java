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
		int control = -1;
		
		if(p<=v[0]) //se o numero for menor que o primeiro elemento
			res[0]=v[0]+v[1]; //retorna a soma dos dois primeiros elementos
		
		else{
			
			while(v[i]<p){ //enquanto o elemento do vetor for menor que a pergunta
				i++; //incrementa
				if(i==n-1){
					control = 1;
					break;
				}
			}	
			
			if(control != 1)
				i--;
			
			if(i<n-1){ //prevenir certos casos (Ex: v={1,2,3,20,26} p=25 res[0]=23 (20+3) res[1]=27 (26+1))
				for(int j=i+1; j<n-1; j++)
					if(Math.abs(p-(v[i]+v[0])) > Math.abs(p-(v[j]+v[0])))
						i=j;
			}
			
			dif=Math.abs(p-(v[i]+v[0])); //menor diferenca actual
			soma1=v[i]+v[0]; //soma mais proxima actual
			
			for(int j=0; j<=i; j++)
				for(int k=j+1; k<=i; k++){
					soma=v[j]+v[k]; //soma o ultimo elemento mais proximo da pergunta com um dos anteriores
					difp = Math.abs(p-soma); //calcula a diferenca
					if(difp<dif && soma2!=-1){ //se a diferenca for menor e ja houver outro valor na soma2
						soma1=soma;
						soma2=-1;
						dif=difp;
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
		
			if(soma1==soma2)
				res[1]=-1;
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
