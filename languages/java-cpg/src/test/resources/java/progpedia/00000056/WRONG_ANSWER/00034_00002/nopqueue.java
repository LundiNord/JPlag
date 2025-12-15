import java.util.*;
import java.lang.Math.*;
class nopqueue {
	public static void main (String args[]){
		Scanner input = new Scanner (System.in);
		int N = input.nextInt();
		LinkedList<sarda> Q = new LinkedList<sarda>();
		for (int i=0; i<N; i++)
			Q.add(new sarda(input.nextDouble(),input.nextDouble()));
		Q.peek().dist = 0;
		double contador = 0;
		double min;
		int imin;
		while (Q.size() > 0){
			min = Q.getFirst().dist;
			imin = 0;
			for (int i=1; i<Q.size(); i++)
				if (min>Q.get(i).dist){
					min = Q.get(i).dist;
					imin = i;
				}
							
			sarda u = Q.remove(imin);
			System.out.println("add "+u.dist);
			contador+=u.dist;
			for (sarda w : Q){
			    
			    w.dist = Math.sqrt(Math.pow((u.x-w.x),2)+Math.pow((u.y-w.y),2));
			    System.out.println(w.dist);}
		}
		System.out.printf("%.2f%n",contador);
	}
}

class sarda{
	double x,y,dist;
	sarda (double x,double y){
		this.x = x;
		this.y = y;
		dist = Double.MAX_VALUE;
	}
}
