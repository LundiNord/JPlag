import java.util.*;

class grafo {
	double x, y;
	double dist;
	grafo pai;
	boolean uspai;

	grafo(double x1, double y1) {
		x = x1;
		y = y1;
		dist = 999999.0;
		uspai = false;
		pai = null;
	}
}

public class sardas {

	public static void prim(grafo g[], grafo r) {
		r.dist = 0;
		LinkedList<grafo> q = new LinkedList<grafo>();
		for (int i = 0; i < g.length; i++)
			q.add(g[i]);
		while (!q.isEmpty()) {
			grafo u = q.remove();
			double dist = 9999999.0;
			for (int i = 0; i < g.length; i++)
				if (g[i] != u) {
					dist = Math.sqrt(((g[i].x - u.x) * (g[i].x - u.x))
							+ ((g[i].y - u.y) * (g[i].y - u.y)));

					if (dist < g[i].dist) {
						g[i].dist = dist;
						g[i].pai = u;
					}
				}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double count = 0.0;
		grafo g[] = new grafo[n];
		for (int i = 0; i < n; i++)
			g[i] = new grafo(in.nextDouble(), in.nextDouble());
		prim(g, g[0]);
		for (int i = 0; i < n; i++)
			if (!g[i].uspai) {
				count += g[i].dist;
				g[i].uspai = true;
			}
		System.out.println(String.format("%.2f", count));
	}
}
