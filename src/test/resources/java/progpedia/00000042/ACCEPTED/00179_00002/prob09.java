import java.util.*;
import java.io.*;

class prob09{
    public static void main(String[] args){
	Scanner in= new Scanner(System.in);
	int M=in.nextInt();
	int N=in.nextInt();
	Pontos[] ponto= new Pontos[N];
	int seg=0;
	for(int i=0;i<N;i++){
	    ponto[i]=new Pontos(in.nextInt(),in.nextInt());
	}
	Arrays.sort(ponto);
	int c=0;
	int i=0;
	while(seg<M){
	    int fim_seg=seg;
	    while(i<N && ponto[i].x<=seg){
		if(ponto[i].y > fim_seg){
		    fim_seg=ponto[i].y;
		}
		i++;
	    }
	    seg=fim_seg;
	    c++;
	}
	System.out.println(c);	
    }
}
class Pontos implements Comparable<Pontos>{
    int x;
    int y;
    Pontos(int a, int b){
	x=a;
	y=b;
    }
    public int compareTo(Pontos a){
	return this.x - a.x;
    }
}

