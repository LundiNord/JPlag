import java.util.Scanner;

public class CigarrasTontas {
	
	public static void main (String[] args) {
		
		Scanner console = new Scanner(System.in);
		
		int[] locais = new int[30];
		
		int x = console.nextInt();
		int p = 0;
		while (x != 0) {
			p = contem(locais,p,x);
			locais[p] = x;
			x = console.nextInt();
			p++;
		}
		
		console.close();
		
		for (int i=0; i<p; i++) {
			System.out.println(locais[i]);
		}
	}
	
	private static int contem (int[] locais, int p, int x) {
		for (int i=0; i<p; i++) {
			if (locais[i] == x) {
				p = i;
				break;
			}
		}
		return p;
	}
}
