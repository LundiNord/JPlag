import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Sardas {

	public static class Point{

		double x;
		double y;


		public Point(double x, double y) {
			this.x = x;
			this.y = y;
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
		}

		
		doPrim(board,board.remove(0),0,n);

		System.out.printf("%.2f\n", finalDist);
	}

	private static void doPrim(HashMap<Integer, Point> board, Point point, int p, int n) {

		while(!board.isEmpty()){

			p = extractMin(board,point,p,n);
			point = board.remove(p);
		}

	}

	private static int extractMin(HashMap<Integer, Point> board, Point point, int p, int n) {

		double min = Double.MAX_VALUE;
		double count = 0;
		int minPoint = 0;
		double difx;
		double dify;

		for(int i = 1; i < n; i++){
			
			if(board.containsKey(i)){
				Point aux = board.get(i);
				difx = Math.abs(point.x - aux.x);
				dify = Math.abs(point.y - aux.y);
				count = Math.sqrt(Math.pow(difx, 2) + Math.pow(dify, 2));

				if(count < min){
					min = count;
					minPoint = i;
				}
			}
		}
		
		finalDist += min;

		return minPoint;
	}

}
