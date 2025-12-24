import java.util.*;
import java.io.*;

class problema9{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int intervalo = in.nextInt();
        int n = in.nextInt(); 
        seg[] segs = new seg[n];
        for (int i = 0; i < n; i++) 
            segs[i] = new seg(in.nextInt(), in.nextInt());
	Arrays.sort(segs);
	int f = 0;
        int f2 = 0;
        int cont = 0;
        while ( f<intervalo) {
            for (int i=0;i<n;i++){
                if (segs[i].esq <= f){
                    if ( segs[i].dir > f2)
                        f2 = segs[i].dir;
                }
            }   
            f = f2;
            cont++;
        }
        System.out.println(cont);
    }
}
class seg implements Comparable<seg>{
    int esq, dir;
    seg(int esq, int dir){
	this.esq=esq;
	this.dir=dir;
    }	
    @Override
	public int compareTo(seg x){
	if( esq < x.esq)
	    return -1;
	else
	    return 1;
    }
}
