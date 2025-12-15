import java.io.*;
import java.util.*;

class Shoes implements Comparable<Shoes> {
    int duration;
    int penalty;
    float ratio;
    int pos;

    Shoes(int duration, int penalty, float ratio, int pos) {
        this.duration = duration;
        this.penalty = penalty;
        this.ratio = ratio;
        this.pos = pos;
    }

    @Override
    public int compareTo(Shoes s) {
        if (ratio < s.ratio)                // Ordenamos de forma decrescente de ratio( as primeiras posições
            return 1;                       // contém o maior ratio
        else if (ratio == s.ratio) {        // Caso o ratio seja igual, ordenamos por ordem crescente de posição
            if (pos < s.pos)                // ( nas primeiras posições ficam os que contém a menor posição, caso
                return -1;                  //    exista empate de ratio )
            else
                return 1;
        } else
            return -1;
    }
}

public class Shoemaker {
    static int num_shoes;           // Quantidade de sapatos
    static Shoes[] sh;              // Vetor com a duração, a multa, o rácio( Multa/Duração ) e a posição

    // Funçã oque lê o input
    static void readInput() {
        Scanner in = new Scanner( System.in );
        num_shoes = in.nextInt();

        sh = new Shoes[num_shoes];
        int d, p;
        float r;
        for( int i = 0; i < num_shoes; i++ ) {
            d = in.nextInt();
            p = in.nextInt();
            r = (float)(p/d);
            sh[i] = new Shoes( d, p, r, i+1 );
        }
    }

    public static void main( String[] args ) {
        readInput();
        Arrays.sort( sh );

        for(int i = 0; i < num_shoes; i++ )
            System.out.print( sh[i].pos+" " );
        System.out.println();
    }
}
