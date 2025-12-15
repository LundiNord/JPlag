/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package daa_treino;


import java.util.*;
import java.lang.*;

class Arco {
    int no_final;
    int comp;
    int largura;
    int altura;
    
    Arco(int fim, int c, int l, int a){
	no_final = fim;
	comp = c;
        largura = l;
        altura = a;
    }

    int extremo_final() {
	return no_final;
    }

    int valor_comp() {
	return comp;
    }
    
    int valor_altura() {
	return altura;
    }
    
    int valor_largura() {
	return largura;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo {
    No verts[];
    int nvs, narcos;
			
    public Grafo(int n) {
	nvs = n;
	narcos = 0;
	verts  = new No[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new No();
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    
    public int num_vertices(){
	return nvs;
    }

    public int num_arcos(){
	return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
	return verts[i].adjs;
    }
    
    public void insert_new_arc(int i, int j, int l, int c, int a){
	verts[i].adjs.addFirst(new Arco(j,l,c,a));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}

public class Infraestrutura {
    public static void main (String[] args) {
        Scanner in = new Scanner (System.in);
        int nv = in.nextInt();
        Grafo g = new Grafo (nv);
        int larMin = in.nextInt();
        int larMax = in.nextInt();
        int comMin = in.nextInt();
        int comMax = in.nextInt();
        int altMin = in.nextInt();
        int origem = in.nextInt();
        int destino = in.nextInt();
        int x = 1;
        int count_nos = 0;
        while (x==1) {
            int a1 = in.nextInt();
            if (a1 == -1) {
                x=0;
            }
            
            else {
                int a2 = in.nextInt();
                int larg = in.nextInt();
                int comp = in.nextInt();
                int alt = in.nextInt();
                if (larg >= larMin) {
                    if (comp >= comMin) {
                        if (alt >= altMin) {
                            count_nos++;
                            //g.insert_new_arc(a1, a2, larg, comp, alt);
                            //g.insert_new_arc(a2, a1, larg, comp, alt);
                        }
                    }
                }
            }
        }
       
        System.out.println(count_nos);
    }
}
