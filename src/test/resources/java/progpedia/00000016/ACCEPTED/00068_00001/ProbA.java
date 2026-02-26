import java.util.LinkedList;
import java.util.Scanner;

/*class Adjs{ // lista de ajacencias representa a lista dos filhos de um no
	LinkedList<String> adjs = new LinkedList <String>();
	}

class Grafo{
	Adjs verts []=new Adjs[26]; ; // vector de listas, que representa para cada no a sua lista de filhos
	
	Grafo(){
		for(int i=0 ; i < verts.length ; i++)
		{
			verts[i] = new Adjs();
		}
	}
	void procurar(int posicao, String a)
	{
		verts[posicao].adjs.add(a);
	}
	
	

}*/
public class ProbA {
	public static final int total=20;
	/*static void inserir (Grafo g[], String s, int posicao)
	{
		Scanner in2 = new Scanner(s);
		int i=0;
		while(in2.hasNext())
		{
			String s2 = in2.next();
			g[posicao].verts[i].adjs.add(s2);
			i++;
		}
	}
	static void imprimir (Grafo g[], int posicao)
	{
		for(int i =0; i<g[posicao].verts.length;i++)
		{
			if(!g[posicao].verts[i].adjs.isEmpty())
			{
				String s = g[posicao].verts[i].adjs.removeFirst();
				System.out.println(s);
			}
			
				
		}
	}*/
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		
		String S1 = in.nextLine();
		String S2 = in.nextLine();
		int i=0;
			for(i=0;i<S1.length();i++)
			{
				char c = S1.charAt(i);
				char c2 = S2.charAt(i);
				if(c != c2 && c2 != '\n'){
					System.out.print(c);
					System.out.print(c2);
					break;
				}
			}
			if(i==S1.length())
				System.out.println("Nenhum");
		
		
	}
	
}
