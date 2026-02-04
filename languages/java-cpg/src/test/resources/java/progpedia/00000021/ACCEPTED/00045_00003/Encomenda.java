import java.util.Scanner;

class NoPrincipal {
    int ponto;
    NoPrincipal prox;
    NoPrincipal adj;
    NoPrincipal primeiro;
    int tamColunas = 0;
    int area;

    NoPrincipal(int ponto) {
        this.ponto = ponto;
        prox = null;
        adj = null;
        primeiro = null;
        area = 0;
    }

    void inserir(int ponto, int area) {
        //Para o primeiro valor da lista
        if (primeiro == null) {
            NoPrincipal novo = new NoPrincipal(ponto);
            novo.setArea(area);
            adj = novo;
            primeiro = adj;
        }
        //Restantes valores
        else {
            NoPrincipal novo = new NoPrincipal(ponto);
            novo.setArea(area);
            adj.setPrx(novo);
            adj = novo;
        }
        tamColunas++;
    }

    //DeadCodeStart
    void mostrarLista() {
        int i = 1;
        NoPrincipal noTemp = primeiro;

        while (noTemp != null) {
            System.out.println(" -> " + noTemp.getPonto());
            noTemp = noTemp.getPrx();
            i = i + 1;
        }
    }
    //DeadCodeEnd

    int[] getAdj() {
        NoPrincipal noTemp = primeiro;
        int n[];
        int i = 0;

        n = new int[tamColunas];
        for (int j = 0; j < tamColunas; n[j] = 0, j++) ;

        while (noTemp != null) {
            n[i] = noTemp.getPonto();
            noTemp = noTemp.getPrx();
            i++;
        }
        return n;
    }

    int getArea(int ponto) {
        NoPrincipal noTemp = primeiro;

        //System.out.println("ponto neste ponto -> " + ponto + " ");
        while (noTemp != null) {
            //System.out.println("  > " + noTemp.ponto);
            if (noTemp.ponto == ponto) {
                return noTemp.getArea();
            }
            noTemp = noTemp.getPrx();
        }
        return 0;
    }

    int getPonto() {
        return ponto;
    }

    NoPrincipal getPrx() {
        return prox;
    }

    void setPrx(NoPrincipal prox) {
        this.prox = prox;
    }

    int getArea() {
        return area;
    }

    void setArea(int area) {
        this.area = area;
    }

    //DeadCodeStart
    int getCol() {
        return tamColunas;
    }
    //DeadCodeEnd

}

class Lista {
    int tam;
    private NoPrincipal primeiro;
    private NoPrincipal lista;

    //Construtor da lista sem parametros
    Lista() {
        primeiro = null;
        tam = 0;
    }

    //Inserir um valor na lista
    void inserir(int pontoP, int pontoC, int area) {
        NoPrincipal novo = new NoPrincipal(pontoP);

        if ((novo = procurarPonto(pontoP)) == null) {
            novo = new NoPrincipal(pontoP);
            //Para o primeiro valor da lista
            if (primeiro == null) {
                lista = novo;
                primeiro = lista;
            }
            //Restantes valores
            else {
                lista.setPrx(novo);
                lista = novo;
            }
            tam++;
        }
        novo.inserir(pontoC, area);

        novo = new NoPrincipal(pontoC);
        if ((novo = procurarPonto(pontoC)) == null) {
            novo = new NoPrincipal(pontoC);
            //Para o primeiro valor da lista
            if (primeiro == null) {
                lista = novo;
                primeiro = lista;
            }
            //Restantes valores
            else {
                lista.setPrx(novo);
                lista = novo;
            }
            tam++;
        }
        novo.inserir(pontoP, area);
        tam++;
    }

    NoPrincipal procurarPonto(int pontoP) {

        NoPrincipal noTemp = primeiro;

        while (noTemp != null) {
            if (noTemp.getPonto() == pontoP) {
                return noTemp;
            }
            noTemp = noTemp.getPrx();
        }
        return null;
    }

    int[] adjacentes(int ponto) {
        NoPrincipal noTemp = primeiro;
        int n[];

        n = new int[tam];

        while (noTemp != null) {
            if (noTemp.ponto == ponto)
                n = noTemp.getAdj();
            noTemp = noTemp.getPrx();
        }
        	/*for(int j = 0; j < n.length; j++)
        		System.out.print(n[j] + " ");
        	System.out.println();*/

        return n;
    }

    int getArea(int prox, int pontoV) {
        NoPrincipal noTemp = primeiro;
        int val;

        while (noTemp != null) {
            if (noTemp.getPonto() == prox) {
                if ((val = noTemp.getArea(pontoV)) != 0)
                    return val;
            }
            noTemp = noTemp.getPrx();
        }
        return 0;
    }

    //DeadCodeStart
    public void mostrarLista() {
        int i = 1;
        NoPrincipal noTemp = primeiro;

        while (noTemp != null) {
            System.out.println(noTemp.getPonto());
            noTemp.mostrarLista();
            noTemp = noTemp.getPrx();
            i = i + 1;
        }
    }
    //DeadCodeEnd

}

class Vectores {
    int posicao;
    int ponto;
    int dist;
    int pred;
    boolean estado;
    Vectores prox;

    Vectores(int ponto, int posicao) {
        this.posicao = posicao;
        this.ponto = ponto;
        dist = 0;
        pred = 0;
        estado = false;
        prox = null;
    }

    int getPonto() {
        return ponto;
    }

    Vectores getPrx() {
        return prox;
    }

    void setPrx(Vectores prox) {
        this.prox = prox;
    }

    int getPred() {
        return pred;
    }

    void setPred(int pred) {
        this.pred = pred;
    }

    boolean getEst() {
        return estado;
    }

    void setEst(boolean estado) {
        this.estado = estado;
    }

    int getDist() {
        return dist;
    }

    void setDist(int dist) {
        this.dist = dist;
    }

    int getPos() {
        return posicao;
    }

}

class MelhorCam {
    int largMin, largMax;
    int compMin, compMax;
    int altMin;
    int origem, destino;
    Lista grafo;
    Dijkstra resolve;

    MelhorCam(int largMin, int largMax, int compMin, int compMax, int altMin) {
        resolve = new Dijkstra();
        this.largMin = largMin;
        this.largMax = largMax;
        this.compMin = compMin;
        this.compMax = compMax;
        this.altMin = altMin;
        origem = 0;
        grafo = new Lista();
    }

    void origemDestino(int origem, int destino) {
        this.origem = origem;
        this.destino = destino;
    }

    void verificaValores(int pontoP, int pontoC, int larg, int comp, int alt) {
        if (larg >= largMin)
            if (comp >= compMin)
                if (alt >= altMin)
                    constroiGrafo(pontoP, pontoC, comp);
    }

    void constroiGrafo(int pontoP, int pontoC, int comp) {
        resolve.inserirVect(pontoP);
        resolve.inserirVect(pontoC);
        grafo.inserir(pontoP, pontoC, comp);
    }

    void mostrarValores() {
        //grafo.mostrarLista();
        resolve.dijk(grafo, origem, compMax);
        System.out.println(resolve.buscarDist(destino));
    }
}

class Dijkstra {

    Vectores dPV;
    Vectores primeirodPV;
    int tam;


    Dijkstra() {
        primeirodPV = dPV = null;
        tam = 0;
    }

    void dijk(Lista grafo, int origem, int compMax) {
        mudar(origem, false, compMax, "dist");
        int prox;
        int d = 0;

        for (int i = 0; i < tam; i++) {
            prox = vertMax();
            mudar(prox, true, 0, "vist");

            int n[] = grafo.adjacentes(prox);

            for (int j = 0; j < n.length; j++) {
                int v = n[j];
                if (buscarPred(prox) != v) {
                    d = grafo.getArea(prox, v);
                    //System.out.println("distancia -> " + d);
                    if (buscarDist(v) < d) {
                        mudar(v, false, max(buscarDist(prox), d), "dist");
                        mudar(v, false, prox, "pred");
                    }
                }
            }
            //System.out.println(" -------- fim ---------");
        }
    }

    int vertMax() {
        int x = 0;
        int y = -1; //grafo nao conectado ou nao visitado
        for (int i = 1; i <= tam; i++) {
            if ((buscarVistPos(i) != true) && (buscarDistPos(i) > x)) {
                y = buscarNumPos(i);
                x = buscarDistPos(i);
                //System.out.println(i + " y > " + buscarNumPos(i) + " x > " + x + " dist > " + buscarDistPos(i));
            }
        }
        return y;
    }

    int max(int x, int y) {
        if (x > y)
            return x - (x - y);
        else if (x < y)
            return y - (y - x);
        return x;
    }

    void inserirVect(int ponto) {
        Vectores novo;
        int flag = 0;

        if (primeirodPV == null) {
            novo = new Vectores(ponto, ++tam);
            dPV = novo;
            primeirodPV = dPV;
        } else {
            Vectores temp = primeirodPV;
            while (temp != null) {
                if (temp.getPonto() == ponto) {
                    //DeadCodeStart
                    flag = 1;
                    //DeadCodeEnd
                    return;
                }
                temp = temp.getPrx();
            }
            //DeadCodeStart
            if (flag != 1) {
                //DeadCodeEnd
                novo = new Vectores(ponto, ++tam);
                dPV.setPrx(novo);
                dPV = novo;
                //DeadCodeStart
            }
            //DeadCodeEnd
        }
    }

    void mudar(int ponto, boolean vis, int valor, String s) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPonto() == ponto) {
                if (s == "dist")
                    temp.setDist(valor);
                else if (s == "pred")
                    temp.setPred(valor);
                else if (s == "vist")
                    temp.setEst(vis);
            }
            temp = temp.getPrx();
        }
    }

    int buscarDist(int ponto) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPonto() == ponto)
                return temp.getDist();
            temp = temp.getPrx();
        }
        return 0;
    }

    int buscarPred(int ponto) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPonto() == ponto)
                return temp.getPred();
            temp = temp.getPrx();
        }
        return 0;
    }

    //DeadCodeStart
    boolean buscarVist(int ponto) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPonto() == ponto)
                return temp.getEst();
            temp = temp.getPrx();
        }
        return false;
    }
    //DeadCodeEnd

    int buscarDistPos(int pos) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPos() == pos)
                return temp.getDist();
            temp = temp.getPrx();
        }
        return 0;
    }

    boolean buscarVistPos(int pos) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPos() == pos)
                return temp.getEst();
            temp = temp.getPrx();
        }
        return false;
    }

    int buscarNumPos(int pos) {
        Vectores temp = primeirodPV;
        while (temp != null) {
            if (temp.getPos() == pos)
                return temp.getPonto();
            temp = temp.getPrx();
        }
        return 0;
    }

    //DeadCodeStart
    public void mostrarLista() {
        Vectores noTemp = primeirodPV;

        while (noTemp != null) {
            System.out.print(noTemp.getPonto() + " ");
            noTemp = noTemp.getPrx();
        }
        System.out.println();
    }
    //DeadCodeEnd

}

class Encomenda {
    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);

        int val;

        MelhorCam jaula = new MelhorCam(stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt());

        jaula.origemDestino(stdin.nextInt(), stdin.nextInt());
        while ((val = stdin.nextInt()) != -1) {
            jaula.verificaValores(val, stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt());
        }
        jaula.mostrarValores();
    }
}
