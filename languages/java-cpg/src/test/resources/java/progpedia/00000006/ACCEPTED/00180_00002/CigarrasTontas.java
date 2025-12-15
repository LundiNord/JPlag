import java.util.Scanner;

public class CigarrasTontas {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int v[] = new int[30];
		int local = in.nextInt();
		v[0] = local;
		int c = 0;
		
		local = in.nextInt();
		while (local != 0) {
			c = insere(v, local, c);
			local = in.nextInt();
		}
		
		escreverResultado(v, c);
		in.close();
	}
	
	public static int insere(int v[], int local, int c) {
		int j = 0;
		while (j <= c && v[j] != local)
			j++;
		v[j] = local;
		return j;
	}
	
	public static void escreverResultado(int[] v, int c) {
		for (int j = 0; j <= c; j++)
			System.out.println(v[j]);
	}

}
