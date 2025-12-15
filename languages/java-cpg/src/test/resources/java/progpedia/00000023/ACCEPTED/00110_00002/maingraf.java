import java.util.*;

class no {
	int peso;
	int receptor;
	no() {
	}
	no(int rec, int pes) {
		receptor = rec;
		peso = pes;
	}
}

class GRAF {
	HashMap<Integer, LinkedList<no>> adj = new HashMap<Integer, LinkedList<no>>();
	

	 void insereG(int o, int d, int p)
	 {
		if (adj.containsKey(o))
		{
			LinkedList<no> temp = adj.remove(o);
			no tempno = new no(d, p);
			temp.add(tempno);
			adj.put(o, temp);
		}
		else
		{
			LinkedList<no> temp = new LinkedList<no>();
			no tempno = new no(d, p);
			temp.add(tempno);
			adj.put(o, temp);
		}

	}  
	int Smallest(ArrayList<Integer> j)
	{
		int min = j.remove(j.size()-1);
		while(j.size()!=0)
		{
			if (min>j.get(j.size()-1))
			{
				min = j.remove(j.size()-1);
			}
		}
		return min;
	}
	int ProcuraQ(ArrayList<Integer> contidos, ArrayList<Integer> Dista)
	{
		int minIndex = 0;
		int minimum=Dista.get(0);
		while (contidos.size()!=0)
		{
			if (minimum>Dista.get(contidos.get(contidos.size()-1)))
			{
				minimum = Dista.get(contidos.get(contidos.size()-1));
				minIndex = contidos.get(contidos.size()-1);
			}
			contidos.remove(contidos.size()-1);
		}
		return minIndex;
	}
	ArrayList<Integer> Dijkstra(int n, int ng)
	{
		//System.out.println(n + " " + ng);
		ArrayList<Integer> prev = new ArrayList<Integer>(ng);
		ArrayList<Integer> Dist = new ArrayList<Integer>();// lista de distancias
		Dist.add(998127316);
		for (int i=0; i<ng; i++)
		{
			Dist.add(998127316);
			prev.add(i+1);
		}
		Dist.set(n, 0);
		
		HashMap<Integer, LinkedList<no>> Q = adj;
		while(Q.size()!=0)//enquanto Q nao for vazia
		{
			ArrayList<Integer> temp4 = new ArrayList<Integer>(prev.size());
			for(int i = 0; i<prev.size(); i++)
			{
				temp4.add(prev.get(i));
			}
			
			//(temp4,prev);
			
			int u = ProcuraQ(temp4, Dist);
			
			if (Dist.get(u)==998127316)
			{
				break;
			}
			//System.out.println(u);
			LinkedList<no> temp = Q.remove(u);
			if (temp!=null)
			{
				while (temp.size()!=0) // percore todos os vertex de vizinhos de u
				{
					int alt = Dist.get(u) + temp.peekLast().peso;
				
					if (alt<Dist.get(temp.peekLast().receptor))
					{
						Dist.set(temp.peekLast().receptor, alt);
					}
					no j = temp.removeLast();
				}
			}
			prev.remove(prev.indexOf(u));
		}
		return Dist;
	}
}

class maingraf {
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int nRegioes = stdin.nextInt();
		int Regiao = stdin.nextInt();
		int origem = 2;
		GRAF graf = new GRAF();
		while(origem!=-1)
		{
			origem = stdin.nextInt();
			if(origem!=-1)
			{
				int destino = stdin.nextInt();
				int peso = stdin.nextInt();
				graf.insereG(origem, destino, peso);
				graf.insereG(destino, origem, peso);
			}
		}
		ArrayList<Integer> Distancias = graf.Dijkstra(Regiao, nRegioes);
		//Collections.sort(Distancias);
		ArrayList<no> Distanciasporde = new ArrayList<no>();
		for(int i = 0; i<nRegioes; i++)
		{
			no temp = new no();
			temp.receptor = i+1;
			temp.peso = Distancias.get(i+1);
			Distanciasporde.add(temp);
		}
		 
		    for (int pass=1; pass < Distanciasporde.size(); pass++) {  // count how many times
		        // This next loop becomes shorter and shorter
		        for (int i=0; i < Distanciasporde.size()-pass; i++) {
		            if (Distanciasporde.get(i).peso > Distanciasporde.get(i+1).peso) {
		                // exchange elements
		                no temp = Distanciasporde.get(i); 
		                
		                Distanciasporde.set(i, Distanciasporde.get(i+1));
		                Distanciasporde.set(i+1, temp);
		            }
		        }
		    }
		    for(int i= 0; i<Distanciasporde.size(); i++)
		    {
		    	if (i!=Distanciasporde.size()-1)
		    		System.out.print(Distanciasporde.get(i).receptor + " ");
		    	else
		    		System.out.print(Distanciasporde.get(i).receptor);
		    }

		
	}
}
