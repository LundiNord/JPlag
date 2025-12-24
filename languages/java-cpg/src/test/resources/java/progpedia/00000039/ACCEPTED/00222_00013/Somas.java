import java.util.*;

public class Somas {

    static int bsearch(int[] v, int low, int n, int k) {
        int middle = 0;
        while (low < n) {
            middle = low + (n - low) / 2;
            if (k == v[middle]) {
                return middle;
            } else if (k < v[middle]) {
                n = middle;
            } else {
                low = middle + 1;
            }
        }

        middle = low;

        if (middle > 0) {
            if (k - v[middle - 1] < v[middle] - k) {
                return middle - 1;
            }
            if (k - v[middle - 1] == v[middle] - k) {
                System.out.print(v[middle - 1] + " " );
                return middle;
            }
            if (v[middle] - k < k - v[middle - 1]) {
                return middle;
            }
        }
        
        return middle;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int v[] = new int[n];
        //int size = (n * (n - 1)) / 2;
        //int pi;
        int np = 0;
        
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
            np = np + i;
        }
        
        int somas[] = new int[np];
        int pos = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                somas[pos] = v[i] + v[j];
                pos++;
            }
        }
        
        Arrays.sort(somas);

        int p = in.nextInt();
        int resp;
        for (int i = 0; i < p; i++) {
            pos = in.nextInt();
            resp = bsearch(somas, 0, np-1, pos);
            if (resp != -1) {
                System.out.println(somas[resp]);
            } else {
                System.out.println(resp);
            }
        }
    }
}
