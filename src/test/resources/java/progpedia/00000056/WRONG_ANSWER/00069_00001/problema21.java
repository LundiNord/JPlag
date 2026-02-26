import java.util.*;
import java.io.*;

class problema21 {
    public static float cont = 0;
    public static PriorityQueue<Aresta> heap = new PriorityQueue<Aresta>();
    public static No no[] = new No[100];
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt() ;
	
	for(int i = 0; i < n; i++) {
	    no[i] = new No(in.nextDouble(), in.nextDouble());
	}
	
	prim(no[0], n);
	System.out.printf("%.2f", cont);
	System.out.println();
    }
    
    public static void prim(No x, int numnos) {
	x.visitado = true;
	
	for(int i = 0; i < numnos; i++) {
	    if(!no[i].visitado) {
		Aresta temp = new Aresta(x, no[i]);
		
		heap.add(temp);
	    }
	}

	    while(heap.size() != 0 && numnos > 0) {
		Aresta temp = new Aresta();
		temp = heap.peek();
		if(temp.b.visitado) {
		    Aresta pl =  heap.poll();
		    System.out.println("Entrei aqui");
		} 
		else{
		    cont += temp.distancia;
		    Aresta pl = heap.poll();
		    numnos--;
		    prim(temp.b, numnos);
		}
		
	    }
	    
	
    }
}
class No {
    double x, y;
    boolean visitado;
    
    No(double x, double y) {
	this.x = x;
	this.y = y;
	visitado = false;
    }   
}

class Aresta implements Comparable<Aresta>{
    No a, b;
    double distancia;
    
    Aresta() {
    }
    
    Aresta(No x, No y) {
	a = x;
	b = y;
	double dis1 = (a.x - b.x);
	double dis2 = (a.y - b.y);
	dis1 = dis1 * dis1;
	dis2 = dis2 * dis2;
	distancia = Math.sqrt(dis1+dis2);
    }

    @Override
    public int compareTo(Aresta x) {
	return Double.compare(this.distancia, x.distancia);
    }


}
