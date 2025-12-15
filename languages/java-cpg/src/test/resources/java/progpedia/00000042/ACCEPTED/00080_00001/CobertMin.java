import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CobertMin {

    public static Pair[] v;
    public static int comp=0;
    
    public static void imprLista(Pair[] array) {
        for (Pair array1 : array) {
            System.out.println(array1.toString());
        }
    }

    public static void lerScanner() {
        int k = 0,pri,seg;

        Scanner scan = new Scanner(System.in);
        comp = Integer.parseInt(scan.next());
        int nVec = Integer.parseInt(scan.next());
        
        Pair par;

        v = new Pair[nVec];

        while (k < nVec) {
            //pri=Integer.parseInt(scan.next());
            //seg=Integer.parseInt(scan.next());
            par = new Pair(Integer.parseInt(scan.next()),Integer.parseInt(scan.next()));
            v[k] = par;
            k++;
        }
    }

    public static class Pair<A, B> {

        private int first;
        private int second;

        public Pair(int first, int second) {
            super();
            this.first = first;
            this.second = second;
        }

        public String toString() {
            return "(" + first + "," + second + ")";
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }

    public static class MyPairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair pair0, Pair pair1) {
            return pair0.getFirst() - pair1.getFirst();
        }
    }


    public static int minSeg() {
        int k = 0,end=0,aux=0,p,s,nSeg=0;

        p=v[k].getFirst();
		
		
        while (end<comp) {
			//System.out.println("end:"+end+" comp:"+comp);
            for(;k< v.length && v[k].getFirst()<=end;k++){
				//System.out.println(p+"<="+end);
                s=v[k].getSecond();
				//System.out.println("end:"+end+" "+v[k].toString()+" k:"+k);
                if(aux<s){
                    aux=s;
                }
                //p=v[k+1].getFirst();
            }
            nSeg++;
			//System.out.println("nSeg:"+nSeg);
            end=aux;
        }
        
        return nSeg;
    }

    public static void main(String[] args) {
        lerScanner();
        //imprLista(v);
        Arrays.sort(v, new MyPairComparator());
        //System.out.println("-----------");
        //imprLista(v);
        System.out.println(minSeg());
    }

}
