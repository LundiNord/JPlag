import java.util.*;
public class prob21{
	static int [] pset;
    static class ramo implements Comparable<ramo>{
        int u;
        int v;
        double w;
        public ramo(int st, int end, double we){
           	u = st;
            v = end;
            w = we;
        }
        @Override
        public int compareTo(ramo o) {
            if(w > o.w)
                return -1;
            else
                return 1;
        }
    }
   
    static int findSet(int i){
    	if ( pset[i] == i ) {
    		return i ;
    	}
    	else {
    		return pset[i] = findSet(pset[i]);
    	}
    }

    static void unionSet(int i, int j){
        pset[findSet(i)] = pset[findSet(j)];
    }

    static boolean isSameSet(int i, int j){
        return findSet(i) == findSet(j); 
    }

    public static void initSet(int tamanho) { 
        pset = new int[tamanho]; 
        for (int i = 0; i < tamanho; i++){ 
        	pset[i] = i; 
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        PriorityQueue<ramo> ramoList = new PriorityQueue<ramo>();
        int n = ler.nextInt();
        double [][] cord= new double[n][2];;
        double currLen; 
        double yDiff,xDiff;
        double min = 0.0;

        for (int i = 0; i < n; i++) {
            min = 0;
            initSet(n);
            cord[i][0] = ler.nextDouble();
            cord[i][1] = ler.nextDouble();
            for (int j = 0; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    xDiff = cord[j][0] - cord[k][0];
                    yDiff = cord[j][1] - cord[k][1];
                    currLen = Math.sqrt(yDiff*yDiff + xDiff*xDiff);
                    ramo e = new ramo(j, k, - currLen);
                    ramoList.add(e);
                }
            }
            while(!ramoList.isEmpty()){
                ramo e = ramoList.poll();
                if(!isSameSet(e.u, e.v)){
                    min += -e.w;
                    unionSet(e.u, e.v);
                }
            }
            //System.out.printf("%.2f\n", min);
        }
         System.out.printf("%.2f\n", min);
    }
}