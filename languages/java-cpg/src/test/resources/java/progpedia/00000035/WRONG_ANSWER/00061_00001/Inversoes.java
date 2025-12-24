/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ppetz
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class Inversoes {

    public static int[] aux;
    public static int[] v;
    public static int inversoes;

    public static void imprLista(int[] lista) {
        for (int i = 0; i < lista.length; i++) {
            if (i == lista.length - 1) {
                System.out.print(lista[i]);
            } else {
                System.out.print(lista[i] + " ");
            }

        }
        System.out.println("");
    }

    public static int[] gerarLista(int nNmr, int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("Numero inicial superior do que o final.");
        }

        int[] lista = new int[nNmr];

        int valor;

        for (int i = 0; i < nNmr; i++) {
            valor = (int) (Math.random() * (max - min) + min);
            lista[i] = valor;
            //System.out.println(valor);
        }

        return lista;
    }

    public static int mergesort(int v[], int start, int end) {
        if (end - start == 0) {
            return 0;
        }

        int middle = (start + end) / 2;
        
        int contador=0;
        
        contador += mergesort(v, start, middle);
        contador += mergesort(v, middle + 1, end);
        contador += merge(v, start, middle, end); /*fazer o merge das duas partições anteriores*/
        
        return contador;
    }

    public static int merge(int v[], int start, int middle, int end) {
        int contador=0;
        
        for (int h = start; h <= end; h++) {
            aux[h] = v[h];
        }

        int i = start, j = middle + 1, k = start;
        while (i <= middle && j <= end) {
            if (aux[i] <= aux[j]) {
                v[k] = aux[i];
                i++;
            } else {
                v[k] = aux[j];
                j++;
                contador+=(middle + 1)-i;
            }
            k++;
        }

        while (i <= middle) {
            v[k] = aux[i];
            //contador+=middle-i;
            k++;
            i++;
        }
        
        //System.out.println("#"+contador);
        
        return contador;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int valor, k = 0;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nVec = Integer.parseInt(br.readLine());
        
        v = new int[nVec];
        aux = new int[nVec];
        
        String readLine = br.readLine();

        String[] words = readLine.split(" ");
        
        for (String si : words) {
            v[k]=Integer.parseInt(si);
            k++;
        }

        inversoes=mergesort(v, 0, v.length - 1);
        //imprLista(v);
        System.out.println(inversoes);
    }
}
/**
8
2 4 2 3 1 5 1 6
 
8
76 32 19 87 7 30 17 79
 */