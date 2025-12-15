import java.io.*;
import java.util.*;
 
public class Inversoes{

public static void main(String args[]) {
 
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int v[] = new int[n];
      
        for (int i=0; i<n; i++)
            v[i] = stdin.nextInt();

        int contador = 0;
        for(int i=0; i<=n-1; i++){
        	for(int j=i+1; j<=n-1; j++){
        		//System.out.print("v[i]: " +v[i]);
        		//System.out.println("   v[j]: " +v[j]);
        		if(v[i] > v[j]){
        		   //System.out.println("trocou: " +v[i] +v[j]);
        		   contador = contador + 1;
        		}
        	}
        }
        		   	
        System.out.println(contador);


}

}