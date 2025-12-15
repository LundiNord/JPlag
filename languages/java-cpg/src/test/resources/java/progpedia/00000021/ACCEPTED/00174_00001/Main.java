	
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
  
class Arco {
    int no_final;
    int lMax;
    int cMax;
    int altura;
      
    Arco(int fim, int l, int c, int a){
        no_final = fim;
        lMax = l;
        cMax = c;
        altura = a;
    }
  
    int extremo_final() {
        return no_final;
    }
  
    int lMax_arco() {
        return lMax;
    }
      
    int cMax_arco() {
        return cMax;
    }
      
    int altura_arco() {
        return altura;
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
      
    public void insert_new_arc(int i, int j, int lMax_ij, int cMax_ij, int altura_ij){
        if(verts.length < i + 1){
            int lastSize = verts.length;
            verts = Arrays.copyOf(verts, i + 1);
            nvs = i;
            for(int novo = lastSize; novo < nvs + 1; novo++)
                verts[novo] = new No();
        }
        verts[i].adjs.addFirst(new Arco(j, lMax_ij, cMax_ij, altura_ij));
            narcos++;
    }
  
    public Arco find_arc(int i, int j){
        for (Arco adj: adjs_no(i))
            if (adj.extremo_final() == j) return adj;
        return null;
    }
      
    public int Dijkstra(int s, int dest){
        int dist[] = new int[nvs + 1];
        for(int i = 1; i <= nvs; i++) dist[i] = 0;
          
        dist[s] = Integer.MAX_VALUE;
          
        Heapmax heap = new Heapmax(dist, nvs);
          
        while(heap.size != 0){
            int v = heap.extractMax();
            for (Arco w: adjs_no(v)) {
                int cCurrente;
                if(w.cMax < dist[v]) cCurrente = w.cMax;
                else{
                    cCurrente = dist[v];
                }
                if(cCurrente > dist[w.no_final]){
                    dist[w.no_final] = cCurrente;
                    heap.increaseKey(w.no_final, dist[w.no_final]);
                }
            }
        }
          
        return dist[dest];
    }
}
  
class Qnode {
    int vert;
    int vertkey;
      
    Qnode(int v, int key) {
        vert = v;
        vertkey = key;
    }
}
  
class Heapmax {
    private static int posinvalida = 0;
    int sizeMax,size;
      
    Qnode[] a;
    int[] pos_a;
  
    Heapmax(int vec[], int n) {
        a = new Qnode[n + 1];
        pos_a = new int[n + 1];
        sizeMax = n;
        size = n;
        for (int i = 1; i <= n; i++) {
            a[i] = new Qnode(i,vec[i]);
            pos_a[i] = i;
        }
      
        for (int i = n/2; i >= 1; i--)
            heapify(i);
    }
  
    int extractMax() {
        int vertv = a[1].vert;
        swap(1,size);
        pos_a[vertv] = posinvalida;  // assinala vertv como removido
        size--;
        heapify(1);
        return vertv;
    }
  
    void increaseKey(int vertv, int newkey) {
  
        int i = pos_a[vertv];
        a[i].vertkey = newkey;
      
        while (i > 1 && compare(i, parent(i)) < 0) { 
            swap(i, parent(i));
            i = parent(i);
        }
    }
  
  
    void insert(int vertv, int key)
    { 
        if (sizeMax == size)
            new Error("Heap is full\n");
          
        size++;
        a[size].vert = vertv;
        pos_a[vertv] = size;   // supondo 1 <= vertv <= n
        increaseKey(vertv,key);   // aumenta a chave e corrige posicao se necessario
    }
  
    void write_heap(){
        System.out.printf("Max size: %d\n",sizeMax);
        System.out.printf("Current size: %d\n",size);
        System.out.printf("(Vert,Key)\n---------\n");
        for(int i=1; i <= size; i++)
            System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);
          
        System.out.printf("-------\n(Vert,PosVert)\n---------\n");
      
        for(int i=1; i <= sizeMax; i++)
            if (pos_valida(pos_a[i]))
            System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }
      
    private int parent(int i){
        return i/2;
    }
    private int left(int i){
        return 2*i;
    }
    private int right(int i){
        return 2*i+1;
    }
  
    private int compare(int i, int j) {
        if (a[i].vertkey > a[j].vertkey)
            return -1;
        if (a[i].vertkey == a[j].vertkey)
            return 0;
        return 1;
    }
  
    
    private void heapify(int i) {
        int l, r, biggest;
      
        l = left(i);
        if (l > size) l = i;
      
        r = right(i);
        if (r > size) r = i;
      
        biggest = i;
        if (compare(l,biggest) < 0)
            biggest = l;
        if (compare(r,biggest) < 0)
            biggest = r;
          
        if (i != biggest) {
            swap(i, biggest);
            heapify(biggest);
        }
      
    }
  
    private void swap(int i, int j) {
        Qnode aux;
        pos_a[a[i].vert] = j;
        pos_a[a[j].vert] = i;
        aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }
      
    private boolean pos_valida(int i) {
        return (i >= 1 && i <= size);
    }
}
  
  
public class Main {
  
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
                  
        int lMin = scan.nextInt();
        int lMax = scan.nextInt();
        int cMin = scan.nextInt();
        int cMax = scan.nextInt();
        int aMin = scan.nextInt();
          
        int origem = scan.nextInt();
        int destino = scan.nextInt();
          
        Grafo locaisGrafo = new Grafo(2);
        int contagemOrigem = 0;
        int contagemDestino = 0;
          
        int local1 = scan.nextInt();
        while(local1 != -1){
            int local2 = scan.nextInt();
            int lMaxArco = scan.nextInt();
            int cMaxArco = scan.nextInt();
            int alturaArco = scan.nextInt();
              
            if(lMaxArco >= lMin && cMaxArco >= cMin && alturaArco >= aMin){
                locaisGrafo.insert_new_arc(local1, local2, lMaxArco, cMaxArco, alturaArco);
                locaisGrafo.insert_new_arc(local2, local1, lMaxArco, cMaxArco, alturaArco);
                if(origem == local1 || origem == local2) contagemOrigem++;
                if(destino == local1 || destino == local2) contagemDestino++;
            }
              
            local1 = scan.nextInt();
        }
        if(contagemOrigem == 0 || contagemDestino == 0) System.out.println("0");
        else{
            int compMax = locaisGrafo.Dijkstra(origem, destino);
            if(compMax > cMax)System.out.println(cMax);
            else{
                System.out.println(compMax);
            }
        }
    }
  
}
