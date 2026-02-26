import java.util.*;
import java.io.*;

//Código feito em conjunto com o Pedro Marques

public class prob6 {
	
	public static int bsearch(int somas[], int low, int high, int key){
		int middle = (low+high)/2;
		
		while (low < high){
			middle = (low+high)/2;
			
			if(key > somas[middle]) return bsearch(somas, middle + 1, high, key);
			else if (key < somas[middle]) return bsearch(somas, low, middle, key);
				else if (key == somas[middle]) return middle;
		}
		return middle;
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int num[] = new int[N];
		
		for(int i=0; i<N; i++)
			num[i]=in.nextInt();
		
		int P = in.nextInt();
		int perg[] = new int[P];
		
		for(int i=0; i<P; i++)
			perg[i]=in.nextInt();
		
		int tamMax = ((N*N)-N)/2; // Tamanho maximo das somas com os numeros de num[] sem repetição
		int somas[] = new int[tamMax];
		
		int count=0;
		
		for(int i=0; i<N; i++)
			for(int j=i+1; j<N; j++){
				somas[count] = num[i] + num[j];
				count++;
			}
		
		Arrays.sort(somas);
		
		/*for(int i=0; i<tam; i++)
			System.out.print(somas[i] + " ");
		*/
		
		for(int i=0; i<P; i++){
			if(perg[i] < somas[0]) 
				System.out.println(somas[0]);
			else if (perg[i] > somas[tamMax-1])
					System.out.println(somas[tamMax-1]);
				else {
					int menor = bsearch(somas, 0, tamMax, perg[i]); //menor soma encontrada
					
					if(perg[i] == somas[menor])
						System.out.println(somas[menor]);
					else {
						int dif1 = Math.abs(perg[i] - somas[menor-1]);
						int dif2 = Math.abs(perg[i] - somas[menor]);
						
						if(dif1 > dif2) System.out.println(somas[menor]);
						else 
							if(dif1 < dif2) System.out.println(somas[menor-1]);
							else System.out.println(somas[menor-1] + " " + somas[menor]);
						}
					}
		}
	}
}

