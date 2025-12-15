import java.util.*;
import java.io.*;
import java.lang.*;

class prob24{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	PriorityQueue<Double> q =new PriorityQueue<Double>(N,new Comparator<Double>(){
		public int compare(Double e1, Double e2){
		    if(e1>e2)
			return 1;
		    else
			return -1;
		}
			
	    });
	Double[] x=new Double[N];
	Double[] y=new Double[N];
	for(int i=0;i<N;i++){
	    x[i]=sc.nextDouble();
	    y[i]=sc.nextDouble();
    }
	for(int k=0;k<N;k++){
	    for(int r=k;r<N;r++){
		if(r==k)
		    continue;
		double t=Math.sqrt(Math.pow((x[k]-x[r]),2)+Math.pow((y[k]-y[r]),2));
		q.add(t);
	    }
	}
	double t=0;
	for(int f=0;f<q.size();f++){
	    double w=q.poll();
	    t=t+w;
	}
	System.out.printf("%.2f\n",t);
    }
}
