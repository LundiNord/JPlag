import java.io.*;
import java.util.*;

class Seg {
    int start;
    int end;
    int size;

    Seg(int s, int e){
        start = s;
        end = e;
        size = start - end;
    }
}

class cobertura {
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        int n = sc.nextInt();
        Seg[] v = new Seg[n];
        for (int i=0 ; i<n ; i++) {
            v[i] = new Seg(sc.nextInt() , sc.nextInt());
        }

        /*Arrays.sort(v, new Comparator<Seg>() {
            public int compare(Seg v1, Seg v2) {
                if (v1.start < v2.start){
                    return -1;
                }
                if (v1.start == v2.start){
                    if(v1.size > v2.size){
                        return -1;
                    }
                }

                return 1;
            }
        });*/

        int p = 0;
        int tmp;
        int cont = 0;

        while (p<m) {
            tmp = p;
            for(int i=0 ; i<n ; i++){
                if (v[i].start <= p && v[i].end > tmp) {
                    tmp = v[i].end;
                }
            }
            p = tmp;
            cont++;
        }

        //System.out.println("--------------------");

        System.out.println(cont);

    }
}
