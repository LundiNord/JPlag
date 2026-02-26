import java.util.*;

public class Piramides {

	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		int c = inp.nextInt(); //tamanho de camadas
		int d = inp.nextInt(); //numero de degraus com defeito
		long v[][] = new long[c][c];
//----------------------------------COLOCAR MATRIZ A -1 E CELULAS A 0---------------------------------------------		
		for(int i=c-1; i>=0; i--)
			for(int j=0; j<c; j++)
				v[i][j]=-1;
		
		for(int i=0; i<d; i++)
			v[inp.nextInt()-1][inp.nextInt()-1]=0;
//----------------------------------CASOS ESPECIAIS---------------------------------------------------------------
		if(c==1) //se so houver um degrau
			System.out.println("1");
		
		else if(v[c-1][0]==0) //se o topo for 0 acaba
			System.out.println("0");
//-----------------------------------CASO COMUN--------------------------------------------------------------------		
		else{
			v[c-1][0]=1; //colocar topo a 1
			
			int coluna=2;
			long res=0;
			
			for(int i=c-2; i>=0; i--){
				for(int j=0; j<coluna; j++){
					
					long conta=0; //contador de caminhos possiveis
					
					if(v[i][j]!=0){
						
						if(v[i+1][j]>0)
							conta+=v[i+1][j];
			
						if(j-1>=0)
							if(v[i+1][j-1]>0)
								conta+=v[i+1][j-1];
							
					
						
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

