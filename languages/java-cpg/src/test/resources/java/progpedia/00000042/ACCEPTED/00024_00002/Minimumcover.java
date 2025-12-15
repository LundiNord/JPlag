import java.io.*;
import java.util.*;
class segmentos implements Comparable <segmentos>{

    public int l;
    public int r;
    segmentos(int l, int r){
	this.l=l;
	this.r=r;
    }


@Override
public int compareTo(segmentos other){
    if(this.l>other.l){return 1;}
    else{return -1;}
}
}





class Minimumcover{






    public static void main(String args[]){
	int max=0;
	int count=0;
	Scanner stdin = new Scanner(System.in);
	int m= stdin.nextInt();
	int n = stdin.nextInt();
	segmentos[] s = new segmentos[n];
	for(int i=0;i<n;i++){
	    s[i]=new segmentos(stdin.nextInt(),stdin.nextInt());
		}
	Arrays.sort(s);

	int end=0;
	int pos=0;
	int best=-1;
	while(end<m){
	    best=-1;
	    while(pos<n && s[pos].l<=end){
		if(s[pos].r>best){best=s[pos].r;}
		pos++;
	    }
	    end=best;
	    count++;
	}
	System.out.println(count);
		
    }
}
