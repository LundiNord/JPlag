import java.util.*;
import java.io.*;

class Ponto implements Comparable<Ponto> {
	int start;
	int end;

	Ponto(int start, int end){
		this.start=start;
		this.end=end;
	}

	@Override 
	public int compareTo(Ponto x){
		return this.start - x.start;
	}
}

class p09{
	public static void main(String args[]){
		Scanner input  = new Scanner(System.in);
		
		int time         = input.nextInt();
		int n            = input.nextInt();
		Ponto[] pontos   = new Ponto[n];

		for(int i=0;i<n;i++){
			pontos[i]    =  new Ponto(input.nextInt() , input.nextInt() );

		}

		Arrays.sort(pontos); // Sort implementado na class ponto
							 // Sort (pontos[ 0 ] . compareTo(pontos[1]) 
		int fim        = 0;
		int counter    = 0;
		int pos        = 0;

		while(fim<time){
			int later_end = fim;
			pos = 0;

			while(pos < n && pontos[pos].start <= fim ){
				//System.out.println("val later: " + later_end + "   val pos: "+ pos);
				if(pontos[pos].end > later_end){
					later_end=pontos[pos].end;
				}

				pos++;
			}
			
			fim   = later_end;
			counter++;
		}

		System.out.println(counter);
	}
}