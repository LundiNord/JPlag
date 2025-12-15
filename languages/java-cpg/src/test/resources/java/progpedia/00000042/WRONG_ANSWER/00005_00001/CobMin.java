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

	int m, n, end=0, elem, conta=0;

	m = in.nextInt();
	n = in.nextInt();
	Cord c[] = new Cord[n];

	for(int i = 0; i<n ; i++)
	    c[i] = new Cord(in.nextInt(), in.nextInt());

	Arrays.sort(c);

        for( int i = 0; end < m && i < n; i++){
	    if(c[i].inicio <= end){
		end = c[i].fim;
		conta++;
	    }
	    
	}

	System.out.println(conta);
	
	
    }
}
