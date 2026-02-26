import java.util.*;

class Encomenda implements Comparable<Encomenda>{

    public int d; //duraçao
    public int m; //multa
    public int nr; //nr de encomenda
    Encomenda(int d, int m, int nr){
	this.d = d;
	this.m = m;
	this.nr = nr;
    }

    @Override

    public int compareTo(Encomenda enc){

	//em caso de empate de multa escolher a de nr menor, se empatar a de nr menor abaixo
	if(this.d > enc.d){
	    return 1;
	}else if(this.d < enc.d){
	    if(this.m > enc.m){
		return -1;
	    }
	    else{
		return 1;
	    }	    
	}
	else{
	     if(this.m > enc.m){
		return -1;
	    }
	    else{
		return 1;
	    }
	    
	}
	

    }

}

class Sapateiro{


    public static void main(String args[]){
	
	Scanner input = new Scanner(System.in);
	int N = input.nextInt();
	Encomenda[] tarefas = new Encomenda[N];
	for(int i=0; i<N; i++){
	    tarefas[i] = new Encomenda(input.nextInt(), input.nextInt(), i+1);
	}

	Arrays.sort(tarefas);
	for(int i=0; i<N; i++){
	    if(i != N-1){
		System.out.print(tarefas[i].nr + " ");
	    }else{
		System.out.print(tarefas[i].nr);
	    }
	}

	
	
    }





}
