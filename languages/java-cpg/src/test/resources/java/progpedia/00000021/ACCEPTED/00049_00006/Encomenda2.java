import java.io.*;
import java.util.*;

class Encomenda2{
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		int lmin = scn.nextInt(); // largura minima
		int lmax = scn.nextInt(); // largura maxima
		int cmin = scn.nextInt(); // comprimento minimo
		int cmax = scn.nextInt(); // comprimento maximo
		int amin = scn.nextInt(); // altura minima
		int origem = scn.nextInt();
		int destino = scn.nextInt();
		LinkedList<Adj> A = new LinkedList<Adj>();
		
		int imax=0;
		int x = scn.nextInt();
		while (x != -1){
			int y = scn.nextInt();
			imax = Math.max(Math.max(x, y), imax);	// indice maximo
			int lm = scn.nextInt();
			int cm = scn.nextInt();
			int am = scn.nextInt();
			if (lmin<=lm && cmin<=cm && amin<=am)
				A.add(new Adj(x, y, Math.min(cm, cmax)));
			
			x = scn.nextInt();
		}
		
		Collections.sort(A);
		
		DisjointSet dj = new DisjointSet(imax+1);
		for (int i=1; i<=imax; i++)
			dj.MakeSet(i);
		
		Adj a = null;
		while (dj.FindSet(origem) != dj.FindSet(destino) && !A.isEmpty()){
			a = A.remove();
			dj.Union(a.x, a.y);
		}
		if (dj.FindSet(origem) == dj.FindSet(destino)) System.out.println(a.p);
		else System.out.println("0");
	}
}

class DisjointSet{
	static int pai[];
	static int rank[];
	
	DisjointSet(int len){
		pai = new int[len];
		rank = new int[len];
	}
	public static void MakeSet(int x){
		pai[x] = x;
		rank[x] = 0;
	}
	public static void Union(int x, int y){
		Link(FindSet(x), FindSet(y));
	}
	public static int FindSet(int x){
		if (x != pai[x]) pai[x] = FindSet(pai[x]);
		return pai[x];
	}
	public static void Link(int x, int y){
		if (rank[x] > rank[y]) pai[y] = x;
		else{
			pai[x] = y;
			if (rank[x] == rank[y]) rank[y]++;
		}
	}
}

class Adj implements Comparable<Adj>{
	int x, y, p;
	
	Adj(int x, int y, int p){
		this.x = x;
		this.y = y;
		this.p = p;
	}	
	public int compareTo(Adj a){
		return a.p - this.p;
	}
}