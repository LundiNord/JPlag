import java.util.*;
import java.io.*;

class p009{
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	int cont=0, fim=0, unfin=0;
	int m=in.nextInt();
	int n=in.nextInt();
	segmento[] segs=new segmento[n];
	for(int i=0;i<n;i++)
	    segs[i]=new segmento(in.nextInt(), in.nextInt());
	Arrays.sort(segs);
	while(fim<m){
	    for(int i=0;i<n;i++){
		if(segs[i].left<=fim && segs[i].right>unfin){
		    unfin=segs[i].right;
		}
	    }
	    fim=unfin;
	    cont++;
	}
	System.out.println(cont);
    }
}
class segmento implements Comparable<segmento>{
    int left, right;
    segmento(int left, int right){
	this.left=left;
	this.right=right;
    }
    @Override
	public int compareTo(segmento seg){
	if(left<seg.left)
	    return -1;
	else
	    return 1;
    }
}