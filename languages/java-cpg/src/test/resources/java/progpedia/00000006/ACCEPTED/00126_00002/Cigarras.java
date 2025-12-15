import java.util.*;

public class Cigarras {
	
	public static int MAX=30;
	public static int locais[]=new int[MAX];
	private static Scanner in;
	
	public static void main(String args[]){
		
		in = new Scanner(System.in);
		int aux=0;
		
		int nLocais=in.nextInt();
		while(nLocais!=0){
			locais[aux]=nLocais;
			aux=locaisVisitados(aux);
			nLocais=in.nextInt();
			aux++;
		}
		
		for(int i=0; i<aux; i++){
			System.out.println(locais[i]);
		}
	}

	private static int locaisVisitados(int aux) {
		
		for(int i=0; i<aux; i++){
			if(locais[i]==locais[aux])
				aux=i;
		}
		return aux;
	}
}