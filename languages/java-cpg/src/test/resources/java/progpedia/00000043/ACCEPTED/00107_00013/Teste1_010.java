
//package teste1_010;

import java.util.*;

class Vetor implements Comparable <Vetor> { 

    public int dur, mul;
    public int index;
    public float x;
    
    Vetor (int i, int duracao, int multa){
        
        index=i;
        dur= duracao;
        mul= multa;

        x= (float) dur / mul;
        
        
    }
    
    @Override
    public int compareTo (Vetor p){
        
        if ( x > p.x )
            return +1;
        
        if ( x < p.x)
            return -1;
 
        if (index > p.index)
            return +1;
        
        if (index < p.index)
            return -1;
        
        return -1;
        
    }
    
}

public class Teste1_010 {

    
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner (System.in);
        
        int n = stdin.nextInt();
        
        Vetor v[]= new Vetor[n];
        
        for(int i=0; i<n; i++){
            v[i]= new Vetor(i+1, stdin.nextInt(), stdin.nextInt());
        }
        
        Arrays.sort(v);
        
        System.out.println();
        
        for (int j=0; j<n; j++){
            
            System.out.print(v[j].index + " ");
            
        }
        
        
        System.out.println();
        
        
        
    }
    
}

