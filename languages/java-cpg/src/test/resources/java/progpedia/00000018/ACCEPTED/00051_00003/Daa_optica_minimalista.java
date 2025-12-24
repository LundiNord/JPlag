//package daa_optica_minimalista;
import java.util.*;


class Node {
    int valor, rend;
    LinkedList<Node> ligacoes;
    boolean visto;
    
    Node (int v, int r) {
        valor = v;
        rend = r;
        ligacoes = new LinkedList<Node> ();
        visto = false;
    }
}

class Grafo {
    int rendtotal, nnos;
    Node lista [];
    LinkedList <Node> l;
    LinkedList <Node> arvore;
    
    Grafo (int n) {
        nnos = n;
        rendtotal = 0;
        lista = new Node [n];
        l = new LinkedList <Node> ();
        arvore = new LinkedList <Node> ();
        for (int i = 0; i<n; i++) {
            lista[i]=new Node (i, 0);
        }
    }
    
    public void prim ( int custo) {
        lista[0].rend = 0;
        l.add(lista[0]);
        while (!l.isEmpty()) {
            Node max = new Node (0, -1);
            for (Node i : l) {
                if (i.rend > max.rend) {
                    max = i;
                }
            }
            l.remove(max);
            for (Node i : max.ligacoes) {                
                if (!lista[i.valor].visto && lista[i.valor].rend < i.rend) {
                    lista[i.valor].rend = i.rend;
                    if (!l.contains(lista[i.valor])) {
                        l.add(lista[i.valor]);
                    }
                }
            }
            lista[max.valor].visto = true;
            arvore.add(new Node (max.valor, max.rend));
            
        }
        
        if (arvore.size() < nnos) {
            System.out.println("impossivel");
        }
        else {
            for (Node i : arvore) {
                rendtotal += i.rend;
            }
            System.out.println ("rendimento optimo: " + (rendtotal - (custo * (nnos-1))));
        }
    }
    
    
}

public class Daa_optica_minimalista {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        int nnos = in.nextInt();
        int nlig = in.nextInt();
        int custo = in.nextInt();
        
        Grafo g = new Grafo (nnos);
        for (int i = 0; i<nlig; i++) {
            int pri = in.nextInt();
            int seg = in.nextInt();
            int r = in.nextInt();
            
            g.lista[pri-1].ligacoes.add(new Node(seg-1, r));
            g.lista[seg-1].ligacoes.add(new Node(pri-1, r));
        }
        
        g.prim(custo);
    }
}
