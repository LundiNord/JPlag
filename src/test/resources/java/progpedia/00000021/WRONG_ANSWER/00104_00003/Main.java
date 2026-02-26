import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;
import java.util.HashMap;

class DistanciaAnimal
{
	private int largMin;
	private int largMax;
	private int compMin;
	private int compMax;
	private int altMin;
	
	public int obterLargMin(){return largMin;}
	public int obterLargMax(){return largMax;}
	public int obterCompMin(){return compMin;}
	public int obterCompMax(){return compMax;}
	public int obterAltMin(){return altMin;}
	
	public DistanciaAnimal(int largMin,int largMax, int compMin,int compMax,int altMin)
	{
		this.largMin = largMin;
		this.largMax = largMax;
		this.compMin = compMin;
		this.compMax = compMax;
		this.altMin  = altMin;
	}	
}

class Distancia
{
	private int largura;
		public int obterLargura(){return largura;}
	
	private int comprimento;
		public int obterComprimento(){return comprimento;}
	
	private int altura;
		public int obterAltura(){return altura;}
		
	public Distancia(int largura,int comprimento,int altura)
	{
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
	}
}
class Distancias
{
	HashMap<Integer,Distancia> distancias;
	
	public Distancias()
	{
		distancias = new HashMap<Integer,Distancia>();
	}
	public void adicionar(int x, Distancia distancia)
	{
		distancias.put(x,distancia);
	}
	public Distancia obter(int x)
	{
		return distancias.get(x);
	}
	
}
class Ligacoes
{
	public HashMap<Integer,Celula> ligacoes;
	
	public Ligacoes()
	{
		ligacoes = new HashMap<Integer,Celula>();
	}
	public void adicionar(int x,Celula c)
	{
		ligacoes.put(x,c);
	}
	public Celula obter(int x)
	{
		return ligacoes.get(x);
	}
	public Integer[] obterTodas()
	{
		Collection<Integer> collectKeys = (Collection<Integer>) this.ligacoes.keySet();
		Integer[] keys = new Integer[collectKeys.size()];
		collectKeys.toArray(keys);
		return keys;
	}
}
class Celula
{
	private Ligacoes ligacoes;
		public Ligacoes obterLigacoes(){ return ligacoes ;}
	
	private Distancias distancias;
		public Distancias obterDistancias(){ return distancias;}
	
	public Celula()
	{
		this.ligacoes = new Ligacoes();
		this.distancias = new Distancias();
	}
}

class Grafo
{
	private HashMap<Integer,Celula> grafo;
	
	int melhorTempoFinal = 0;
	
	public Grafo()
	{
		this.grafo = new HashMap<Integer,Celula>();
	}
	public boolean existe(int x)
	{
		return grafo.containsKey(x);
	}
	public void criar(int x)
	{
		this.grafo.put(x,new Celula());
	}
	public void ligar(int x,int y, Distancia distancia)
	{
		if(!existe(x))
			criar(x);
		if(!existe(y))
			criar(y);
		
		Celula celulaX = this.grafo.get(x);
		Celula celulaY = this.grafo.get(y);
		
		Distancias distanciasX = celulaX.obterDistancias();
		Distancias distanciasY = celulaY.obterDistancias();
		
		distanciasX.adicionar(y, distancia);
		distanciasY.adicionar(x, distanciasX.obter(y));
		
		Ligacoes ligacoesX = celulaX.obterLigacoes();
		Ligacoes ligacoesY = celulaY.obterLigacoes();
		
		ligacoesX.adicionar(y, celulaY);
		ligacoesY.adicionar(x, celulaX);	
	}
	public Celula obter(int x)
	{
		return this.grafo.get(x);
	}
	
	public boolean ligados(int x,int y)
	{
		Celula c = grafo.get(x);
		
		if(c == null)
			return false;

		return (c.obterLigacoes().obter(y) != null);		
	}
	public Integer[] obterLigacoes(int x) 
	{
		return grafo.get(x).obterLigacoes().obterTodas();
	}
	@SuppressWarnings("unchecked")
	public void encontrarRecursivo(HashMap<Integer,Void> visitadas, int actual, int ultimo,DistanciaAnimal disAni,Integer melhorTempoActual )
	{	
		if(actual == ultimo)
		{			

			if (this.melhorTempoFinal < melhorTempoActual)
			{
				this.melhorTempoFinal = melhorTempoActual;
			}		
			
			return;
		}
		else
		{
			Celula celulaActual = grafo.get(actual);
			
			Integer[] ligacoes = celulaActual.obterLigacoes().obterTodas();
			Distancias distancias = celulaActual.obterDistancias();
			
			for(Integer ligacao : ligacoes)
			{
				if(!visitadas.containsKey(ligacao))
				{
					Distancia dis = distancias.obter(ligacao);
					
					if(
						(disAni.obterLargMin() <= dis.obterLargura()) && (dis.obterLargura() <= disAni.obterLargMax())
						&& (disAni.obterCompMin() <= dis.obterComprimento()) && (dis.obterComprimento() <= disAni.obterCompMax())
						&& (disAni.obterAltMin() <= dis.obterAltura())
					  )
					{
						HashMap<Integer,Void> visitadasClone = (HashMap<Integer,Void>) visitadas.clone();
						visitadasClone.put(ligacao, null);
						
						Integer melhorTempoActualClone = new Integer(melhorTempoActual);
						
						if(dis.obterComprimento() < melhorTempoActual)
						{
							melhorTempoActualClone = dis.obterComprimento();
						}
						
						encontrarRecursivo(visitadasClone,ligacao,ultimo,disAni,melhorTempoActualClone);
						
					}
				}
			}			
		}
	}
	public void caminhos(DistanciaAnimal disAni)
	{
		
	}
}

public class Main 
{
	
	public static void main(String[] args) 
	{
		Grafo grafo = new Grafo();
			
		InputStreamReader converter = new InputStreamReader (System.in);
		Scanner scanner = new Scanner(converter);
		
		int largMin = scanner.nextInt();
		int largMax = scanner.nextInt();
		int compMin = scanner.nextInt();
		int compMax = scanner.nextInt();
		int altMin  = scanner.nextInt();
		
		int comeco  = scanner.nextInt();
		int fim     = scanner.nextInt();		
		
		if(comeco == fim)
		{
			System.out.println(0);	
			return;
		}
			
		
		DistanciaAnimal disAni = new DistanciaAnimal(largMin, largMax, compMin, compMax, altMin);
		
		
		while(true)
		{
			int comecoRota = scanner.nextInt();			
			if(comecoRota == -1)
				break;
			
			int fimRota = scanner.nextInt();
			
			int largMaxRota = scanner.nextInt();
			int compMaxRota = scanner.nextInt();
			int altMaxRota  = scanner.nextInt();
			
			Distancia distancia = new Distancia(largMaxRota,compMaxRota,altMaxRota);
			
			grafo.ligar(comecoRota,fimRota,distancia);
		
		}
						
		HashMap<Integer,Void> visited = new HashMap<Integer,Void>();
		visited.put(comeco,null);
		
		grafo.encontrarRecursivo(visited,comeco,fim,disAni,new Integer(Integer.MAX_VALUE));
		System.out.println(grafo.melhorTempoFinal);	
	
	}

}
