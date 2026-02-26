/**
 *
 * @author Eduardo
 */
import java.util.Scanner;

public class negocioelectronico {
    static int size;
    static int distancias[];
    public static void main(String Args[]) {
        Scanner in = new Scanner(System.in);
        int i;
        size = in.nextInt();
        int inicial = in.nextInt()-1;
        int pai, filho, peso;
        int[][] pesos = new int[size][size];
        for (i = 0; i < size; i++) {
            pesos[i][i] = 0;
        }
        pai = in.nextInt();
        while (pai != -1) {
            filho = in.nextInt();
            peso = in.nextInt();
            pesos[pai-1][filho-1] = peso;
            pesos[filho-1][pai-1] = peso;
            pai = in.nextInt();
        }
        distancias = dijkstra(pesos, inicial);
        for(i=0; i<size; i++){
            int n=minimo();
            distancias[n]=Integer.MAX_VALUE;
            System.out.print(n+1+" ");
        }
        System.out.println();

    }
    static boolean visitados[];
    static int[] dijkstra(int[][] pesos, int origem) {
//        pais = new int[size];
        visitados=new boolean[size];
        distancias = new int[size];
        for (int i = 0; i < size; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }
        distancias[origem]=0;
        for(int i=0; i<size; i++){
            int n=minimo_nao_visitado();
            visitados[n]=true;
            for(int pi=0; pi<size; pi++){
                if(pesos[n][pi]!=0)
                    relax(n, pi, pesos);
            }
        }
        return distancias;
    }

    private static void relax(int n, int i, int[][] pesos) {
        int peso_alternativo=distancias[n]+pesos[n][i];
        if(peso_alternativo<distancias[i]){
            distancias[i]=peso_alternativo;
        }
        return;
    }
    private static int minimo_nao_visitado() {
        int minimum=0;
        for (int i = 1; i < size; i++) {
            if (distancias[i] < distancias[minimum]) {
                if(!visitados[i])
                    minimum = i;
            }
        }
        return minimum;
    }

    private static int minimo() {
        int minimum=0;
        for (int i = 1; i < size; i++) {
            if (distancias[i] < distancias[minimum]) {
                minimum = i;
            }
        }
        return minimum;
    }
}
