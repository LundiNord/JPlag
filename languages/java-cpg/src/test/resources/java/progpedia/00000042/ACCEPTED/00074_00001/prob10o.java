import java.util.*;
     
    class Segmento {
            int inicio;
            int fim;
           
            Segmento(int inicio, int fim) {
                    this.inicio = inicio;
                    this.fim = fim;
            }
    }
     
    class prob10o {
           
            private static int m;
            private static int n;
           
            public static void main(String[] args) {
                    Scanner in = new Scanner(System.in);
                   
                    m = in.nextInt();
                    n = in.nextInt();
                    if (n<=0) return;
                    Integer[][] seg = new Integer[n][2];
                   
                    for (int i=0; i<n; i++) {
                            seg[i][0] = in.nextInt();
                            seg[i][1] = in.nextInt();
                    }
                   
                    Arrays.sort(seg, new Comparator<Integer[]>(){
                    @Override
                    public int compare(Integer[] o1, Integer[] o2) {
                        return o1[0].compareTo(o2[0]);
                    }
                });

                    if (seg[0][0] != 0) {
                        System.out.println("0");
                        return;
                    } else if (n == 1 && seg[0][1] < m) {
                        System.out.println("0");
                        return;
                    }
                    System.out.println(Segmentate(seg));
            }
           
            public static int Segmentate(Integer[][] seg) {
                    int count = 1;
                    int last=0;
                    boolean first;
                    int i=0,max=0;

                    while(seg[i][0] == 0) {
                        if (seg[i][1] > seg[max][1]) {
                            max = i;
                        }
                        if (i+1 == n) { i++; break; }
                        i++;
                    }
                    last = i-1;
                    int index = max;
                    if(seg[max][1] >= m) return count;
                    
                    while(true && n > 1) {
                            first = true;
                            max = 0;
                            for (i = last+1; i<n; i++) {
                                    if ((seg[i][0] <= seg[index][1] && seg[i][0] > seg[index][0])) {
                                            if (seg[i][1] > seg[max][1] || first) { max = i; first=false; }
                                    } else { last = i-1; index=max; break; }
                            }
                            count++;
                            if (seg[max][1] >= m) break;
                            else if (i==n && seg[max][1] < m) return 0;
                    }
                   
                    return count;
            }
    }
 