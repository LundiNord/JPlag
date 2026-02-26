import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CoberturaMinima {

	
	static class Linha implements Comparable<Linha>{
			int x, y;
			
		public Linha(int esq, int dir){
				esq=x;
				dir=y;
			}
		
		public String toString(){
			return x +" "+y ;
		} 
		
		public int compareTo(Linha arg0){
			if(x< arg0.x || (x==arg0.x && y>arg0.y)){
				return -1;
			}
			else return 1;
		}
		
	}
public static void main(String args[]){
	int tamanhoSegmento,Ncasos;
	Scanner kb = new Scanner(System.in);
	tamanhoSegmento=kb.nextInt();
	Ncasos=kb.nextInt();
	PriorityQueue<Linha> K = new PriorityQueue<Linha>();
	int s,e;
	while(Ncasos-->0){
		s=kb.nextInt();
		e=kb.nextInt();
		K.add(new Linha(s,e));
	}
	int start=0;
	LinkedList<Linha> T = new LinkedList<Linha>();
	while(start<tamanhoSegmento){
		Linha melhor = null;
		while(!K.isEmpty() && K.peek().x<=start){
			Linha cur = K.remove();
			if( melhor==null || cur.y>melhor.y){
				melhor=cur;
			}
		}
		
		if(melhor==null){
			break;}
		else{
			start=melhor.y;
			T.add(melhor);
		}
	}
	
	if(start<tamanhoSegmento){
		System.out.println("0");	
	}
	else{
		System.out.println(T.size());
		for(Linha i : T){
			System.out.println(i);
			}
		}
	}	
}
