import java.io.*;
import java.util.*;

public class Piramide{

public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();

    int a[][] = new int[n][n];
    //for(int i=0;i<n+1;i++)
    //	for(int j=0;j<i;j++)
    //		System.out.println(a[i][j]);

    for(int i=0; i<n; i++){
    	//System.out.println("sim");
    	a[0][i] = 1;
    }
    int c = stdin.nextInt();
    for(int i=0; i<c; i++)
       a[stdin.nextInt()-1][stdin.nextInt()-1] = -1;


   //print
   	 for(int i=0; i<n; i++){
   	 	//System.out.println();
   	 	
      for(int j=0; j<(n-i); j++){
   	 		//System.out.print(a[i][j]);
   	 		
   	 		

   	 	}
   	   
     }
    
     //System.out.println();
     

     for(int i =1; i<n; i++){
        for(int j=0; j<(n-i); j++){
          //if((a[i][j]) != -1 && a[i-1][j] != -1 && a[i-1][j+1] != -1){
            //a[i][j]= a[i-1][j] + a[i-1][j+1];
          //}

          if(a[i][j] !=-1){
             if(a[i-1][j] != -1){
              a[i][j] += a[i-1][j];
             }

             if(a[i-1][j+1] != -1){
                a[i][j] += a[i-1][j+1];
             }
          }
        
         }
        }

   if(a[n-1][0] == -1)
     a[n-1][0] = 0;

   System.out.println(a[n-1][0]);  
     








/*for(int i=0; i<n; i++){
      System.out.println();
      
      for(int j=0; j<(n-i); j++){
        System.out.print(a[i][j]);
        
        

      }
      
     }
  */  

}
}//Piramide