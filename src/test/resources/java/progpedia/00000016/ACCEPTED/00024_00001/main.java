import java.util.*;
class graf
{
	HashMap<String, String> adj = new HashMap<String, String>();
	ArrayList<String> origens = new ArrayList<String>();
	ArrayList<String> destinos = new ArrayList<String>();
	
	void inserir(char origem, char destino)
	{
		String o = "" + origem;
		String d = "" + destino;
		origens.add(o);
		destinos.add(d);
		adj.put(o, d);
	}
	void check()
	{
		origens.removeAll(destinos);
		while(destinos.size()!=0)
		{
			System.out.print(origens.get(0));
			origens.add(adj.get(origens.get(0)));
			origens.remove(0);
			destinos.remove(origens.get(0));		
		}
	}
}
class main {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		String primeira = stdin.nextLine();
		String segunda = stdin.nextLine();
		graf grafo = new graf();
		int ink =0;
			int min = Math.min(primeira.length(), segunda.length());
			for (int i =0; i<min; i++ )
			{
				if(primeira.charAt(i)!=segunda.charAt(i))
				{
					grafo.inserir(primeira.charAt(i), segunda.charAt(i));
					ink=1;
					System.out.println(primeira.charAt(i)+""+segunda.charAt(i));
					break;
				}
				else
				{
					continue;
				}
			}
			
		if(ink!=1)
			System.out.println("Nenhum");
		
	}

}
