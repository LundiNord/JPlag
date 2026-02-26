import java.util.*;

public class prob9{
    
    static int n;
    static int m;
    static Seg segmento[];
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        segmento = new Seg[n]; 
        for(int i = 0; i<n; i++) segmento[i] = new Seg(in.nextInt(), in.nextInt());
        Arrays.sort(segmento);
        System.out.println(encontrar());
    }
    
    public static int encontrar(){
        int end = 0;
        int x = 0;
        while(end < m){
            int aux = 0;
            for(int i = 0; i<n; i++){
                if(segmento[i].esq <= end)
                    if(segmento[i].dir >= aux) aux = segmento[i].dir;
            }
            end = aux;
            x++;
        }
        return x;
    }
    
}

class Seg implements Comparable<Seg>{
    int esq;
    int dir;
    
    Seg(int e, int d){
        esq = e;
        dir = d;
    }
    
    @Override
    public int compareTo(Seg s){
        if(esq >= s.esq) return 1;
        else return -1;
    }
    
}