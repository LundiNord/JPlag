import java.util.Scanner;

class CigarrasTontas {
    private static Scanner stdin = new Scanner(System.in);

    public static void main(String [] args) {
        int [] visitados = lerCaminho();
        int entrada = getEntrada(visitados);
        printPercurso(visitados, entrada);
    }

    public static int [] lerCaminho() {
        int [] locais = new int[10000];

        int entrada = stdin.nextInt();
        int inicio = entrada;
        int fim = stdin.nextInt();
        locais[inicio] = fim;
        inicio = fim;
        
        while (inicio != 0) {
            fim = stdin.nextInt();
            locais[inicio] = fim;
            inicio = fim;
        }

        locais[0] = entrada;
        return locais;
    }

    public static int getEntrada(int [] visitados) {
        return visitados[0];
    }

    public static void printPercurso(int [] visitados, int entrada) {
        int cursor = entrada;
        while (cursor != 0) {
            System.out.println(cursor);
            cursor = visitados[cursor];
        }
    }
}
