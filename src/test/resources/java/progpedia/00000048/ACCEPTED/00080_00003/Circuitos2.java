import java.util.*;
import java.io.*;

class Ligacao
 {
	int indice;
	LinkedList<Ligacao> adj;
	boolean visitado;
	
	Ligacao(int p)
	{
		indice = p;
		adj = new LinkedList<Ligacao>();
		visitado = false;
	}
	
	void addLigacao(Ligacao l)
	{
		adj.addLast(l);
	}
 }

class Grafo
{
	Ligacao pontos[];
	int npontos;
	int nligacoes;
	int nvisitados;
	int nredes;
	
	
	Grafo(int np, int nlig)
	{
		npontos = np;
		nligacoes = nlig;
		pontos = new Ligacao[np+1];
		nvisitados = 0;
		//nredes = 0;
		
		
	}
	
	void createGrafo(Scanner in)
	{
		for(int i = 0; i<nligacoes;i++)
		{
			int p1= in.nextInt();
			int p2 = in.nextInt();
			if(pontos[p1]== null)
				pontos[p1] = new Ligacao(p1);
			if(pontos[p2] == null)
				pontos[p2] = new Ligacao(p2);
			pontos[p1].addLigacao(pontos[p2]);
			pontos[p2].addLigacao(pontos[p1]);
			
		}
	}
	
	
	void dfs(Ligacao l)
	{
		l.visitado = true;
		for(Ligacao x:l.adj)
			if(!x.visitado)
				dfs(x);
	}
	
	
	int contaredes()
	{
		int nredes = 0;
		
		if(nligacoes>0)
		{			
			for(int i = 1; i<= npontos; i++)
			{
				if (pontos[i] != null)
				{
					pontos[i].visitado = false;
				}
			}
		
			for(int i = 1;i<=npontos;i++)
			{
				if (pontos[i] == null)
				{
					nredes++;
				}
				else if(!(pontos[i].visitado))
				{
					nredes++;
					dfs(pontos[i]);
				}
			}
		}
		else
		{
			nredes = npontos;
		}
		
		return nredes;
	}
  
  
}


class Circuitos2
{
	public static void main(String args[])
	{
		Scanner in =  new Scanner(System.in);
		int npontos = in.nextInt();
		int nligacoes = in.nextInt();
		Grafo g = new Grafo(npontos, nligacoes);
		g.createGrafo(in);
		int nredes = g.contaredes();
		System.out.println(nredes);
		
	}
}
