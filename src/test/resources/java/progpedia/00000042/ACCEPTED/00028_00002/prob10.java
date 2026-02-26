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
			return 1;
		    /* if(a.inicio<b.inicio)
			return -1;
		    else if(a.inicio>b.inicio)
			return 1;
		    else if (a.fim<b.fim)
			     return -1;
		    else
		    return 1;		    */
		}});
	int end=0;
	int endr=0;
	int max=0;
	int count=0;
	int k=0;
	while(end<f.fim){
	    for(int l=0;l<N;l++){
		if(R[l].inicio<=end){
		    max=R[l].fim;
		    if(endr<max)
			endr=max;
		}
	    }
		if(end<endr){
		    end=endr;
		    count++;
		}
	    
		    
	}
	System.out.println(count);
    }	
}
    
