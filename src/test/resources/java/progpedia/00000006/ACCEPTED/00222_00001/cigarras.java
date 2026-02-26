import java.util.Scanner;

public class cigarras {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] vec = new int[30];
		int n = 0;
		elimina(vec);
	}

	public static void elimina(int[] caminho) {
		int inicio = in.nextInt();
		in.nextLine();
		caminho[0] = inicio;
		int nnos = 1;
		int novo = in.nextInt();
		in.nextLine();
		while (novo != 0) {
			nnos = insere(caminho, nnos, novo);
			novo = in.nextInt();
			in.nextLine();
		}
		escreve(caminho, nnos);
	}

	public static int insere(int[] caminho, int nnos, int novo) {
		for(int i=0; i<=(nnos-1); i++) {
			if (caminho[i]==novo) {
				return i+1;
			}
		}
		caminho[nnos]=novo;
		return nnos+1;
	}
	
	public static void escreve(int []caminho,int nnos) {
		for(int i=0;i<=(nnos-1); i++) {
			System.out.print(caminho[i] + " \n");
		}
	}

}
