import java.util.LinkedList;
import java.util.Scanner;

class Arco {

    int no_final;

    Arco(int fim) {
        no_final = fim;
    }

    int extremo_final() {
        return no_final;
    }
}

class Node {

    LinkedList<Arco> adjs;

    Node() {
        adjs = new LinkedList<Arco>();
    }
}

class Grafo {

    Node verts[];
    int nvs, narcos;

    public Grafo(int n) {
        nvs = n;
        narcos = 0;
        verts = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            verts[i] = new Node();
        }
    }

    public int num_vertices() {
        return nvs;
    }

    public int num_arcos() {
        return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
        return verts[i].adjs;
    }

    public void insert_new_arc(int i, int j) {
        verts[i].adjs.addFirst(new Arco(j));
        narcos++;
    }

    public Arco find_arc(int i, int j) {
        for (Arco adj : adjs_no(i)) {
            if (adj.extremo_final() == j) {
                return adj;
            }
        }
        return null;
    }
}

public class Biologica {

    static Grafo lergrafo(Scanner in) {
        Grafo g;
        int nNos = in.nextInt();
        int nRamos = in.nextInt();
        int noInicio, noFim;
        g = new Grafo(nNos);
        for (int i = 0; i < nRamos; i++) {
            noInicio = in.nextInt();
            noFim = in.nextInt();
            g.insert_new_arc(noInicio, noFim);
            g.insert_new_arc(noFim, noInicio);
        }
        return g;
    }

    static int[] distmin(Grafo g, int noOrigem) {
        int d[] = new int[g.nvs + 1];
        boolean visitado[] = new boolean[g.nvs + 1];
        for (int u = 0; u < g.nvs + 1; u++) {
            visitado[u] = false;
        }
        visitado[noOrigem] = true;
        d[noOrigem] = 0;
        LinkedList<Integer> t = new LinkedList<Integer>();
        t.add(new Integer(noOrigem));
        while (!t.isEmpty()) {
            Integer aux = (Integer) t.removeFirst();
            int u = aux.intValue();
            for (Arco a : g.adjs_no(u)) {
                int v = a.extremo_final();
                if (!visitado[v]) {
                    visitado[v] = true;
                    d[v] = d[u] + 1;
                    t.addLast(new Integer(v));
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Grafo g = lergrafo(in);
        int v[] = new int[g.nvs + 1];
        int matr[][] = new int[g.nvs + 1][g.nvs + 1];
        for (int i = 1; i <= g.nvs; i++) {
            matr[i][i] = 0;
            v = distmin(g, i);
            for (int j = 1; j <= g.nvs; j++) {
                matr[i][j] = v[j];
            }
        }

        int exc[] = new int[g.nvs + 1];
        for (int i = 1; i <= g.nvs; i++) {
            for (int j = 1; j <= g.nvs; j++) {
                if (matr[i][j] > exc[i]) {
                    exc[i] = matr[i][j];
                }
            }
        }
        int diam = exc[1];
        for (int k = 2; k <= g.nvs; k++) {
            if (exc[k] > diam) {
                diam = exc[k];
            }
        }
        int raio = exc[1];
        for (int k = 2; k <= g.nvs; k++) {
            if (exc[k] < raio) {
                raio = exc[k];
            }
        }
        System.out.println(diam);
        System.out.println(raio);
        int count = 0;
        int countaux = 0;
        for (int k = 1; k <= g.nvs; k++) {
            if (exc[k] == raio) {
                count++;
            }
        }
        for (int k = 1; k <= g.nvs; k++) {
            if (exc[k] == raio) {
                countaux++;
                if (countaux == count) {
                    System.out.println(k);
                } else {
                    System.out.print(k + " ");
                }
            }
        }
        count = 0;
        countaux = 0;
        for (int k = 1; k <= g.nvs; k++) {
            if (exc[k] >= diam) {
                count++;
            }
        }
        for (int k = 1; k <= g.nvs; k++) {
            if (exc[k] >= diam) {
                countaux++;
                if (countaux == count) {
                    System.out.println(k);
                } else {
                    System.out.print(k + " ");
                }
            }
        }
    }
}
