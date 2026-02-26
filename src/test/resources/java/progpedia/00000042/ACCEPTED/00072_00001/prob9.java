import java.util.*;
import java.io.*;

class objecto implements Comparable<objecto>{
    int first;
    int last;

   public objecto(int x,int y){
	first = x;
	last = y;
    }

    @Override
    public int compareTo(objecto t){
	if(first > t.first) return +1;
	if(first < t.first) return -1;
	else{
	    if(last > t.last) return +1;
	    if(last < t.last) return -1;
	    else return 1;
	}
     }


    public static int verifica(objecto s[],int tamanho, int M){


	int end = 0;
	int soluçao = 0;

	int best = 0;

	while(end < M){
	    for (int i=0;i<tamanho;i++)
		if(s[i].first <= end && s[i].last>best)
		    best = s[i].last;
	    end = best;
	    soluçao++;
	}
	return soluçao;
    }

}


public class prob9{
    public static void main(String args[]){

	Scanner in = new Scanner(System.in);

	int M = in.nextInt();


	int N = in.nextInt();

	objecto seg[] = new objecto[N];
	
	
	for(int i=0;i<N;i++)
       	    seg[i] = new objecto(in.nextInt(),in.nextInt());


	Arrays.sort(seg);

	int p = objecto.verifica(seg, N, M);

	System.out.println(p);
    }

}
