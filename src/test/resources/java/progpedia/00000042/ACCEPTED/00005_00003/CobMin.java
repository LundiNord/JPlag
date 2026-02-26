import java.util.*;
import java.io.*;

class Cord implements Comparable<Cord>{
    public int inicio, fim;

    Cord(int i, int f){
	inicio = i;
	fim = f;
    }

    @Override
    public int compareTo(Cord c){
	if (inicio == c.inicio)
	    if(fim > c.fim)
		return -1;
	    else
		return 1;
	else
	    if( inicio < c.inicio)
		return -1;
	    else
		return 1;
		
    }
    
}

class CobMin {
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);

	int m, n;

	m = in.nextInt();
	n = in.nextInt();
	Cord c[] = new Cord[n];

	for(int i = 0; i<n ; i++)
	    c[i] = new Cord(in.nextInt(), in.nextInt());

	Arrays.sort(c);

	int end=0, bestsf, conta = 0;
	
	while( end < m){
		bestsf = 0; 
		for(int i = 0 ; i < n ; i++){
			if(c[i].inicio <= end){
				if(c[i].fim > bestsf)
					bestsf = c[i].fim;
			}
		}
		end = bestsf;
		conta++;
	}
	
	/*for(int i = 0; i < n ; i++)
		System.out.println(c[i].inicio + " " + c[i].fim);
*/
	System.out.println(conta);
	
	
    }
}
