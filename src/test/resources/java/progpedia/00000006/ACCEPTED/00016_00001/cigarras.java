import java.util.Scanner;
import java.io.*;

interface Constants
{
    public static final int LMAX = 30;
}

public class cigarras implements Constants
{
    public static void main(String args[])
    {
        Scanner myscan = new Scanner(System.in);
        
        
        
	int locais[] = new int[30];
	int i, l, j;
        boolean repetido;

	for(i=0; i < LMAX; i++)
	{
            repetido = false;
            
	    l = myscan.nextInt();
            
            if(l==0)
                break;
            
            for(j=0; j<i; j++)
                if(locais[j] == l)
                {
                    i=j;
                    repetido = true;
                }
        
                
            if(repetido == false)
                locais[i] = l;      
        }
        
        for(j=0; j<i; j++)
            System.out.println(locais[j]);
    }


}
