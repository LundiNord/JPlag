
//package probl3;

import java.util.*;
import java.io.*;


public class Probl3 {

    public static void main(String[] args) {

        
        
       Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        
        int v[] = new int[n];
        
        for(int s=0; s<n;s++)
        {
            v[s]= stdin.nextInt();
            
        }
        
        Arrays.sort(v);
        
        
        for (int i=0; i<n;i++)
        {
            System.out.print(v[i]);
        }
    }
    
}
