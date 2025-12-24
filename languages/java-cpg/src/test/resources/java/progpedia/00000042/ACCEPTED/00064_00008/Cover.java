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
        int i = 0, flag = 0;      // Variável local flag que verifica se já atingimos toda a cobertura
        int endSegment = 0;      // Variável local endSegment serve para ajudar a escolher o melhor segmento
        int numSegment = 0;      // Guarda a posição do melhor segmento escolhido

       while( i < n_seg ) {
           int j = i;

           while( seg[j].start <= end ) {               // Ciclo para procurar o melhor segmento

               if( seg[j].end > endSegment ) {          // Encontramos um segmento melhor do que os anteriores
                   endSegment = seg[j].end;             // Guardamos o end do melhor segmento
                   numSegment = j;                      // E guardamos a posição do melhor segmento
               }
               if( seg[j].end >= seg_size ) {           // Chegamos ao fim da cobertura
                   flag = 1;
                   break;
               }
               j++;
           }

           if( flag == 1 ) {             // Se flag == 1, então atingimos a cobertura toda, logo contamos um ultimo
               count++;                  // segmento, saimos do ciclo e retornamos o numero de segmentos da cobertura
               break;
           }
           else {                       // Caso a flag != 1, então ainda não atingimos a cobertura toda
               end = endSegment;        // Encontramos o melhor segmento, substituimos end pelo end do melhor segmento
               count++;
               i = numSegment;          // Saltamos para a posição do melhor segmento no primeiro ciclo
           }
       }
        return count;
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
