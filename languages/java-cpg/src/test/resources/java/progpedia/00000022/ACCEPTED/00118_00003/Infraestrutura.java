//package daa2;

import java.util.Scanner;

public class Infraestrutura {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		in.nextInt();

		int largMin = in.nextInt();
		in.nextInt();
		int compMin = in.nextInt();
		in.nextInt();
		int altMin = in.nextInt();

		int nTrocos = 0;

		in.nextInt();
		in.nextInt();
		
		int larg;
		int comp;
		int alt;

		while (true) {
			if (in.nextInt() == -1) {
				break;
			}
			
			in.nextInt();
			
			larg = in.nextInt();
			comp = in.nextInt();
			alt = in.nextInt();
			

			if (larg >= largMin && comp >= compMin	&& alt >= altMin) {
				nTrocos++;
			}

		}

		System.out.println(nTrocos);
		in.close();
	}

}
