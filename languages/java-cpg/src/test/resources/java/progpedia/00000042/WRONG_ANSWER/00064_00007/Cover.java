import java.io.*;
import java.util.*;

class Segment implements Comparable<Segment> {              // Criar uma classe Segment para cada segmento
    int start;
    int end;

    Segment( int start, int end ) {
        this.start = start;
        this.end   = end;
    }

    @Override
    public int compareTo( Segment s ) {                     // Ordenar os segmentos por ordem ascendente de início
        if( start < s.start )
            return -1;
        else if( start == s.start ) {                       // Se os segmentos tiverem o mesmo início, ordenar por
            if( (end - start) < (s.end - s.start) )         // tamanho maior de segmento
                return 1;
            else
                return -1;
        }
        else
            return 1;
    }
}

public class Cover {

    static int seg_size;        // Tamanho do segmento
    static int n_seg;           // Número de segmentos
    static Segment[] seg;       // Vetor do tipo Segment que vai conter o início e fim de cada segmento em cada posição

    // Função que calcula o número de segmentos até cpmpletarmos a cobertura
    static int segmentCounter() {
        int end   = 0;            // Variável local end que indica o fim do último segmento utilizado
        int count = 0;            // Varaivel local count que conta o número de segmentos até completarmos a cobertura

        for( int i = 0; i < n_seg; i++ ) {
            if( end < seg_size ) {
                int pos = Search( seg, seg.length-1, 0, end );          // Usar pesquisa binária para encontrar a posição
                end = end + ( seg[pos].end - seg[pos].start );          // mais próxima de end
                count++;
            }
            else
                break;
        }
        return count;
    }

    static int Search( Segment[] v, int high, int low, int key ) {
        int middle = ( low + high ) / 2;
        while( low < high ) {
            middle = ( low + high ) / 2;
            if( v[middle].start < key )
                high = middle - 1;
            else if( v[middle].start > key )
                low = middle + 1;
            else
                return middle;
        }
        return middle;
    }

    // Função para ler o input
    static void readInput() {
        Scanner in = new Scanner( System.in );
        seg_size = in.nextInt();
        n_seg    = in.nextInt();
        seg = new Segment[n_seg];

        for( int i = 0; i < n_seg; i++ )
            seg[i] = new Segment( in.nextInt(), in.nextInt() );

    }

    public static void main( String[] args ) {
        readInput();
        Arrays.sort( seg );

        int answer = segmentCounter();
        System.out.println( answer );
    }
}
