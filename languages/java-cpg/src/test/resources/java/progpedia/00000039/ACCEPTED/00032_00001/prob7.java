import java.util.*;

//Código submetido no ano passado

public class prob7 {
	
	public static int bsearch(int somas[], int low, int high, int key){
		int middle = (low+high)/2;
		
		while (low < high){
			middle = (low+high)/2;
			
			if(key > somas[middle]) 
                return bsearch(somas, middle+1, high, key);
			if (key < somas[middle]) 
                return bsearch(somas, low, middle, key);
			if (key == somas[middle])
                return middle;
		}
		return middle;
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int num[] = new int[n];
		
		for(int i=0; i<n; i++)
			num[i]=in.nextInt();
		
		int p = in.nextInt();
		int perg[] = new int[p];
		
		for(int i=0; i<p; i++)
			perg[i]=in.nextInt();
		
		int tamMax = ((n*n)-n)/2; // Tamanho maximo das somas com os numeros de num[] sem repetição
		int somas[] = new int[tamMax];
		
		int count=0;
		for(int i=0; i<n; i++)
			for(int j=i+1; j<n; j++){
				somas[count] = num[i] + num[j];
				count++;
			}
		
		Arrays.sort(somas);
		
		/*for(int i=0; i<tam; i++)
			System.out.print(somas[i] + " ");
		*/
		
		for(int i=0; i<p; i++){
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