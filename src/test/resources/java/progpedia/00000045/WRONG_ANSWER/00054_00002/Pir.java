import java.util.*;
import java.io.*;
//codigo realizado com a ajuda dos colegas Francisco Oliveira e Artur Ferreira
class Pir {
	static int c;
	static long v[][];
	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		c = inp.nextInt(); //tamanho de camadas
		int d = inp.nextInt(); //numero de degraus com defeito
		v= new long[c][c];
		
		for(int i=0; i<c; i++) {		
			 v[0][i] = 1; 	//colocar priamide na sua base a 1
			
			
			}
		
		for(int i=0; i<d;i++) {
		v[inp.nextInt()-1][inp.nextInt()-1] = -1;  //colocar pedras com defeito a -1
		}
		
		calcula(); // funçao que calcula os caminhos na piramide
	        if(v[c-1][0] == -1) System.out.print(0); //se o topo tiver defeito print 0 caminhos..
		System.out.print(v[c-1][0]);
	}
		




		static void calcula() {    // funçao que calcula apartir da segunda camada os caminhos possiveis recursivamente somand rescursimavente o numero de caminhos disponiveis desde a base ate ao topo 
			for(int i=1;i<c;i++) {
			for(int j=0;j<c-i;j++) {
				if(v[i][j] != -1) {
		    		if(v[i-1][j]!=-1 && v[i-1][j+1]!=-1)   
					v[i][j]+=v[i-1][j] + v[i-1][j+1];
		    		else if(v[i-1][j]!=-1)
					v[i][j]+=v[i-1][j];
	 			else if(v[i-1][j+1]!=-1)
					v[i][j]+=v[i-1][j+1];
				}
			 }
			 }
		}

}
