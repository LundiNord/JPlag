
//TiagoDuraes 201202465
// pedi ajuda na implementação do algoritmo a alguns colegas 


import java.util.*;

class line implements Comparable <line>{
    int l;
    int r;
    
    public line (int l, int r) {
        this.l = l;
        this.r = r;
        
    }
    
  
    public int compareTo(line nxt) {
	if (l < nxt.l) return -1;
	if (l > nxt.l) return +1;
        if (l == nxt.l) {
            if (r > nxt.r) return -1;
            if (r < nxt.r) return +1;
        }
	return 0;
    }
}

public class CobMin {
    static int m;
    
    static int processArray(line v[], int n, int end) {
        int count = 0;
        int i = 0; 
        int bigR = 0;
        while (end < m && i < n) {
            if (v[i].l <= end){
                count++;
                end = v[i].r;
                int j = i+1;
                while (j < n && v[j].l <= end) {
                    if (v[j].r > bigR) {
                        bigR = v[j].r;
                        i = j;
                    }
                    j++;
                }
                
            }
            bigR = 0;
            
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
 
        System.out.println(x);
    }
}
