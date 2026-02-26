import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader stdin = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st = new StringTokenizer("");

	// Read next input-token as string.
	static String readString() throws Exception {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(stdin.readLine());
		}
		return st.nextToken();
	}

	// Read next input-token as integer.
	static int readInt() throws Exception {
		return Integer.parseInt(readString());
	}

	// Read next input-token as double.
	static double readDouble() throws Exception {
		return Double.parseDouble(readString());
	}

	public static void main(String[] args) throws Exception {


			Double total = Double.valueOf(0);
			int numberOfFreckles = readInt();

			Double[][] freckles = new Double[numberOfFreckles][2];
			int[] sets = new int[numberOfFreckles];

			for (int i = 0; i < numberOfFreckles; i++) {
				freckles[i][0] = readDouble();
				freckles[i][1] = readDouble();
				sets[i] = i; //legger alle fregner i et set
			}

			for (int i = 1; i < numberOfFreckles; i++) {

				// curretn set sets[i];
				Double shortest = Double.MAX_VALUE;
				int s = -1;

				// finn naermeste fregne fra f[i] som ikke er i samme sett
				for (int j=0; j < numberOfFreckles; j++) {
					s = -1;

//					System.out.println("set i: " + sets[i] + " set j: " + sets[j]);

					if ( sets[i] != sets[j]) {

						Double n = findDistance(freckles[i], freckles[j]);
						if (n < shortest) {
							shortest = n;
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
