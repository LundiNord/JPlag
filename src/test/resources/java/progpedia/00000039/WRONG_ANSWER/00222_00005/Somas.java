
import java.util.Arrays;
import java.util.Scanner;

public class Somas {

    static int bsearch(int v[], int low, int n, int k) {

        int middle = 0;
        int size = n;

        while (low < n) {
            middle = low + (n - low) / 2;
            if (k == v[middle]) {
                return middle;
            } else if (k < v[middle]) {
                n = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        
        middle = low;

        if (middle > 0 && middle < size) {
            if (k - v[middle - 1] < v[middle] - k) {
                return middle - 1;
            }
            if (k - v[middle - 1] == v[middle] - k) {
                return middle;
            }
            if (v[middle] - k < k - v[middle - 1]) {
                return middle;
            }
        }
        if (middle >= size) {
            middle = size - 1;
        }

        return middle;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int v[] = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
        }

        int size = (n * (n - 1)) / 2;
        int somas[] = new int[size];

        int pos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                somas[pos++] = v[i] + v[j];
            }
        }
        Arrays.sort(somas);

        int p = in.nextInt();
        int pi, resp;
        for (int i = 0; i < p; i++) {
            pi = in.nextInt();
            resp = bsearch(somas, 0, size, pi);
            if (resp != -1) {
                System.out.println(somas[resp]);
            } else {
                System.out.println(resp);
            }
        }

    }
}
