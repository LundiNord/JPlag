import java .util.*;
import java.io.*;

class ex9
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        int n_segmentos = input.nextInt();
        
        Ponto[] pontos = new Ponto[n_segmentos];
        for(int i = 0; i < n_segmentos; i++)
        {
            pontos[i] = new Ponto(input.nextInt(), input.nextInt());
        }
        Arrays.sort(pontos);
        
        int finale = 0;
        int po = 0;
        int segmentos_nec = 0;
        
        while(finale < n)
        {
            int f_t = finale;
            while(po < n_segmentos && pontos[po].x <= finale)
            {
                if(pontos[po].y > f_t)
                {
                    f_t = pontos[po].y;
                }
                po++;
            }
            finale = f_t;
            segmentos_nec++;
        }
        System.out.println(segmentos_nec);
        }
}

class Ponto implements Comparable<Ponto> // exercicio 4
{
    int x;
    int y;
    
    Ponto(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override // nunca precebi muito bem para que ser este Override
    public int compareTo(Ponto a)
    {
        return this.x -a.x;
    }
}