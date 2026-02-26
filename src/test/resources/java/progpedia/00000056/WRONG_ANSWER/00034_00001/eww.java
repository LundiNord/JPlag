import java.util.*;
import java.lang.Math.*;
class eww {
	public static void main (String args[]){
		Scanner input = new Scanner (System.in);
		int N = input.nextInt();
		PriorityQueue<sarda> Q = new PriorityQueue<sarda>();
		for (int i=0; i<N; i++)
			Q.add(new sarda(input.nextDouble(),input.nextDouble()));
		Q.peek().dist = 0;
		double contador = 0;
		while (Q.size() > 0){
			sarda u = Q.remove();
			contador+=u.dist;
			for (sarda w : Q)
				w.dist = Math.sqrt(Math.pow((u.x-w.x),2)+Math.pow((u.y-w.y),2));
		}
		System.out.printf("%.2f%n",contador);
	}
}

class sarda implements Comparable<sarda>{
	double x,y,dist;
	sarda (double x,double y){
		this.x = x;
		this.y = y;
		dist = Double.MAX_VALUE;
	}

	public int compareTo ( sarda dois){
		if (this.dist <= dois.dist)
			return 1;
		else return 0;
	}
}
