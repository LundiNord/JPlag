/*
Ordenar actividades por ordem crescente do seu in´ıcio (li).

Come¸car por iniciar end = 0 (sendo que vamos sempre tendo coberto
o segmento [0, end])

Processar na lista todos os segmentos que tˆem in´ıcio pelo menos em
end (li ≤ end), e escolher destes o que termina depois (maior ri).

Actualizar end para o s´ıtio onde termina o segmento escolhido e
repetir o passo anterior at´e que end ≥ M

*/


import java.util.*;

class segmento implements Comparable<segmento>{
    int li, ri;
    segmento(int a, int b){
        li = a;
        ri = b;
    }

    public int compareTo(segmento a){
        if(li<a.li) return -1;
        else if(li>a.li) return 1;
        else {
            if(ri>a.ri) return -1;
            else if(ri<a.ri) return 1;
            else return 0;
        }
    }
}

class cob_minima {
    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);

        int m = stdin.nextInt();
        int n = stdin.nextInt();
        segmento seg[] = new segmento[n];
        for(int i=0; i<n; i++)
            seg[i] = new segmento(stdin.nextInt(),stdin.nextInt());

        Arrays.sort(seg);
        int end = 0;
        int quant = 0;
        int ant_f = 0;
        while(end < m){
            for(int i=0; i<n; i++)
                if(seg[i].li <= end && seg[i].ri > ant_f){
                    ant_f = seg[i].ri;
                }
            
            if(ant_f!=0) {quant++; end = ant_f;}
            ant_f = 0;

        }

        System.out.println(quant);

        /*for(int i=0; i<n; i++)
            System.out.println(seg[i].li + " "+ seg[i].ri);*/
    }
}