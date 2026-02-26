import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Sardas {

	public static class Point{

		double x;
		double y;
		double dist;
		Point father;
		HashMap<Integer, Double> weight;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
			dist = Double.MAX_VALUE;
			weight = new HashMap<Integer, Double>();
		}

	}

	static double finalDist = 0.00;

	public static void main(String[] args) {

		int n;
		double a, b;
		Scanner in = new Scanner(System.in);

		HashMap<Integer, Point> board = new HashMap<Integer, Point>();

		n = in.nextInt();

		for(int i = 0; i < n; i++){

			a = in.nextDouble();
			b = in.nextDouble();

			board.put(i, new Point(a,b));	

			if(i==0)
				board.get(i).dist = 0;
		}

		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				Point p1 = board.get(i);
				Point p2 = board.get(j);
				double count = doDist(p1,p2);
				p1.weight.put(j, count);
				p2.weight.put(i, count);
			}
		}

		HashMap<Integer, Point> boardCopy = new HashMap<Integer, Point>();
		boardCopy.putAll(board);
		doPrim(board,n);
		doMinDist(boardCopy,n);
		System.out.printf("%.2f\n", finalDist);
	}

	private static void doMinDist(HashMap<Integer, Point> boardCopy, int n) {

		for(int i=0; i < n; i++){
			finalDist += boardCopy.get(i).dist;
		}

	}

	private static double doDist(Point p1, Point p2) {

		double difx = Math.abs(p1.x - p2.x);
		double dify = Math.abs(p1.y - p2.y);
		double count = Math.sqrt(Math.pow(difx, 2) + Math.pow(dify, 2));

		return count;
	}

	private static void doPrim(HashMap<Integer, Point> board, int n) {

		while(!board.isEmpty()){

			int p = extractMin(board, n);
			Point point = board.remove(p);

			for(int i = 1; i < n; i++){

				if(board.containsKey(i)){

					Point aux = board.get(i);

					if(point.weight.get(i) < aux.dist){
						aux.dist = point.weight.get(i);
						aux.father = point;
					}
				}

			}
		}

	}

	private static int extractMin(HashMap<Integer, Point> board, int n) {

		double minDist = Double.MAX_VALUE;

		int p = 0;

		for(int i = 0; i < n; i++){
			if(board.containsKey(i)){
				Point aux = board.get(i);

				if(aux.dist < minDist){
					minDist = aux.dist;
					p = i;
				}
			}
		}
		return p;
	}


}
