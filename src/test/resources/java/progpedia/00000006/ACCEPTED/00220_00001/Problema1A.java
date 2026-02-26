import java.util.*;

class Problema1A{
    public static void main (String args[]){
	Scanner in = new Scanner (System.in);
	int caminho[] = new int [10000];
	int ponto1 = in.nextInt();
	int ponto2 = in.nextInt();

	for (int j = 0; j < 10000; j++)
	    caminho[j] = -1;

	caminho[0] = ponto1;
	int i = ponto1;

	while (ponto2 != 0){
	    caminho[i] = ponto2;
	    i = ponto2; 
	    ponto2 = in.nextInt();
	}

	boolean t = true;

	i = caminho[0];

	while (t == true){
	    System.out.println(i);

	    if (caminho[i] != -1)
		i = caminho[i];
	    else
		t = false;
	}
    }
}