import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class NegocioElectronico { 
	static final int inf=Integer.MAX_VALUE;
	static int nlojas;
	static Gnode[] lista;
	
	static class Gnode {
		int id, d;
		LinkedList<Edge> adj;
		
		Gnode(int id) {
			this.id=id;
			d=inf;
			adj=new LinkedList<Edge> ();
		}
	}
	
	static class Edge {
		Gnode d; //destino
		int p;
		Edge(Gnode d, int p) {
			this.d=d;
			this.p=p;
		}
	}

	public static void main(String[] args) {
		int destino, a, b, p;
		Edge tmp;
		Gnode noA, noB;
		Scanner in;
		
		in=new Scanner(System.in);
		nlojas=in.nextInt();
		destino=in.nextInt();
		
		lista=new Gnode[nlojas];
		
		for(int i=0; i<nlojas; i++)
			lista[i]=new Gnode(i+1);
		
		boolean terminou=false;
		
		while(!terminou) {
			a=in.nextInt();
			if(a<0)
				terminou=true;
			else {
				b=in.nextInt();
				p=in.nextInt(); //peso
				noA=lista[a-1];
				noB=lista[b-1];
				
				tmp=new Edge(noB,p);	
				noA.adj.add(tmp);
				tmp=new Edge(noA,p);
				noB.adj.add(tmp);
			}
		}
		Dijkstra(destino);
	}

	private static void Dijkstra(int destino) {
		lista[destino-1].d=0;
		ArrayList<Gnode> fila=new ArrayList<Gnode> ();
		StringBuilder res=new StringBuilder(nlojas*2); //For performance
		
		for(Gnode i: lista)
			fila.add(i);
		
		while(!fila.isEmpty()) {
			Gnode u=ExtractMin(fila);	
			res.append(u.id+" ");
			
			for(Edge v: u.adj)
				Relax(u,v.d,v.p);
		}
		
		res.deleteCharAt(res.length()-1);
		System.out.println(res);
	}
	
	private static Gnode ExtractMin(ArrayList<Gnode> fila) {
		int size=fila.size();
		int min=inf;
		int index=-1;
		
		for(int i=0; i<size; i++) {
			Gnode tmp=fila.get(i);
			if (tmp.d<min) {
				min=tmp.d;
				index=i;
			}
		}
		return fila.remove(index);
	}

	private static void Relax(Gnode u, Gnode v, int peso) {
		if (v.d>u.d+peso)
			v.d=u.d+peso;
	}
}
