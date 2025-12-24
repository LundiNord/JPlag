
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Herlander Silva
 */
public class P03_Ordenando_Numeros {
    
    static int dim;
    static int[] v;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner stdin = new Scanner(System.in);
//        @SuppressWarnings("resource")
//        Scanner stdin = new Scanner(new File("Problemas/P03_Ordenando_Numeros.in"));
        dim = stdin.nextInt();

        v = new int[dim];
        int idx = 0;
        while (idx <= dim - 1) {
            v[idx] = stdin.nextInt();
            idx++;
        }
        
        Arrays.sort(v);
        
//        System.out.print("Depois de ordenar: ");
	for (int i=0; i < v.length -1 ; i++) {
	    System.out.print(v[i] + " ");
        }
	System.out.println(v[v.length-1]);
    }
    
}
