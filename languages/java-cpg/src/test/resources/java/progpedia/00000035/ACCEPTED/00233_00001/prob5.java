import java.util.*;

class prob5{
    public static void main(String args[]){
        
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        
        int array[] = new int[n];
        
        for(int i=0;i<n;i++)
            array[i] = in.nextInt();
        
        int contador = 0;
        
        for(int i=0;i<n;i++)
            for(int j = i+1;j<n;j++)
                if(array[i] > array[j])
                    contador++;            
        
        System.out.println(contador);
        
    }
}
