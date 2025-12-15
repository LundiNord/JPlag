import java.io.*;
import java.util.*;
class SomasProximas
{

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int k=0,flag=0;
        int n=input.nextInt();
        int [] numeros = new int[n];
        for(int i=0;i<n;i++)
            numeros[i]=input.nextInt();

        int p=input.nextInt();
        int [] perguntas=new int[p];
        for(int i=0;i<p;i++)
            perguntas[i]=input.nextInt();
        int u=(n/2)*(n-1);
        int [] somas=new int [u];

        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++)
            {
                somas[k]=numeros[i]+numeros[j];
                k++;
            }

        Arrays.sort(somas);


        for(int i=0;i<p;i++)
        {
            flag=0;
            for(int j=0;j<u;j++)
            {
                if(somas[j]>perguntas[i])
                {
                    flag=1;
                    if(j>0)
                    {
                        if(somas[j]-perguntas[i]>perguntas[i]-somas[j-1]){ System.out.println(somas[j-1]); break;}
                        if(somas[j]-perguntas[i]<perguntas[i]-somas[j-1]){ System.out.println(somas[j]); break;}
                        else {System.out.println(somas[j-1] + " " + somas[j]); break;}
                    }
                    else {System.out.println(somas[j]); break;}
                }
            }
            if(flag==0) System.out.println(somas[u-1]);
        }
    }
}
