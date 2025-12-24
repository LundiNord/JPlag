import java.io.*;
import java.util.*;

class Par implements Comparable<Par> {
    public double dias,multa,r;
    public int pos;
    Par(double d,double m,int i){
        dias = d;
        multa = m;
        pos = i;
        r = m/d;
    }
    @Override
    public int compareTo(Par p){
        if(r > p.r) return -1;
        else if(r < p.r) return +1;
        else if(pos < p.pos) return -1;
        else if(pos > p.pos) return +1;
        return 0;
    }
}

public class sapateiro {
    static int n;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        Par v[] = new Par[n];
        for(int i=0;i<n;i++)
            v[i]=new Par(in.nextDouble(),in.nextDouble(),i+1);
        Arrays.sort(v);
        for(int i=0;i<n-1;i++)
            System.out.print(v[i].pos + " " );
        System.out.println(v[n-1].pos);
    }



}
