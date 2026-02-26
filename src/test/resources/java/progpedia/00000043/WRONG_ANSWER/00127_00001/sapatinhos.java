import java.util.*;
class sapatinhos {
	public static void main (String[] args){
		Scanner input = new Scanner (System.in);
		int N = input.nextInt();
		sapato[] sapatos = new sapato[N];
		for (int i=0; i<N; i++)
			sapatos[i] = new sapato(input.nextInt(),input.nextInt());
		int multa_total = 0;	//Sim, já sinto q vou fazer batota. n<=1000? Deve passar quadrático...
		for (int i=0; i<N; i++)
			multa_total += sapatos[i].multa;
		for (int i=0; i<N; i++){
			for (int j=0; j<N; j++){
				if (!(sapatos[j].feito))
					sapatos[j].cost = sapatos[j].tempo * (multa_total - sapatos[j].multa);
			}	//Array.sort baseado no cost? <- pensar.
			int Min=-1;
			int jMin=-1;
			for (int j=0; j<N; j++){
				if (!(sapatos[j].feito)){
					if (Min==-1){
						Min = sapatos[j].cost;
						jMin = j;
					}
					else{
						if (Min>sapatos[j].cost){ // E empates?
							Min = sapatos[j].cost;
							jMin = j;
						}
					}
				}
			}
			if (i<N-1)
				System.out.print(jMin+" ");
			else
				System.out.print(jMin);
			sapatos[jMin].feito = true;
			multa_total -= sapatos[jMin].multa;
		}
		System.out.println();
	}
}

class sapato{
	int tempo,multa;
	boolean feito;
	int cost;
	sapato(int tempo, int multa){
		this.tempo=tempo;
		this.multa=multa;
		this.feito=false;
	}
}