import java.util.Arrays;
import java.util.Scanner;

public class Prob03 {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int v[] = new int[n];

		for (int i = 0; i < n; i++) {
			int num = stdin.nextInt();
			v[i] = num;
		}
		Arrays.sort(v);

		// System.out.print("Depois de ordenar: ");
		for (int i = 0; i < n; i++)
			System.out.print(v[i] + " ");
		System.out.println();
	}
}
