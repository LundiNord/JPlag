/* Problema de Programacao Dinamica. Resolvido recursivamente. A solucao recursiva excedia o limite de tempo para alguns casos de teste, portanto, apos identificar os subproblemas comuns, foi criada uma matriz que nos diz se e possivel, a partir da posicao x y, chegar ao fundo (o "cursor" parte do topo!). Inicialmente, a metade triangular inferior da matriz piramide e marcada toda como true (os arrays boolean em java sao inicializados, por defeito, a false). Ao receber o input, marcamos as pedras com as coordenadas que nos sao passadas como False, para que nao seja possivel visita-las. O metodo sobe comeca em 0,0 (chamado no main) e desce para x+1 y (esq) e x+1 y+1 (dir) se essas pedras nao forem false. quando x == n-1, chegou ao topo, e retorna 1 para que fique armazenado um valor diferente de -1. count x y = numero de caminhos possiveis para a base passando na pedra x y.*/

import java.io.*;
import java.util.*;

public class DAA12 {
    static boolean[][] piramide;
    static int n;
    static int d;
    //static int caminhos = 0;
    static long[][] count;

    public static long sobe(int x, int y) {
	if (count[x][y] != -1)
	    return count[x][y];

	if (x == n-1)
	    return 1;
	else {
	    long c = 0;
	    if (piramide[x+1][y])
		c += sobe(x+1,y);
	    if (piramide[x+1][y+1])
		c += sobe(x+1,y+1);
	    count[x][y] = c;
	    return c;
	}
    }

    public static void main (String[] args) {
	Scanner reader = new Scanner(System.in);
	n = reader.nextInt();
	d = reader.nextInt();
	piramide = new boolean[n][n];
	count = new long[n][n];

	for (int i = 0; i < n; i++)
	    Arrays.fill(count[i], -1);

	for (int i = 0; i < n; i++) {
	    for (int j = 0; j <= i; j++) {
		piramide[i][j] = true;
	    }
	}
	for (int i = 0; i < d; i++) {
	    piramide[n-reader.nextInt()][reader.nextInt()-1] = false;
	}

	if (piramide[0][0])
	    System.out.println(sobe(0,0));
	else
	    System.out.println(0);
    }
}
