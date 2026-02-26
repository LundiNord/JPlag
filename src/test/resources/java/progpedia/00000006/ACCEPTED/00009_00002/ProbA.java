import java.util.*;

class ProbA {

    public static void main (String args[]){
	int n, i;
	int numeros[] = new int[30];
	Scanner kb = new Scanner(System.in);
	
	for (i=0; i < 30; i++)
	    numeros[i] = 0;

	numeros[0] = kb.nextInt();
	
	while (kb.hasNextInt()) {
	    i = 0;
	    n = kb.nextInt();
	    while (numeros[i] != 0) {
		if (numeros[i] == n) {
		    numeros[i+1] = 0;
		}
		i++;
	    }

	    if (numeros[i-1] != n) {
		numeros[i] = n;
		numeros[i+1] = 0;
	    }
	}

	for (i=0; numeros[i] != 0; i++)
	    System.out.println(numeros[i]);
    }

}
