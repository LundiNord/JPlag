import java.util.*;
import java.io.*;
import java.util.Arrays;

class soma {
	static int bsearch(int [] soma , int low , int high , int key ) {
		int middle=0; 
		while ( low < high ) {
			middle = low + ( high - low )/2 ;
			if ( key == soma[middle]) {
				return middle;
			}
			else if ( key < soma[middle] ) {
				high = middle;
			}
			else{ low = middle+1 ;
			} 
		}
		
		middle = low;
		if ( middle > 0 ) {
			if (key-soma[middle-1] < soma[middle]-key)
				return middle-1;
			if ( key-soma[middle-1] == soma[middle]-key ) {
				System.out.print(soma[middle-1]+ " " );
				return middle;
			}
			if( soma[middle]-key < key - soma[middle-1]) { 
				return middle;
			}
		}
		return middle ; 
		}
		
public static void main ( String[] args ) {
	Scanner ler = new Scanner(System.in);
	int n = ler.nextInt();
	int s[] = new int[n];
	int fact = 0 ; 
	for ( int i = 0 ; i < n ; i++) {
		s[i] = ler.nextInt();
		fact =fact +i;
		//System.out.println(fact);
	}
	
	int soma[] = new int [fact];
	int x = 0 ; 
	for ( int i = 0 ;  i < n ; i++) {
		for ( int j = 0; j < i ; j++)  {
			soma[x] = s[i]+s[j];
			x++;
			//System.out.println("Soma[" + x + "]--> " + soma[x-1] ) ;
		}
	}
	Arrays.sort(soma);
	int p = ler.nextInt();
	int perg ;
	int k ;  
	for ( int i = 0 ; i < p ; i++) {
		x = ler.nextInt();
		k = bsearch(soma,0,fact-1,x);
		if ( k != -1 ) {
			System.out.println(soma[k]);
		}
		else
			System.out.println(k);
	}				 
 }
}
		
	
