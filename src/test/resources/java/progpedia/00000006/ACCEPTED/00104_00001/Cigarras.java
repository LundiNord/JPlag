import java.util.*;
import java.io.*;

public class Cigarras {
	static int verificar(int v[], int i, int n) {
		for (int j = i - 1; j >= 0; j--) {
			if (v[j] == n)
				return j;
		}
		return -1;
	}

	public static void main(String[] args) {
		int n, i = 0;
		int visitaram[] = new int[31];
		Scanner le = new Scanner(System.in);
		n = le.nextInt();
		visitaram[0] = n;
		while (n != 0) {
			i++;
			n = le.nextInt();
			if (verificar(visitaram, i, n) != -1) {
				int aux = verificar(visitaram, i, n);
				for (int j = aux + 1; j < i - 1; j++) {
					visitaram[j] = 0;
				}
				i = aux;
			}
			visitaram[i] = n;
		}
		for (int j = 0; j < i; j++) {
			if(visitaram[j]!=0)
			System.out.println(visitaram[j]);
		}
	}

}
