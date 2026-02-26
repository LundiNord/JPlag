import java.util.*;

class somas implements Comparable<somas> {
    int soma;
    int n, m;
    somas(int i, int j){
        n = i;
        m = j;
        soma = i + j;
    }

    public int compareTo(somas x){
        if(x.soma > soma) return -1;
        else return 1;
    }

}

class somas_mais{
    static somas s[];
    public static void binarySearch(int p){
       binarySearch(0, s.length-1, p);
    }

    public static void binarySearch(int low, int high, int p){
        while(low <= high){
             int midle = low + (high - low)/2;
             if(p == s[midle].soma) {System.out.println(p); break;}
             else if(midle -1 <0 && s[midle].soma>p) {System.out.println(s[midle].soma); break;} 
             else if(midle  == s.length -1 ) {System.out.println(s[midle-1].soma); break;} 
             else if(midle -1 >=0 && s[midle].soma >p && s[midle-1].soma <p ) {
                 if(Math.abs(s[midle-1].soma-p) == Math.abs(s[midle].soma-p)) System.out.println(s[midle-1].soma + " " + s[midle].soma);
                 else if(Math.abs(s[midle-1].soma-p) > Math.abs(s[midle].soma-p)) System.out.println(s[midle].soma);
                 else System.out.println(s[midle-1].soma); 
                 break;
                 }
             else if(p < s[midle].soma) high = midle;
             else low = midle + 1;
        }
    }


    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int num[] = new int[n];
        for(int i=0; i<n; i++)
            num[i] = stdin.nextInt();
        int p = stdin.nextInt();
        int pn[] = new int[p];
        for(int i=0; i<p; i++)
            pn[i] = stdin.nextInt();

        s = new somas[n*(n-1)];

        int k = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(num[i]!= num[j]) s[k++] = new somas(num[i], num[j]);

        Arrays.sort(s);

        /*for(int i=0; i<(n-1)*n; i++)
            System.out.println(s[i].soma);*/
        
        for(int i=0; i<p; i++) {
            binarySearch(pn[i]);
        }
    }
}