import java.io.*;
import java.util.*;
import java.math.*;

//Ajuda recebida por Joana Trindade (up201505351)

public class piramides{
    public static void main(String args[]){
	    
        Scanner input = new Scanner(System.in);
        int camadas = input.nextInt();
        int buracos = input.nextInt();
        long [][] matriz = new long [camadas][camadas];

        for(int i=0;i<camadas;i++)
            for(int j=0;j<camadas;j++)
                matriz[i][j]=1;
                
        for(int i=0;i<buracos;i++)
            matriz[(camadas-1)-(input.nextInt()-1)] [input.nextInt()-1]=0;


        int c=0;
      
        System.out.println(function(matriz,camadas));
    }

public static long function(long [][] matriz, int camadas){

  for(int i=camadas-2; i>=0; i--){
    for(int j=0; j<=i; j++) {
      if (matriz[i][j]!=0)
	  matriz[i][j] = matriz[i+1][j] + matriz[i+1][j+1];
	
    }
  }
  return matriz[0][0];
}

}



