import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Point {
	double x, y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {
	double x1, y1, x2, y2;
	int node1, node2;

	Edge(int node1, int node2, double x1, double y1, double x2, double y2) {
		this.node1 = node1;
		this.node2 = node2;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double getDistance() {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public int compareTo(Edge e) {
		if (getDistance() < e.getDistance())
			return -1;

		return 1;
	}
}

public class Prob21 {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		Point[] p = new Point[n];
		String s = stdin.nextLine();

		for (int i = 0; i < n; i++) {
			String[] line = stdin.nextLine().split(" ");
			double a = Double.parseDouble(line[0]);
			double b = Double.parseDouble(line[1]);
			p[i] = new Point(a, b);
		}

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				Edge edge = new Edge(i, j, p[i].x, p[i].y, p[j].x, p[j].y);
				pq.add(edge);
			}
		}

		boolean[] added = new boolean[n];
		double total = 0;// quantidade de tinta a gastar

		Edge temp = pq.remove();
		added[temp.node1] = true;
		added[temp.node2] = true;
		total += temp.getDistance();

		boolean found = false;
		ArrayList<Edge> tempList = new ArrayList<Edge>();

		while (!pq.isEmpty() && !found) {

			Edge temp2 = pq.remove();

			if ((added[temp2.node1] && !added[temp2.node2]) || (!added[temp2.node1] && added[temp2.node2])) {
				added[temp2.node1] = true;
				added[temp2.node2] = true;
				total += temp2.getDistance();
				pq.addAll(tempList);
				tempList.clear();
			} else if (!added[temp2.node1] && !added[temp2.node2])
				tempList.add(temp2);

		}
		System.out.printf("%.2f\n", total);
	}
}