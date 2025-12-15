import java.util.*;

class no {
    int peso;
    int id;
    LinkedList<Integer> filho;
    String cor;
    no (int t) {
	id = t;
	filho = new LinkedList<Integer>();
	peso = 1000000;
	cor = "white";
    }

    void addfilho(int x){
	filho.addLast(x);
    }

    int removefilho(){
	int x = filho.removeFirst();
	return x;
    }
    boolean isempty(){
	boolean u = filho.isEmpty();
	return u;
    }
}


class distancia{
    
    int m [][];
    int d;
    no Q[];
    int t;
    distancia (int matriz[][],int dest,int total,no R[]){
	Q = R;
	m = matriz;
	d = dest;
	t = total;
    }
    
    void inicio() {
	
	int u = -1;
	int z = 1;
	int uz = t-1;

	Q[d].peso = 0;

	while (uz > 0){
	    z = extractmin(Q);
	    if (u != z) {
		relax(Q[z],m);
		Q[z].cor = "black";
		if (uz != 1){ 
		    System.out.print(Q[z].id + " ");
		}
		else {System.out.print(Q[z].id);}
		u = z;
		uz--;
	    }
	}

	System.out.println();
    }
    
    void relax(no p,int mat[][]){
       
	while (! p.isempty()){
	    int x = p.removefilho();
	    if (Q[x].peso > Q[p.id].peso + mat[p.id][Q[x].id] || Q[x].peso == 1000000){
		Q[x].peso = Q[p.id].peso + mat[p.id][Q[x].id];
	    }   
	}
    }
    
    int extractmin(no q[]){
	
	int i=1;
	
	while(q[i].peso == 1000000 || q[i].cor.equals("black") ){
	    i++;
	    if (i==t-1){break;}
	}
	int min = q[i].peso;
	int ind = i;
	for (int b=1;b<t;b++) {

	    if (q[b].cor.equals("white")){
		if (min > q[b].peso){
		    min = q[b].peso;
		    ind = b;
		}
	    }
	}
	return ind;
    }
}

class negocio {
	
    public static void main(String args[]) {

	Scanner in = new Scanner(System.in);

	int total = in.nextInt();
	int destino = in.nextInt();
	no Q[] = new no[total+1];
	int matriz [][] = new int [total+1][total+1]; 

	while (in.hasNextInt()){
	    
	    int a = in.nextInt();
	    if (a == -1){break;}
	    int b = in.nextInt();
	    matriz[a][b] = in.nextInt();
	    matriz[b][a] = matriz[a][b];

	    if (Q[a] == null){
		no n = new no(a);
		n.addfilho(b);
		Q[a] = n;
	    }
	    else{Q[a].addfilho(b);}
	    
	    if (Q[b] == null){
		no n = new no(b);
		n.addfilho(a);
		Q[b] = n;
	    }
	    else{Q[b].addfilho(a);}
	    
	}
	
	distancia d = new distancia(matriz,destino,total+1,Q);
	d.inicio();
	
    }
}