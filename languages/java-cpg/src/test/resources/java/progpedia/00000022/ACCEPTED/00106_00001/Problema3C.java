import java.util.*;

class Problema3C{
    public static void main (String args[]){
	Scanner in = new Scanner (System.in);
	int pontos = in.nextInt();
		
	//	Grafo G = new Grafo(pontos);
		
	int lmin = in.nextInt();
	int lmax = in.nextInt();
	int cmin = in.nextInt();
	int cmax = in.nextInt();
	int amin = in.nextInt();
		
	int inicio = in.nextInt();
	int fim = in.nextInt();
		
	int ponto = in.nextInt();
	int conta = 0;
		
	while (ponto != -1){
	    int ponto2 = in.nextInt();
	    int l = in.nextInt();
	    int c = in.nextInt();
	    int a = in.nextInt();

	    if (lmin <= l && cmin <= c && amin <= a)
	    	conta++;
	    ponto = in.nextInt();
	}
		
	System.out.println (conta);
    }
}