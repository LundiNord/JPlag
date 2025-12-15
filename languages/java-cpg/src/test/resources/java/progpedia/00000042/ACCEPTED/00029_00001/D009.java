import java.io.*;
import java.util.*;

class Vector implements Comparable<Vector>{
    public int inicio, fim;

    Vector(int a, int b){
	inicio = a;
	fim = b;
    }

    public int compareTo(Vector p){
	if ( inicio < p.inicio) 
	    return -1;
	if ( inicio > p.inicio)
	    return +1;
	if( fim < p.fim)
	    return +1;
	if (fim > p.fim)
	    return -1;
	return 1;
    }

}

public class D009{

    static int comp;
    static int nsegm;
    static Vector segm[];
    
    public static void lerInput(){
	Scanner stdin = new Scanner(System.in);
	comp = stdin.nextInt();
	nsegm = stdin.nextInt();
	segm = new Vector[nsegm];
	for ( int i = 0; i < nsegm ; i++)
	    segm[i] = new Vector(stdin.nextInt(), stdin.nextInt());
	Arrays.sort(segm);
	}

    public static void solve(){
	int melhor = 0, imelhor = 0;
	for ( int i = 0; i < nsegm && segm[i].inicio == 0; i++)
	    if(segm[i].fim > melhor){
		melhor = segm[i].fim;
		imelhor = i;
	    }
	done(imelhor,segm[imelhor].inicio, melhor);
    }

    public static void done(int n, int inicio, int fim){
	int quantidade = 1;
	int melhorf = 0, melhori = 0, melho = 0; 
	while(fim < comp){
	    quantidade++;
	    for(int i = n; i < nsegm && segm[i].inicio <= fim; i++)
		if(segm[i].fim > melhorf){
		    melhorf = segm[i].fim;
		    melhori=segm[i].inicio;
		    melho = i;
		}
	    n = melho;
	    inicio = melhori;
	    fim = melhorf; 
	}
	System.out.println(quantidade);
    } 

    public static void main(String args[]){
	lerInput();
	solve();
    }
}
