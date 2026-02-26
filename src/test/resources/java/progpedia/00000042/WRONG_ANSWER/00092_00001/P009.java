import java.util.*;
import java.io.*;

class Segmento implements Comparable<Segmento>{
    public int left,right,tamanho=0;;
    Segmento(int l, int r){
	left = l;
	right = r;
	tamanho = right - left;
    }

    @Override
    public int compareTo(Segmento s){
	if(left < s.left) return -1;
	if(left > s.left) return +1;
	if(right < s.right) return -1;
	if( right > s.right) return +1;
	return 0;
    }
}


public class P009{
    public static void main (String args[]){

	Scanner in = new Scanner(System.in);

	int m = in.nextInt();
	int n = in.nextInt();
	Segmento s[] = new Segmento[n];

	for(int i=0; i<n; i++)
	    s[i] = new Segmento(in.nextInt(),in.nextInt());
	Arrays.sort(s);

	int tmp=0,end=0,ns=0,max=0;
	for(int i=0; i<n; i++){
	    if(end >= m)
		break;
	    for(int j=0; j<n; j++){
		if(s[j].left <= end && s[j].right > end){
		    tmp = s[j].right;   
		}
	    }
	    if(tmp > end)
		end = tmp;
	    tmp=0;
	    ns++;
	}
	
	System.out.println(ns);	    
	   
    }
}
