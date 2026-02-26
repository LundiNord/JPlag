import java.io.*;
import java.util.*;

public class ordenar {
	static int nr ; 
	static int seq[];
	static int swp;
	static int pos_min;
	static int i ; 
	static int j ; 
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		nr = ler.nextInt();
		if ( nr >= 1 && nr <= 100000){
		seq=new int [nr];
		
		for( i = 0 ; i < nr ; i++){
			seq[i] = ler.nextInt();
		}
		
		/*for ( i = 0 ; i < nr - 1 ; i++){
			pos_min = i ; 
			for ( int j= i+1 ; j < nr ;  j++ ){
				if ( seq[j] <= seq[pos_min]){
					pos_min = j ;
				}
			}
			
			  swp = seq[pos_min] ; 
			  seq[pos_min] = seq[i];
			  seq[i] = swp;
			
		}*/
		Arrays.sort(seq);
		for ( i = 0 ; i < nr-1 ; i++){
			System.out.print(seq[i]+ " ");
		}
		System.out.println(seq[nr-1]);
		}
	}
	}


