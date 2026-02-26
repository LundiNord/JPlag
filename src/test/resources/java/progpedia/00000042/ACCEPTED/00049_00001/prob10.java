import java.util.*;
import java.io.*;

class Segmento{

    int Li;
    int Ri;

    Segmento(int L, int R){
	Li = L;
	Ri = R;
    }
    
    int left(){
	return Li;
    }
    int right(){
	return Ri;
    }
    
}

class prob10{
    public static void main(String args[]){
	Scanner s = new Scanner(System.in);
	//	int i;
	int M;
	M = s.nextInt();
	s.nextLine();
	int N;
	N = s.nextInt();
	s.nextLine();
	Segmento seg[] = new Segmento[N];

	for(int i=0; i<N; i++){
	    seg[i]= new Segmento(s.nextInt(), s.nextInt());
	    s.nextLine();
	}

	Arrays.sort(seg, new Comparator<Segmento>() {
		public int compare(Segmento s1, Segmento s2) {
		    if(Math.abs(s1.left() - s2.left()) > 0) 
			return s1.left() - s2.left();
		    return s1.right() - s2.left();
		}
	    });

	int end=0;
	int count=0;
	while( end < M ){
	    int max=0;    
	    
	    for(int i=0; i<N; i++){
		if( seg[i].left()<= end){
		    if(seg[i].right() > max) 
			max = seg[i].right();
		}
	    }
	    count++;
	    end = max;
	}

	System.out.println(count);
	
    }
}

