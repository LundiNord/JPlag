import java.io.*;
import java.util.*;

public class sort {
    public static void main(String args[]) {

    Scanner input = new Scanner(System.in);
    int n = input.nextInt(); 
    int v[] = new int[n];

    for(int i=0; i<n; i++){
           v[i] = input.nextInt();
    }

    Arrays.sort(v);
    
    //depois de ordenar
    for (int i=0; i<n; i++)
        System.out.print(v[i] + " ");
        
    }
}