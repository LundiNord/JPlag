import java.util.*;

public class Somas{
	static int[] somas;
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int num = in.nextInt();
		int s[] = new int[num];
		
		
		for(int i=0;i<num;i++){
			s[i]=in.nextInt(); 
		}
		
		int np = in.nextInt();
		int p[] = new int[np];
	
		
		for(int i=0;i<np;i++){
			p[i] = in.nextInt();
		}
		
		totalSomas(s);
		Arrays.sort(somas);

	
		for(int i=0;i<np;i++){
			binaria(p[i], somas);
			
		}
	}
	
	static void totalSomas(int s[]){
		int tam = s.length;
		int tSomas = tam*(tam-1)/2;
		somas = new int[tSomas];
		int cont=0;
		
		for(int i=0;i<tam;i++){
			for(int j=i+1;j<tam;j++){
					somas[cont]=s[i]+s[j];
					cont++;
			}
		}
		
	}
	
	static void binaria(int p, int[] s){
		int menor = 0;
		int maior = s.length-1;
		int meio;
		
		
		while(menor<maior-1){
			meio = menor + (maior-menor)/2;
	
			if(p==s[meio]){
				System.out.println(s[meio]);
				return;
			}
			
			else if(p<s[meio]){
				maior=meio;
			}
			
			else{
				menor=meio;
			}
		
		}
			
			if(Math.abs(s[maior]-p)<Math.abs(s[menor]-p)){
				System.out.println(s[maior]);
				return;
			}
			
			if(Math.abs(s[maior]-p)>Math.abs(s[menor]-p)){
				System.out.println(s[menor]);
				return;
			}
			
			System.out.println(s[menor]+" "+s[maior]);
		
	}
	
}
