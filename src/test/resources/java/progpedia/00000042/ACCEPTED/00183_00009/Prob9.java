
//package prob9;
import java.util.*;

class Prob9{
    public static void main(String[] args) {
        Scanner le = new Scanner(System.in);
        int inter = le.nextInt();
        int n = le.nextInt(); 
        segmento[] seg = new segmento[n];
        for (int i = 0; i < n; i++) 
            seg[i] = new segmento(le.nextInt(), le.nextInt());
	Arrays.sort(seg);
	int f = 0;
        int f1 = 0;
        int count = 0;
        while ( f<inter) {
            for (int i=0;i<n;i++){
                if (seg[i].l <= f){
                    if ( seg[i].r > f1 )
                        f1 = seg[i].r;
                }
            }   
            f = f1;
            count++;
        }
        System.out.println(count);
    }
}
class segmento implements Comparable<segmento>{
    int l, r;
    segmento(int l, int r){
	this.l=l;
	this.r=r;
    }	
    @Override
	public int compareTo(segmento seg){
	if( l < seg.l)
	    return -1;
	else
	    return 1;
    }
}

