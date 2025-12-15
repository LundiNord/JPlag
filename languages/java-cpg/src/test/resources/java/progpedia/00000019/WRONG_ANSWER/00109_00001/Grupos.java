import java.io.*;
import java.util.*;

class TestGrupos
{	
	static void verAlunos(int cenario, int n_alunos, Vertice[] alunos)
	{
		int aluno, n_amigos, amigo;
	
		Grupos.pw.print("\n\n\nCenario " + cenario);  //ver cenario
	
		for(aluno = 0; aluno < n_alunos; aluno++)
		{
			n_amigos = alunos[aluno].getNAmigos();
		
		
			if(n_amigos > 0)
			{
				Grupos.pw.print("\n\n aluno: " + (aluno+1) + " tem " + n_amigos + " amigo(s) que sao: ");
			
				for(amigo = 0; amigo < n_amigos; amigo++)
					Grupos.pw.print("\n\t" + (alunos[aluno].Amigo(amigo)+1));
			}
			
			else
				Grupos.pw.print("\n\n aluno: " + (aluno+1) + " nao tem amigos.");
		}
		
		Grupos.pw.print("\n\n");
		
		// PrintWriter nao retorna IOExceptions mas
        // oferece  o metodo catchError()
        if (Grupos.pw.checkError ())
            Grupos.pw.println ("An output error occurred!" );
	}
	
	
	static void listaLCFC(int cenario, Lista_CFC lista)
	{
		CFC i;
		
		Grupos.pw.print("\n\n\nCenario: " + cenario);
		
		for(i=lista.getPrimeiro(); i!=null; i=i.getNext())
			showStack(i.getComponente());
	}
	
	
	static void showStack(Stack pilha)
	{
		int[] stack;
		int n_elems = pilha.elemsStack(), i;
	
		stack = pilha.getElements();
		
		Grupos.pw.print("\n\n\t Pilha:");
		
		if(n_elems>0)
			for(i=0; i <n_elems; i++)
				Grupos.pw.print("\n\t\t" + (stack[i]+1));
		
		else
			Grupos.pw.println("\n\tSem elementos");
			
		Grupos.pw.print("\n\n");
	}
}

class Vertice
{
	private int n_amigos;
	int[] amigos;
	
	int index;
	int lowlink;
	
	Vertice(int n, int[] ami)
	{
		n_amigos = n;	
		amigos = ami;
		
		index = -1;
		lowlink = -1;
	}
	
	int getNAmigos()
	{
		return(n_amigos);
	}
	
	int Amigo(int x)
	{
		return(amigos[x]);
	}
	
	void setIndex(int ind)
	{
		index = ind;
	}
	
	int getIndex()
	{
		return(index);
	}
	
	void setLowlink(int ll)
	{
		lowlink = ll;
	}
	
	int getLowlink()
	{
		return(lowlink);
	}
	
	boolean isAmigo(int a)
	{
		int i;
		 
		for(i=0; i<n_amigos; i++)
			if(amigos[i] == a)
				return(true);
		
		return(false);
	}
}

class Resultados
{
	Resultado primeiro;
	Resultado ultimo;

	Resultados()
	{
		primeiro = null;
		ultimo = null;
	}
	
	Resultado getPrimeiro()
	{
		return(primeiro);
	}

	void adicionarResultado(Resultado res)
	{
		if(primeiro == null)
		{
			primeiro = res;
			ultimo = res;
		}
		
		else
		{
			ultimo.setProximo(res);
			
			ultimo=res;
		}
	}
}

class Resultado
{
	int caso; //variavel desnecessario - incluida para testes
	
	int gruposDe4;
	int outrosElementos;
	
	Resultado next;
	
	Resultado(int cas, int grupos, int outros)
	{
		caso = cas;
		gruposDe4 = grupos;
		outrosElementos = outros;
		
		next = null;
	}
	
	Resultado(int cas)
	{
		caso = cas;

		gruposDe4 = 0;
		outrosElementos = 0;
		
		next = null;
	}
	
	int getCaso()
	{
		return(caso);
	}
	
	void addGrupoDe4()
	{
		gruposDe4++;
	}
	
	int getGruposDe4()
	{
		return(gruposDe4);
	}
	
	void addOutrosElementos()
	{
		outrosElementos++;
	}
	
	int getOutrosElementos()
	{
		return(outrosElementos);
	}
	
	void setProximo(Resultado res)
	{
		next = res;
	}
	
	Resultado proximo()
	{
		return(next);
	}
} 

class Lista_CFC
{
	CFC primeiro, ultimo;

	Lista_CFC()
	{
		primeiro = null;
		ultimo = null;
	}
	
	CFC getPrimeiro()
	{
		return(primeiro);
	}
	
	void add2Lista(Stack pilha)
	{
		CFC compfc = new CFC(pilha);
		
		if(primeiro == null)
			primeiro = compfc;
			
		else
			ultimo.setNext(compfc);
			
		ultimo = compfc;
	}
}

class CFC //Componente Fortemente Conexa
{
	Stack componente;
	CFC next;
	
	CFC(Stack comp)
	{
		componente = comp;
		next = null;
	}
	
	Stack getComponente()
	{
		return(componente);
	}
	
	void setNext(CFC no)
	{
		next = no;
	}
	
	CFC getNext()
	{
		return(next);
	}
}

class Stack
{
	int elems;
	Node first;

	Stack()
	{
		elems = 0;
		first = null;
	}
	
	int elemsStack()
	{
		return(elems);
	}
	
	void push(int x)
	{
		Node no = new Node(x);
	
		no.setNext(first);
		first = no;
		
		elems++;
	}
	
	int pop()
	{
		int x = first.getValor();
		
		first = first.getNext();
		
		elems--;
		
		return(x);
	}
	
	boolean isEmpty()
	{
		return(first==null);
	}
	
/*	void emptyStack()
	{
		elems = 0;
		first = null;
	}
*/	
	boolean elemInStack(int x)
	{
		Node i;
		
		for(i=first; i!=null; i=i.getNext())
			if(i.getValor()==x)
				return(true);
				
		return(false);
	}
	
	int[] getElements()
	{
		int[] elements = new int[elems];
		int j;
		Node i;
		
		for(i=first, j=elems-1; i!=null; i=i.getNext(), j--)
		{
			elements[j] = i.getValor();
		}
		
		return(elements);
	}
	
	Stack getTill(int v)
	{
		Node nova;
		int i;
		Stack s = new Stack();
		
		do
		{
			i = this.pop();
		
			s.push(i);
		}
		while(i!=v);
		
		return(s);
	}
	
	int verPosicao(int ind)
	{
		Node j;
		int i;
	
		for(i=0, j = first; i<ind && j!= null; i++, j=j.getNext());
	
		if(j!=null)
			return(j.getValor());
		
		else
			return(-1);
	}
}

class Node
{
	int valor;
	Node next;
	
	Node(int x)
	{
		valor = x;
		
		next = null;
	}
	
	int getValor()
	{
		return(valor);
	}
	
	void setNext(Node no)
	{
		next = no;
	}
	
	Node getNext()
	{
		return(next);
	}
}

public class Grupos
{
	public static PrintWriter pw = new PrintWriter(System.out, true);
    private static Scanner sca = new Scanner(System.in);

	
	public static void main(String args[])
	{
		input();
	}
	
	
	private static void input()
	{
		int n_cenarios, cenario; //variaveis responsaveis pela iteracao do cenario
		int n_alunos, aluno; //variaveis responsaveis pela iteracao dos alunos
		Vertice[] alunos; //vector de alunos do tipo Vertice
		int id, n_amigos, amigos[], i; //variaveis relacionadas com a leitura dos campos de um aluno
		Resultados resus = new Resultados();
		
		n_cenarios = sca.nextInt();
		
		for(cenario = 1; cenario <= n_cenarios; cenario++)
		{
			n_alunos = sca.nextInt();
			
			alunos = new Vertice[n_alunos];
			
			for(aluno=0; aluno < n_alunos; aluno++)
			{
				id = sca.nextInt();
			
				n_amigos = sca.nextInt();
			
				amigos = new int [n_amigos];
			
				for(i=0; i < n_amigos; i++)
				{
					amigos[i]= sca.nextInt()-1; //para adequar a gestao da base de dados
				}
				
				alunos[id-1] = new Vertice(n_amigos, amigos);
			}
			
			TestGrupos.verAlunos(cenario, n_alunos, alunos);
			
			resus.adicionarResultado(contagem_de_grupos(cenario, n_alunos, alunos));
		}
		
		output(resus);
	}
	
	
	private static Resultado contagem_de_grupos(int cenario, int n_alunos, Vertice[] alunos)
	{
		int index, i, n_grupos4, outros, elementos_na_pilha;
		boolean analisados[] = new boolean[n_alunos];
		Stack pilha = new Stack(), p2;
		Lista_CFC lista_comp = new Lista_CFC();
		CFC j;
		Resultado resu;
		
		for(i=0; i<n_alunos; i++)
			analisados[i]=false;
		
		n_grupos4 = 0;
		outros = 0;
		
		index = 0;
			
		while((i=primeiro_Nao_Analisado(analisados, n_alunos))!=-1)
		{
			lista_comp = tarjan(n_alunos, alunos, i, index, pilha, lista_comp); //******** linha alterada **********
			
			//TestGrupos.listaLCFC(cenario, lista_comp);
			
			for(j = lista_comp.getPrimeiro(); j!= null; j=j.getNext())
			{
				p2 = j.getComponente(); 
				
				elementos_na_pilha = p2.elemsStack();
			
				if(elementos_na_pilha>=4)
					n_grupos4++;
				
				else
					outros+=elementos_na_pilha;
	
				while(p2.isEmpty() == false)
				{
					analisados[p2.pop()] = true; 
				}
			}

		}	
		
		resu = new Resultado(cenario, n_grupos4, outros);
		
		return(resu);	
	}
	
	
	static int primeiro_Nao_Analisado(boolean[] estadoPosicoes, int n)
	{
		int i;
		
		for(i=0; i<n; i++)
			if(estadoPosicoes[i]==false)
				return(i);
				
		return(-1);
	}
	
	
	private static Lista_CFC tarjan(int n_alunos, Vertice[] alunos, int v, int index, Stack pilha, Lista_CFC lista_comp)
	{
		int i, n_amigos, amigo;
		
		//pw.println("v = " + v);
		
		alunos[v].setIndex(index);
		alunos[v].setLowlink(index);
		
		index++;
		
		pilha.push(v);
		
		n_amigos = alunos[v].getNAmigos();
		
		for(i=0; i<n_amigos; i++)
		{
			amigo = alunos[v].Amigo(i);

			if(alunos[amigo].getIndex()==-1)
			{
				tarjan(n_alunos, alunos, amigo, index, pilha, lista_comp);
				
				alunos[v].setLowlink(min(alunos[v].getLowlink(), alunos[amigo].getLowlink()));
			}
			
			else
				if(pilha.elemInStack(amigo))
					alunos[v].setLowlink(min(alunos[v].getLowlink(), alunos[amigo].getIndex()));
		}
		
		if(alunos[v].getLowlink() == alunos[v].getIndex())
		{
//			if(mesma_componente(pilha, v, alunos) == false)
//			{
//				pw.println("mesma_componente(" + v + ") = " + mesma_componente(pilha, v, alunos));
			
				lista_comp.add2Lista(pilha.getTill(v));
//			}
		}
		
		return(lista_comp);
	}
	
	
	private static int min(int x, int y)
	{
		if(x <= y)
			return(x);
		
		return(y);
	}
		
/*	private static boolean mesma_componente(Stack pilha, int v, Vertice[] alunos)
	{
		Stack componente_actual = new Stack();
		Stack componente_anterior = new Stack();
		int i, j, pos;
		boolean entrada, saida;
		
		i=-1;
		
		do
		{
			i++;
			
			componente_actual.push(pilha.verPosicao(i));
		}
		while(i!=v);
		
		if(i+1<pilha.elemsStack()) //se houver outra componente
		{
			do
			{
				i++;
				
				pos = pilha.verPosicao(i);
			
				componente_anterior.push(pos);
				
				//System.out.println("pos= " + pos);
			}
			while(alunos[pos].getLowlink() != alunos[pos].getIndex());
		}
		
		if(componente_anterior.elemsStack()!=0) //se existir componente anterior
		{
			entrada = false;
			saida = false;
			
			for(i=0; entrada!=true && i<componente_anterior.elemsStack(); i++) //encontrar uma entrada da componente actual para o anterior
			{
				for(j=0; entrada!=true && j<componente_actual.elemsStack(); j++)
					if(alunos[componente_actual.verPosicao(j)].isAmigo(componente_anterior.verPosicao(i)))
						entrada=true;	
			}	
			
			for(i=0; saida!=true && i<componente_actual.elemsStack(); i++) //encontrar uma saida do componente anterior para o actual
			{
				for(j=0; saida!=true && j<componente_anterior.elemsStack(); j++)
					if(alunos[componente_anterior.verPosicao(j)].isAmigo(componente_actual.verPosicao(i)))
						saida=true;
			}
			
			return(entrada && saida);
		}
		
		return(false);
	}
*/	
	static void output(Resultados resus)
	{
		Resultado i;
		
		for(i=resus.getPrimeiro(); i!=null; i=i.proximo())
		{
			pw.println("Caso #" + i.getCaso() + "\n" + i.getGruposDe4() + " " + i.getOutrosElementos());
		}
	}
}