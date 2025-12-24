import java.io.*;
import java.util.*;


/*

#PROBLEMA 4 - ORDENAÇAO COSTUM#

class Equipa implements Comparable<Equipa> {
    String nome;
    int vit;
    int emp;
    int der;
    int golosm;
    int goloss;

    Integer pontos;
    Integer gdif;

    Equipa (String n, int v, int e, int d, int gm, int gs) {
	nome   = n;
	vit    = v;
	emp    = e;
	der    = d;
	golosm = gm;
	goloss = gs;
	
	pontos = vit * 3 + emp;
	gdif = golosm - goloss;
    }

    @Override
    public int compareTo(Equipa n) {
	if (pontos.equals(n.pontos)) {
	    if (gdif.equals(n.gdif))
		return nome.compareTo(n.nome);
	    else 
		return n.gdif.compareTo(gdif);
	}
	else 
	    return n.pontos.compareTo(pontos);
    }
}


class TesteP1 {
    public static void main (String args[]) {
	
	Scanner stdin = new Scanner(System.in);
	int N = stdin.nextInt();
	Equipa v[] = new Equipa[N];

	for (int i=0; i<N; i++)
	    v[i] = new Equipa(stdin.next(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt());
	
	Arrays.sort(v);

	for (int i = 0; i<N; i++)
	    System.out.println(v[i].nome + " " + v[i].pontos + " " + v[i].gdif);

    }
}

*/




class TesteP1 {    
    public static void bsearch (int v[],int low, int high, int key) {
		
	while (low < high) {
	    int middle = low + (high-low)/2;
	    if (v[middle] > key) {
		high = middle;
	    }
	    else 
		low = middle + 1;
	}
	
	if (Math.abs(v[low]-key) == Math.abs(v[low-1]-key))
	    System.out.println(v[low-1]+" "+v[low]);
	
	else if (Math.abs(v[low]-key) < Math.abs(v[low-1]-key))
	    System.out.println(v[low]);

	else
	    System.out.println(v[low-1]);
    }
	    
    
    public static void main (String args[]) {
	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt();
	int s[] = new int[n];
	
	for (int i=0; i<n; i++) 
	    s[i] = stdin.nextInt();
	  
	int np = stdin.nextInt();
	int p[] = new int[np];

	for (int i=0; i<np; i++)
	    p[i] = stdin.nextInt();
       
	int lim = n*(n-1)/2;
	int somas[] = new int[lim];
	int k=0;

	for (int i=0; i<lim; i++)
	    for(int j=i+1; j<n; j++) {
		somas[k] = s[i] + s[j];
		k++;
	    }

	Arrays.sort(somas);

	for (int i=0;i<np;i++)
	    bsearch(somas,1,somas.length-1,p[i]);

    }
}