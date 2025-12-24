//Ajuda recebida por Diogo Trigo & André Pereira

import java.io.*;
import java.util.*;

public class cobertura {
    public static void main(String args[]){
	    
		Scanner input = new Scanner(System.in);
		int m = input.nextInt();
		int n = input.nextInt();
		int c=0;
		int fim=0;
		
		ponto v[] = new ponto[n];
		
        for(int i=0;i<n;i++) v[i]=new ponto(input.nextInt(), input.nextInt());
        
        Arrays.sort(v);
        int inicio=0;
        int i=0;
		while(i != n)
		{

            if(v[i].a<=fim)
            {
                int max=v[i].b; 
                int pos=i;
                
                while(i+1 != n && v[i+1].a <=fim)
                {
                    if(max<v[i+1].b)
                    {
                        max=v[i+1].b;
                        pos=i+1;
                    }
                    i++;
                }
                
                i=pos;
                fim=v[i].b;
                c++;
            }
            if(fim>=m) break;
            i++;
	 	}

        System.out.println(c);

           
		}
}

class ponto implements Comparable<ponto>
{
    public int a;
    public int b;

    ponto(int a, int b)
    {
        this.a=a;
        this.b=b;
    }


    public int compareTo(ponto p)
    {
        if (a < p.a) return -1;
        if (a > p.a) return +1;
        if (b > p.b) return -1;
        if (b < p.b) return +1;
        return 0;
    }
}
