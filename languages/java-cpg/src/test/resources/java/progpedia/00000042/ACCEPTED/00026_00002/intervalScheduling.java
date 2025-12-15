import java.io.*;
import java.util.*;

class evento implements Comparable<evento>{
    int inicio;
    int fim;

    evento(int i, int f){
	inicio=i;
	fim=f;
    }


    @Override
    public int compareTo(evento n) {
	if (inicio == n.inicio)
	    if (fim > n.fim)
		return 1;
	    else
		return -1;
	else
	    if(inicio > n.inicio)
		return 1;
	    else 
		return -1;   
    }
}


class intervalScheduling{

    public static int Output(evento[] n,int m, int p){
	int end=0, pos=0, count=0;
	int best;

	while(end<m){
	    best=-1;
	    while(pos<p && n[pos].inicio<=end){
		if(n[pos].fim>best)
		    best=n[pos].fim;
		pos++;
	    }
	    end=best;
	    count++;
	}
	return count;
    }

    
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);

	int inter = stdin.nextInt();

	int n_inter = stdin.nextInt();

	evento[] event = new evento[n_inter];
	
	for(int i=0; i< n_inter;i++){
	    event[i] = new evento(stdin.nextInt(), stdin.nextInt());	    
	}

	Arrays.sort(event);
	
	System.out.println(Output(event, inter, n_inter));
	
    }

}
