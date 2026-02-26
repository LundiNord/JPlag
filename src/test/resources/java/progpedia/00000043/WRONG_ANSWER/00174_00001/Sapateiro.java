//Realizado com a ajuda de Célio Rodrigues e Carlos Bernardes

import java.util.Scanner;

class Sapateiro {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // o racio entre numero de dias e multa ira dar a solucaoo greedy otima
        double[] racio = new double[n];

        for (int i = 0; i < n; i++) {
            int dias = in.nextInt();
            int multa = in.nextInt();
            racio[i] = dias / (multa + 0.00);
        }

        for (int i = 0; i < n; i++) {
            double min = 999999;
            int index = 0; // o index da permutacao do numero do sapato
            for (int j = 0; j < n; j++) {
                if (min > racio[j] && racio[j] > 0.00) {
                    min = racio[j];
                    index = j;
                }
            }
            // para resolver presentation error (espaaco no fim a mais)
            // se a iteracao nao for a ultima imprime permutacao mais espaaco a frente
            if (i != n - 1) {
                System.out.print((index + 1) + " ");
            } else {
                System.out.println(index + 1); // imprime sÃ³ a perm mais \n
            }
        }
    }
}
