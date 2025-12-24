
import java.util.Scanner;

class especiedegrafo {
	
	double x,y;
	boolean vis;
	
	especiedegrafo(double x1, double y1) {

		x= x1; y=y1;
		vis=false;
	}
}


public class twentyfour {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		especiedegrafo g[] = new especiedegrafo[n];
		for (int i = 0; i < n; i++)
			g[i] = new especiedegrafo(in.nextDouble(),in.nextDouble());
		System.out.println(String.format( "%.2f", calc(g)));

	}

	private static double calc(especiedegrafo[] g) {
		
		int a[]=new int[g.length*2]; 
		int b[]=new int[g.length*2];
		int nar=0;
		double count=0;
		for (int i = 0; i < g.length; i++){
			double min=99999;
			double dist=0;
			for (int j = 0; j < g.length; j++){
				dist= Math.sqrt((g[j].x-g[i].x)*(g[j].x-g[i].x) + (g[j].y-g[i].y)*(g[j].y-g[i].y));
				if(!g[j].vis)
				if(dist<min && dist>0 && !exist(a,b,i,j)) {
					min=dist;
					g[j].vis=true;
					g[i].vis=true;
					a[nar]=i; b[nar]=j;
					a[nar+1]=j; b[nar+1]=i;
					nar+=2;
				}
			}
			if(min!=99999)
				count+=min;		
		}
		return count;
	}

	private static boolean exist(int [] a, int [] b,int i, int j) {
		
		for(int h=0;h<a.length;h++)
			if(a[h]==i && b[h]==j)
				return true;
		return false;
	}
}
