import java.util.*;
import java.io.*;

class seg implements Comparable<seg>{
    int x;
    int y;

    seg(int x, int y){
	this.x=x;
	this.y=y;
    }

    public int compareTo(seg s){
	if(x<s.x) return -1;
	else if(x>s.x) return 1;
	else return 0;
    }
}

public class moo09{
    public static void main(String args[]){
	Scanner stdio=new Scanner(System.in);
	int M=stdio.nextInt();
	int N=stdio.nextInt();
	seg[] s=new seg[N];
	for(int i=0;i<N;i++){
	    s[i]= new seg(0,0);
	}
	int count=0;
	int end=0;
	int pos=0;
	for(int j=0;j<N;j++){
	    s[j].x=stdio.nextInt();
	    s[j].y=stdio.nextInt();
	}
	Arrays.sort(s);
	while(end<M){
	    int best=-1;
	    while(pos<N && s[pos].x<=end){
		if(s[pos].y>best) best=s[pos].y;
		pos++;
	    }
	    end=best;
	    count++;
	}
	System.out.println(count);				     
    }
}
