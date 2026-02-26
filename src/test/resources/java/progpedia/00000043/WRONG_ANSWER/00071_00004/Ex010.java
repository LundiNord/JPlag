
//package ex010;

import java.util.*;

class Trabalho implements Comparable<Trabalho>{
    int pos;
    float c;
    
    Trabalho(int a, float b){
        this.pos=a;
        this.c=b;
        
    }
    
    @Override
    public int compareTo (Trabalho p){
        if(this.c == p.c ){
            if(pos<p.pos)
                return -1;
            else
                return 1;
        }
        else{
            if(p.c > this.c)
                return 1;
            else
                return -1;
        }
    } 
                               
}

public class Ex010 {

     public static void main(String[] args) {
        Scanner stdin= new Scanner(System.in);
        
        //ler 
        int n=stdin.nextInt();
        Trabalho[] trabalhos= new Trabalho[n];
        for(int i=0;i<n;i++){
            int dias= stdin.nextInt();
            trabalhos[i]= new Trabalho(i+1, stdin.nextInt()/dias);
        }
        /*for(int i=0;i<n;i++)
            System.out.println(trabalhos[i].pos + " " + trabalhos[i].c);*/
        
        Arrays.sort(trabalhos);
        
        for(int i=0; i<n-1;i++)
            System.out.print(trabalhos[i].pos + " ");
       
        System.out.println(trabalhos[n-1].pos);
           
    }
    
}
