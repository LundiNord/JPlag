import java.util.*;
import java.lang.*;

class Encomenda implements Comparable<Encomenda>{

    public int d; //duraçao
    public int m; //multa
    public int nr; //nr de encomenda
    public float ratio;
    Encomenda(int d, int m, int nr){
	this.d = d;
	this.m = m;
	this.nr = nr;
	this.ratio = (float)m/d;
    }

    @Override

    public int compareTo(Encomenda enc){

	//em caso de empate de multa escolher a de nr menor, se empatar a de nr menor abaixo
	if(Float.compare(this.ratio, enc.ratio)>0){
	    return -1;
	}else if(Float.compare(this.ratio, enc.ratio)<=0){
	    return 1;
	}
        return 0;
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

	    if(i == N-1){
		break;
	    }
	  
	    if(Float.compare(tarefas[i].ratio, tarefas[i+1].ratio) == 0){
		if(tarefas[i].nr > tarefas[i+1].nr){
		    Encomenda temp = tarefas[i];
		    tarefas[i] = new Encomenda(tarefas[i+1].d, tarefas[i+1].m, tarefas[i+1].nr);
		    tarefas[i+1] = temp;
			
		}
	    }
	    
	}


	for(int i=0; i<N; i++){	    
	    if(i == N-1){
		System.out.print(tarefas[i].nr);
	    }else{
		System.out.print(tarefas[i].nr + " ");
	    }
	}
	System.out.println();
	
    }





}
