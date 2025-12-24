import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
class dados{
	int liga;
	int larg,comp,alt;
	public dados(int d, int a, int b,int c) {
		liga=d;
		larg=a;
		comp=b;
		alt=c;
	}
}
class Adjs {
	LinkedList<dados> adjs = new LinkedList<dados>();
}
class Grafo {
	Adjs verts [];
			
	public Grafo(int nv) {
		verts  = new Adjs[nv+1];
		// TODO Auto-generated constructor stub
		for ( int i = 0 ; i <= nv ; i++)
			verts[i] = new Adjs();
	}
	
}
public class infra {

public static int analisaDimensoes(Scanner stdin, int largmax, int largmin, int compmax, int compmin, int altmin){
		int largmaxtroco = stdin.nextInt();
		int compmaxtroco = stdin.nextInt();
		int altmaxtroco = stdin.nextInt();
		int checks = 0;
		if(largmaxtroco >= largmin)
			checks++;
		if(compmaxtroco >= compmin)
			checks++;
		if(altmaxtroco >= altmin)
			checks++;
		if(checks < 3)
			return 0;
		return 1;
}

	public static void main (String args[]){
		Scanner io = new Scanner(System.in);
		int n = io.nextInt();
		int lmin = io.nextInt();
		int lmax = io.nextInt();
		int cmin = io.nextInt();
		int cmax = io.nextInt();
		int amin = io.nextInt();
		int inicio = io.nextInt();
		int destino = io.nextInt();
		Grafo a = new Grafo(n);
		int origem = io.nextInt();
		int largura[] = new int [n+1];
		int res=0;
		while(origem!=-1)
		{
			int liga = io.nextInt();
			res+=analisaDimensoes(io, lmax, lmin, cmax, cmin, amin);
			origem = io.nextInt();
		}
		System.out.println(res);
		
	}
}
