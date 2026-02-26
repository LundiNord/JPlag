import java.util.*;
import java.io.*;


class sardas{
    double x;
    double y;

    sardas(double x, double y){
	this.x = x;
	this.y = y;
    }

    static double dist(sardas a, sardas b){
	double xm = a.x - b.x;
	double ym = a.y - b.y;

	return (Math.sqrt((xm*xm) + (ym*ym)));
    }
}

class moo21{
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	sardas[] s = new sardas[n+1];
	
	for(int i=1; i<=n; i++){
	    s[i] = new sardas(in.nextDouble(), in.nextDouble());
	}

	double[] out = new double[n+1];
	
	for(int i=1; i<n; i++){
	    out[i] = sardas.dist(s[i], s[i+1]);
	}
	out[n] = sardas.dist(s[n], s[1]);

	Arrays.sort(out);

	double outp = 0;
	for(int i=1; i<n; i++){
	    outp += out[i];
	}
	
	System.out.printf("%.2f\n", outp);
	//	System.out.println();
    }
}
