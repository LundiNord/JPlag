import java.util.*;
import java.math.*;
import java.io.*;
import java.util.regex.Matcher;

public class Sum {

    static int n_num;           // Quantidade de números na sequência
    static int n_questions;     // Número de perguntas
    static int[] nums;          // Vetor com a sequência de números
    static int[] questions;     // Vetor com as perguntas
    static int[] arraySum;      // Vetor com as somas

    static void readInput() {
        Scanner in = new Scanner(System.in);

        n_num = in.nextInt();
        nums = new int[n_num];
        for (int i = 0; i < n_num; i++)
            nums[i] = in.nextInt();

        n_questions = in.nextInt();
        questions = new int[n_questions];
        for (int i = 0; i < n_questions; i++)
            questions[i] = in.nextInt();
    }

    // Pesquisa Binária para procurar o valor mais próximo e retornar a posição desse valor
    // Vamos dividindo a meio o vetor ordenado
    // Se o valor que procuramos for menor do que o meio, o maior valor passa a ter o valor do meio
    // Se o valor que procuramos for maior do que o meio, o menor valor passa a ter o valor do meio
    // Caso contrário, encontramos o valor mais próximo e retornamo-lo
    static int Search(int[] v, int high, int low, int key) {
        int middle = (low + high) / 2;
        while (low <= high) {
            middle = (low + high) / 2;

            if (key < v[middle])
                high = middle - 1;
            else if (key > v[middle])
                low = middle + 1;
            else
                return middle;
        }
        return middle;
    }

    // Calcular todas as somas possíveis
    static void Sums() {
        int count = 0;
        int maxLength = ((n_num * n_num) - n_num) / 2;          // tamanho máximo do vetor das somas
        arraySum = new int[maxLength];

        for (int i = 0; i < n_num; i++)
            for (int j = i + 1; j < n_num; j++) {
                arraySum[count] = nums[i] + nums[j];
                count++;
            }
    }

    public static void main(String[] args) {
        readInput();
        Sums();
        Arrays.sort(arraySum);

        for (int i = 0; i < n_questions; i++) {
            int pos = Search(arraySum, arraySum.length-1, 0, questions[i]);
            int distance1 = 0, distance2 = 0, distance3 = 0;
            int chosen;

            /* Usamos os distance para saber a distância entre a pergunta e as somas
                As condições if-else confirmam se a posição retornado pela função Search
                é realmente a soma mais próxima
                O chosen é a distância mais pequena entre a pergunta e as somas
              */
            if (pos > 0 && pos < arraySum.length - 1) {
                distance1 = Math.abs(arraySum[pos + 1] - questions[i]);
                distance2 = Math.abs(arraySum[pos - 1] - questions[i]);
                distance3 = Math.abs(arraySum[pos] - questions[i]);

                if (distance1 == distance3)
                    System.out.print(arraySum[pos] + " " + arraySum[pos + 1]);
                else if (distance2 == distance3)
                    System.out.println(arraySum[pos - 1] + " " + arraySum[pos]);
                else {
                    chosen = Math.min( Math.min( distance1, distance2 ), distance3 );
                    if( chosen == distance1 )
                        System.out.println( arraySum[pos+1] );
                    else if( chosen == distance2 )
                        System.out.println( arraySum[pos-1]);
                    else
                        System.out.println( arraySum[pos] );
                }
            } else if (pos == 0) {
                distance1 = Math.abs(arraySum[pos + 1] - questions[i]);
                distance3 = Math.abs(arraySum[pos] - questions[i]);
                if (distance1 == distance3)
                    System.out.println(arraySum[pos] + " " + arraySum[pos + 1]);
                else {
                    chosen = Math.min( distance1, distance3 );
                    if( chosen == distance1)
                        System.out.println(arraySum[pos+1]);
                    else
                        System.out.println( arraySum[pos] );
                }
            } else if (pos == arraySum.length - 1) {
                distance2 = Math.abs(arraySum[pos - 1] - questions[i]);
                distance3 = Math.abs(arraySum[pos] - questions[i]);
                if (distance2 == distance3)
                    System.out.println(arraySum[pos - 1] + " " + arraySum[pos]);
                else {
                    chosen = Math.min( distance2, distance3 );
                    if( chosen == distance2)
                        System.out.println(arraySum[pos-1]);
                    else
                        System.out.println( arraySum[pos]);
                }
            } else
                System.out.println(arraySum[pos]);
        }

    }
}
