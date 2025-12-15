/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marina
 */
import java.util.*;

class Encomenda implements Comparable<Encomenda> {
   
    int dias;
    int multa;
    int index;
    float ratio;
    public Encomenda(int dias, int multa, int index, float ratio) {
	this.dias = dias; 
	this.multa = multa;
        this.index = index;
       this.ratio = ratio;
    }
    
    
    @Override
    public int compareTo(Encomenda e) {
	if (ratio < e.ratio) return -1;
	if (ratio > e.ratio) return +1;
        if (ratio == e.ratio){
            if (e.multa == e.dias) 
            {
                if (index < e.index) return -1;
                if (index > e.index) return +1;
            }
        }
	return 0;
       
    }       
}


public class Sapateiro {
   
    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);
        int N = stdin.nextInt();
        int dias;
        int multa;
        float ratio;
        int index;
        Encomenda v[] = new Encomenda[N];
        
        for(int i=0; i<N; i++)
            {   
                dias = stdin.nextInt();
                multa = stdin.nextInt();
                ratio = (float)dias / multa;
                index = i+1;
                v[i] = new Encomenda(dias, multa, index, ratio);
                
            }       
        Arrays.sort(v); 
        
    
    for(int i = 0; i < N-1; i++) 
        {
            System.out.print( v[i].index + " ");
        }
        System.out.println(v[N-1].index);
        
    }
}   