import java.util.*;

public class Piramides {

	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		int c = inp.nextInt(); //tamanho de camadas
		int d = inp.nextInt(); //numero de degraus com defeito
		int v[][] = new int[c][c];
		
		for(int i=c-1; i>=0; i--) //iniciar matriz a -1
			for(int j=0; j<c; j++)
				v[i][j]=-1;
		
		for(int i=0; i<d; i++)//colocar celulas defeituosas a 0
			v[inp.nextInt()-1][inp.nextInt()-1]=0;
//-------------------------------------------------------------------------------------------------------
		if(c==1)
			System.out.println("1");
		
		else if(v[c-1][0]==0) //se o topo for 0 acaba
			System.out.println("0");
//-------------------------------------------------------------------------------------------------------		
		else{
			v[c-1][0]=1; //colocar topo a 1
			
			int coluna=2;
			int res=0;
			
			for(int i=c-2; i>=0; i--){
				for(int j=0; j<coluna; j++){
					
					int conta=0; //contador de caminhos possiveis
					
					if(v[i][j]!=0){
						if(v[i+1][j]>0) //se o degrau anterior tiver solucao
							conta+=v[i+1][j];
			
						if(j-1>=0){
							if(v[i+1][j-1]>0) //se o degrau anterior tiver solucao
								conta+=v[i+1][j-1];
						}
						
						v[i][j]=conta; //colocar o numero de solucoes que o degrau tem
						
						if(i==0)
							res+=v[i][j]; //se for o ultimo degrau soma as solucoes possiveis
					}
				}
				coluna++; //aumenta coluna
			}	
		System.out.println(res);
		}
	}
}	

