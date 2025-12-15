

import java.util.*;
public class Prob3 {


    public static void main(String[] args) {
        Scanner le= new Scanner(System.in);
        int n= le.nextInt();
        int a[]= new int[n];
        for(int i=0; i<n; i++)
            a[i]=le.nextInt();
        Arrays.sort(a);
        for(int i=0; i<a.length; i++)
            System.out.print(a[i]+ " ");
        
    }
    
}
