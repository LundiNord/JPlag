import java.io.*;
import java.util.*;

class Segmento implements Comparable<Segmento> {
    public int left,right,tamanho;

    Segmento(int l,int r){
        left = l;
        right = r;
        tamanho = r-l;
    }
    @Override
    public int compareTo(Segmento s){
        if(left < s.left) return -1;
        else if(left > s.left) return +1;
        else if(right < s.right) return -1;
        else if(right > s.right) return +1;
        return 0;
    }
}

class cobertura{
    static int np;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        np= in.nextInt();
        Segmento l[] = new Segmento[np];
        for(int i=0;i<np;i++){
            l[i] = new Segmento(in.nextInt(),in.nextInt());
        }
        //System.out.println();
        //System.out.println();
        Arrays.sort(l);
        //for(int i=0;i<np;i++)
        //    System.out.println(l[i].left + " " + l[i].right);
        //System.out.println();
        //System.out.println();
        int end=0,cs=0,best=0,jt=0;
        boolean b = false;
        for(int i=0;i<np;i++) {
            if(end >= m)
                break;
            for(int j=i;j<np;j++) {
                if(l[j].left <= end && l[j].right > end )
                    if(best<l[j].right)
                        best=l[j].right;
            }
            end=best;
            cs++;
            //System.out.println(end);
        }
        System.out.println(cs);

    }
}
