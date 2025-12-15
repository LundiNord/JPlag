import java.util.Scanner;

public class Prob21{

	public static void main(String[] args) throws Exception {
		Scanner stdin = new Scanner(System.in);

			int n = stdin.nextInt();
			Double total = Double.valueOf(0);
			
			Double[][] freckles = new Double[n][2];
			int[] sets = new int[n];

			for (int i = 0; i < n; i++) {
				freckles[i][0] = stdin.nextDouble();
				freckles[i][1] = stdin.nextDouble();
				sets[i] = i; 
			}

			for (int i = 1; i < n; i++) {

				Double shortest = Double.MAX_VALUE;
				int s = -1;

				for (int j=0; j < n; j++) {
					s = -1;

					if ( sets[i] != sets[j]) {

						Double d = findDistance(freckles[i], freckles[j]);
						if (d < shortest) {
							shortest = d;
							s = sets[j];
						}
					}
				}

				total += shortest;
				sets[i] = s;
			}

			System.out.printf("%.2f\n", total);
	}

	private static double findDistance(Double[] f1, Double[] f2) {
		return Math.sqrt( ((f1[0] - f2[0])*(f1[0] - f2[0])) + ((f1[1] - f2[1])*(f1[1] - f2[1])) );
	}
}