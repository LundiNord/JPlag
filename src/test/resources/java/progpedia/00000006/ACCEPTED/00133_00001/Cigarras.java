import java.util.*;

class Cigarras {

	public static int ja_existe (int cam, int caminho[], int percorrido){
		int i = 0, ind = -1;
		while ((i < percorrido) && (ind == -1)){
			if (caminho[i] == cam) ind = i;
			i++;
		}
		return ind;
	}

	public static void main (String args[]){
		Scanner kb = new Scanner (System.in);
		int[] caminho = new int[31];
		int percorrido = 0;
		int cam = kb.nextInt();
		int indice;
		while (cam != 0){
			indice = ja_existe(cam, caminho, percorrido);
			if (indice == -1) caminho[percorrido++] = cam;
			else percorrido = indice+1;
			cam = kb.nextInt();
		}
		int i;
		for (i = 0; i < percorrido; i++) System.out.println(caminho[i]);
	}
}