import java.util.LinkedList;

class No {
    int no;
    int tempof;
    LinkedList<No> adj;
    boolean visitado;

    No(int n) {
        no = n;
        tempof = 0;
        adj = new LinkedList<No>();
        visitado = false;
    }

    void addLigacao(No x) {
        adj.addLast(x);
    }

}
