//Ajudado por Diogo Trigo - 201407189

import java.io.*;
import java.util.*;


class ponto implements Comparable<ponto>
{
    public int a;
    public int b;

    ponto(int a, int b)
    {
        this.a=a;
        this.b=b;
    }

    @Override
    public int compareTo(ponto p)
    {
        if (a < p.a) return -1;
        if (a > p.a) return +1;
        if (b > p.b) return -1;
        if (b < p.b) return +1;
        return 0;
    }
}

public class CoberturaMinima
{

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int contador=0, end=0;
        int m = input.nextInt();
        int n_pontos = input.nextInt();
        ponto v[] = new ponto[n_pontos];
        for(int i=0;i<n_pontos;i++)
            v[i]=new ponto(input.nextInt(), input.nextInt());

        Arrays.sort(v);
        if(v[0].b<=m){
        end=v[0].b; contador++;}
        int inicio=0;
        for(int i=1;i<n_pontos;i++)
        {

            /*if(v[i].a==end && v[i].a<m)
            {
                inicio=i;
                end=v[i].b;

                contador++;
            }

            else if(v[i].a>end)
            {
                int n=1;
                while(1==1 && i-n>inicio)
                {
                    if(v[i-n].a<m)
                    {
                        inicio=end;
                        end=v[i-n].b;
                        contador++;
                        break;
                    }
                    n++;
                }


            }

            if(end>=m) break;*/

            if(v[i].a<=end)
            {
                int max=v[i].b, pos=i;
                while(i+1 != n_pontos && v[i+1].a <=end)
                {
                    if(max<v[i+1].b)
                    {
                        max=v[i+1].b;
                        pos=i+1;
                    }
                    i++;
                }
                i=pos;
                end=v[i].b;
                contador++;
            }
            if(end>=n_pontos) break;
            i++;


        }

        System.out.println(contador);

    }

}
