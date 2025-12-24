

/**
 *
 * @author Luís Graça up201304954
 */

import java.util.*;

class line implements Comparable <line>{
    int l;
    int r;
    
    public line (int l, int r) {
        this.l = l;
        this.r = r;
        
    }
    
    @Override
    public int compareTo(line seg) {
	if (l < seg.l) return -1;
	if (l > seg.l) return +1;
        if (l == seg.l) {
            if (r > seg.r) return -1;
            if (r < seg.r) return +1;
        }
	return 0;
    }
}

public class coberturaMin {
    static int m;
    static int processArray(line v[], int n, int end) {
        int count = 0;
        int i = 0; 
        int bigR = 0;
        while (end < m && i < n) {
            if (v[i].l <= end){
                if (v[i].r > bigR) {
                    end = v[i].r;  
                    count++;
                }
            }
            i++;
        }
        return count;
    }
    
    public static void main (String args[]) {
        Scanner stdin = new Scanner(System.in);
        m = stdin.nextInt();
        int n = stdin.nextInt();
        line v[] = new line[n];
        for (int i = 0; i < n; i++) {
            int l = stdin.nextInt();
            int r = stdin.nextInt();
            v[i] = new line(l, r);
        }
        Arrays.sort(v);
        int end = 0;
        int x = processArray(v, n, end);
        
        //System.out.println("Vetor ordenado:");
        /*for (int i = 0; i < n; i++) {
            System.out.println(v[i].l + " " + v[i].r);
        }*/
      
        System.out.println(x);
    }
}
