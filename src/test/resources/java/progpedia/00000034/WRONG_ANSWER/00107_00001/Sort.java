// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	int v[];
  	Scanner in = new Scanner (System.in);
  	int num = in.nextInt();
  	v=new int[num];
	for (int i=0; i<num; i++){
	    v[i]=in.nextInt();
	}
	
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);
	
	System.out.print("Depois de ordenar: ");
	for (int i=0; i<v.length-1; i++)
	    System.out.print(v[i] + " ");
	System.out.println(v[v.length-1]);

    }
}