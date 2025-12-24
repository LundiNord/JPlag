import java.util.*;

class No{
	int id;
	int peso;
	LinkedList<No> lig;
	boolean visitado;
	No(int i,int c){
		id=i;
		peso=c;
		lig=new LinkedList<No>();
		visitado=false;
	}
}

class Grafo{
	int nos;
	No grafo[];
	int ligs;
	int man;
	LinkedList<No> arvore;
	
	Grafo(int n,int l,int m){
		nos=n;
		ligs=l;
		man=m;
		grafo=new No[nos+1];
		arvore = new LinkedList<No>();
	}
	void criar(Scanner kb){
		for(int i=1;i<=nos;i++)
			grafo[i]=new No (i,0);
		
		for(int i=0;i<ligs;i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			int c=kb.nextInt();
			No no1 = new No (a,c);
			No no2 = new No (b,c);
			grafo[a].lig.addLast(no2);
			grafo[b].lig.addLast(no1);
		}
	}
	
	No extract_max(LinkedList<No> t){
		No max = new No (0,-1);
		for(No cursor : t){
			if(cursor.peso>max.peso)
				max=cursor;
		}
		t.remove(max);
		return max;
	}
	void prim(){
		LinkedList<No> fila = new LinkedList<No>();
		fila.addLast(grafo[1]);
		while(!fila.isEmpty()){
			No novo=extract_max(fila);
			for(No cursor : novo.lig){
				if(cursor.peso>grafo[cursor.id].peso && !grafo[cursor.id].visitado){
					grafo[cursor.id].peso=cursor.peso;
					if(!fila.contains(grafo[cursor.id]))
						fila.addLast(grafo[cursor.id]);
				}
			}
			grafo[novo.id].visitado=true;
			arvore.addLast(novo);
		}
	}
	
	void print(){
		if(arvore.size()<nos)
			System.out.println("impossivel");
		else{
			int custo=0;
			for(No i : arvore){
				custo+=i.peso;
			}
			System.out.println("rendimento optimo: "+(custo-(nos-1)*man));
		}
	}
}
public class OtimcaMinimalista {
	public static void main(String[] args) {
		Scanner kb=new Scanner(System.in);
		int nnos=kb.nextInt();
		int nlig=kb.nextInt();
		int custo=kb.nextInt();
		Grafo g = new Grafo (nnos,nlig,custo);
		if(nlig<(nnos-1))
			System.out.println("impossivel");
		else{
			g.criar(kb);
			g.prim();
			g.print();
		}
	
	}

}
