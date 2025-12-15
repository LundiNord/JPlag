import java.io.*;
import java.util.*;
import java.util.*;
public class Prob9 {
	//com ajuda de https://github.com/ksaveljev/UVa-online-judge/blob/master/10020.cpp
	//feito em conjunto com Catarina Ribeiro e Joel Sousa
	static int m; //numero indicando o tamanho do segmento
	static int n; // indicando a quantidade de segmentos a considerar
	static int li;
	static int ri;
	static int begin;
	static class Activ implements Comparable<Activ>{
		int end;
		int begin;
		
		public Activ(int in, int e){
			begin = in;
			end = e;
		}
		
		@Override
		public int compareTo(Activ a) {
			if(begin < a.begin || (begin == a.begin && end > a.end))
				return -1;
			return 1;
		}
		
	}

	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		m = stdin.nextInt();
		n = stdin.nextInt();
		
		LinkedList<Activ> Lista = new LinkedList<Activ>();
		PriorityQueue<Activ> Actividade = new PriorityQueue<Activ>();
		
		for(int i = 0; i < n; i++){
			li = stdin.nextInt();
			ri = stdin.nextInt();
			Actividade.add(new Activ(li,ri));//adicionar os elementos a lista 
		}
		
		int begin = 0;
		while(begin < m){
			Activ melhor = null;
			while(!Actividade.isEmpty() && Actividade.peek().begin <= begin){
				Activ atual = Actividade.remove();
				if(melhor == null || atual.end > melhor.end)
					melhor = atual;
			}
			if(melhor != null){
				begin = melhor.end;
				Lista.add(melhor);
			}
		}
		if(begin < m)
			System.out.println(0);
		else
			System.out.println(Lista.size());

	}
}
