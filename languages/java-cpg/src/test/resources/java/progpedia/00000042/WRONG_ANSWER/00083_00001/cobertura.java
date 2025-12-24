import java.io.*;
import java.util.*;

class Segmento implements Comparable<Segmento> {
    public int left,right;

    Segmento(int l,int r){
	left = l;
	right = r;
    }
    @Override
    public int compareTo(Segmento s){
	if(left < s.left) return -1;
	else if(left > s.left) return +1;
	else if(right < s.right) return -1;
	else if(right > s.right) return +1;
	return 0;
    }
}

class cobertura{
    static int np;
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int m = in.nextInt();
	np= in.nextInt();
	Segmento lr[] = new Segmento[np];
	for(int i=0;i<np;i++){
	    lr[i] = new Segmento(in.nextInt(),in.nextInt());  
	}
	Arrays.sort(lr);
	int end=0,cs=0;
	for(int i=0;i<np;i++){
	    if(lr[i].left<=end){
		for(int j=0;j<np;j++){
		    if(lr[i].right>end)
			end = lr[i].right;
		}
		cs++;
	    }
	    //System.out.println(lr[i].left + " " + lr[i].right);
	}
	System.out.println(end);
    }

}
