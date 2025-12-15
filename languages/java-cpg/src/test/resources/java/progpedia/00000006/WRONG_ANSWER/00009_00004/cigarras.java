import java.util.*;

class cigarras {
	public static void main (String [] args) {
		int locais[], i, n;
		Scanner kb = new Scanner(System.in);

		locais = new int[30];
		for (i=0; i < 30; i++) locais[i] = 0;

		n = kb.nextInt();
		while (n != 0) {
			for (i=0; i < 30; i++) {
				if (locais[i] == n) {
					if (i+1 < 30) locais[i+1] = 0;
					break;
				} else if (locais[i] == 0) {
					locais[i] = n;
					break;
				}
			}
			n = kb.nextInt();
		}

		i = 0;
		while (locais[i] != 0) {
			System.out.println(locais[i]);
			i++;
		}
	}
}