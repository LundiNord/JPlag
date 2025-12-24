import java.io.*;
import java.util.*;



public class Piramides
{
    //public int c=0;
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int camadas = input.nextInt();
        int buracos = input.nextInt();
        int [][] matriz = new int [camadas][camadas];

        for(int i=0;i<camadas;i++)
            for(int j=0;j<camadas;j++)
                matriz[i][j]=1;

        for(int i=0;i<buracos;i++)
            matriz[(camadas-1)-(input.nextInt()-1)] [input.nextInt()-1]=0;


        int c=0;
        /*for(int i=0;i<camadas;i++)
        {
            for(int j=0;j<camadas;j++){
            System.out.print(matriz[i][j]);}
            System.out.println();
            //asd(matriz,camadas,1,camadas,c);
        }*/

        System.out.println(asd(matriz,camadas));
    }

public static long asd(int [][] matriz, int camadas){

  for(int i=camadas-2; i>=0; i--) {
    for(int j=0; j<=i; j++) {
      if (matriz[i][j] != 0)
	matriz[i][j] = matriz[i+1][j] + matriz[i+1][j+1];
    }
  }
  return matriz[0][0]; // o topo da piramide contem o nº de caminhos
}




    /*public static void asd(int [][] matriz,int i,int j, int camadas, int c)
    {
        if(i>0)
        {

            if(j==i)
            {
                if(matriz[i-1][j-1]!=0)
                {
                    asd(matriz,i-1,camadas-1,camadas,c);
                }
            }

            if(j==0)
            {
                if(matriz[i-1][j]!=0)
                {
                    asd(matriz,i-1,camadas-i,camadas,c);
                }
            }

            else
            {
                if(matriz[i-1][j]!=0)
                {
                    asd(matriz,i-1,j,camadas,c);
                }

                if(matriz[i-1][j-1]!=0)
                {
                    asd(matriz,i-1,j,camadas,c);
                }
            }


        }

        if(i==0 && matriz[0][0]!=0) c++;

    }*/


}



