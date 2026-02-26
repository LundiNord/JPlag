import java.util.*;
import java.io.*;

class ex5
{
    public static int ct = 0;
    
    static void merge(int v[], int start, int midle, int end)
    {
        int aux[] = new int[end + 1];
        int i, p1, p2;
        
        p1 = start;
        p2 = midle + 1;
        i = start;
        
        while(p1 <= midle && p2 <= end)
        {
            if(v[p1] <= v[p2])
            {
                aux[i++] = v[p1++];
            }else
            {
                aux[i++] = v[p2++];
                ct = p2 - i + ct;;
            }
        }
        
        while(p1 <= midle)
        {
            aux[i++] = v[p1++];
        }
        
        while(p2 <= end)
        {
            aux[i++] = v[p2++];
        }
        
        for(i = start; i <= end; i++)
        {
            v[i] = aux[i];
        }
    }
    
    static void mergesort(int v[], int start, int end)
    {
        int midle;
        
        if(start < end)
        {
            midle = (start + end) / 2;
            mergesort(v, start, midle);
            mergesort(v, midle + 1, end);
            merge(v, start, midle, end);
        }
    }
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        int v[] = new int[n];
        
        for(int i = 0; i < n; i++)
        {
            v[i] = input.nextInt();
        }
        
        mergesort(v, 0, n-1);
        
        System.out.println(ct);
    }
}