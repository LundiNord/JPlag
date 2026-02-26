import java.util.*;
import java.io.*;
class recta{
    int inicio;
    int fim;
    recta(int i, int f){
	inicio=i;
	fim=f;
    }
}
class prob10{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int T=sc.nextInt();
	int N=sc.nextInt();
	int in,fi;
	recta f=new recta(0,T);
	recta R[]=new recta[N];
	for(int i=0;i<N;i++){
	    in=sc.nextInt();
	    fi=sc.nextInt();
	    R[i]=new recta(in,fi);
	    }
	Arrays.sort(R, new Comparator<recta>(){
		public int compare(recta a, recta b){
		    if(a.inicio<b.inicio)
			return -1;
		    else
			return 1;}});
	int end=0;
	int endr=0;
	int count=0;
	int k=0;
	while(end<f.fim){
	    if(R[k].inicio<=end){
		endr=R[k].fim;
		if(endr>end)
		    end=endr;
		else
		    count--;
		count++;
	    }
	    k++;
	}
	System.out.println(count);
    }	
}
    
