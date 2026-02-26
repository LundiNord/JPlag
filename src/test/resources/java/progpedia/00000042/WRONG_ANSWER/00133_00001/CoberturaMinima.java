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

        for(int i=1;i<n_pontos;i++)
        {
            if(v[i].a>=end && v[i].b<=m)
            {
                end=v[i].b;
                contador++;
            }
        }

        System.out.println(contador);

    }

}
