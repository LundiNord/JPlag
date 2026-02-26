import java.util.*;
import java.io.*;

class ex12
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        int[][] pir = new int[n][n] ;
        
        int d = input.nextInt();
        
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                pir[i][j] = 1;
            }
        }
        
        for(int i = 0; i < d; i++)
        {
            int a = input.nextInt();
            int b = input.nextInt();
            pir[a - 1][b - 1] = 0;
        }
        
        for(int  i = 1; i < n ; i++)
        {
            for(int j = 0; j < n-i; j++)
            {
                if(pir[i][j] == 0)
                {
                    pir[i][j] = 0;
                }else
                {
                    pir[i][j] = pir[i - 1][j] + pir[i -1][j + 1];
                }
            }
        }
        
        System.out.println(pir[n - 1][0]);
    }
}