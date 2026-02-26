import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class prob03 {
    public static void main(String args[]) {

    // Um array exemplo 
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int[] seq = new int[n];
    for(int i=0; i<n; i++){
        seq[i]=stdin.nextInt();
    }
    
    
    // Chamada ao sort padrao da linguagem Java
    Arrays.sort(seq);
    
    for (int i=0; i<n-1; i++)
        System.out.print(seq[i] + " ");
    System.out.print(seq[n-1]);
    System.out.println();

    }
}