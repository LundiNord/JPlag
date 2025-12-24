import java.util.*;
import java.io.*;

class Shoes implements Comparable<Shoes> // igual ao ex4
{
    int po;
    float diference;
    
    Shoes(int po, float diference)
    {
        this.po = po;
        this.diference = diference;
    }    
    
    @Override  //igual ao ex4
    public int compareTo(Shoes s)
    {
        if(this.diference == s.diference)
        {
            if( this.po < s.po)
            {
                return -1;
            }else
            {
                return 1;
            }
        }else
        {
            if(s.diference > this.diference)
            {
                return 1;
            }else
            {
                return -1;    
            }    
        }
    }
}

class ex10
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        Shoes[] diference = new Shoes[n];
        
        for(int i = 0; i < n; i++)
        {
                int time = input.nextInt();
                float m = input.nextInt();
                float p = m / time; // parecido com o problema da sacola
                diference[i] = new Shoes(i + 1, p);
        }
        
        Arrays.sort(diference);
        
        for(int i = 0; i < n - 1; i++)
        {
            System.out.print(diference[i].po + " ");
        }
        System.out.println(diference[(n - 1)].po);
    }
}

