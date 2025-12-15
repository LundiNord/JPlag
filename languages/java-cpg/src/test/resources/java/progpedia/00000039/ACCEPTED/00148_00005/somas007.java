import java.io.*;
import java.util.*;

public class somas007 {

    static int k = 0;

    static void bsearch(int somas[], int p, int min, int max){
	int meio = 0;
	meio = min + (max - min)/2;
	int sol1 = p*p;
	int i    = 0;
	
	while(min <=max){   
	    meio = min + (max - min)/2;
	    if (p < somas[meio])
		max = meio - 1;   
	    else
		min = meio + 1;
	    if(Math.abs(sol1 - p) > Math.abs(somas[meio] - p)){
		sol1 = somas[meio];
		i = meio;
	    }
	}
	if(p == somas[i])
	    System.out.println(somas[i]);
	else if(p > somas[i]){
	    int i2 = i;
	    int aux = somas[i];
	    while( i2 <= k-1 && aux==somas[i2])
		i2++;
	    
	    if( i2 <= k-1 && p - somas[i] == somas[i2] - p)
		System.out.println(somas[i] + " " + somas[i2]);
	    else
		System.out.println(somas[i]);		
	}
	else{
	    int i2 = i;
	    int aux = somas[i];
	    while( i2 >=0 && aux==somas[i2])
		i2--;
	    if( i2 >= 0 && somas[i] - p == p - somas[i2])
		System.out.println(somas[i2] + " " + somas[i]);
	    else
		System.out.println(somas[i]);


	}


	
	/*else if(p > somas[i]){
	    if( i+1 <= k-1 && p - somas[i] == somas[i+1] - p)
		System.out.println(somas[i] + " " + somas[i+1]);
	    else
		System.out.println(somas[i]);
	}
	else{
	    if( i-1 >= 0 && somas[i] - p == p - somas[i-1])
		System.out.println(somas[i-1] + " " + somas[i]);
	    else
		System.out.println(somas[i]);
		}*/

    }

    
    static int pares(int npar, int n){
	for(int i=0; i<n; i++)
	    for(int j=i+1; j<n; j++)
		npar++;
	return npar;
    }
    





    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	int n   = stdin.nextInt();
	int s[] = new int[n];
	for (int i=0; i<n; i++)
	    s[i] = stdin.nextInt();
	int m   = stdin.nextInt();
	int p[] = new int[m];
	for (int i=0; i<m; i++)
	    p[i] = stdin.nextInt();

	//calcular numero de pares possiveis
	int npar = pares(0, n);

	//calcular  somas dos pares possiveis
	int somas[] = new int[npar]; 
	for(int i=0; i<n; i++)
	    for(int j=i+1; j<n; j++){
		somas[k]= s[i] + s[j];
		k++;
	    }

	/*for(int i=0; i<k; i++)	    
	    System.out.println(somas[i] + " ");
	*/
	//ordenar array somas
	Arrays.sort(somas);

	/*for(int i=0; i<k; i++)	    
	    System.out.print(somas[i] + " ");
	 System.out.println();
	*/
	 for(int i=0; i<m; i++)
	    bsearch(somas, p[i], 0, k-1);	 
    }
}
