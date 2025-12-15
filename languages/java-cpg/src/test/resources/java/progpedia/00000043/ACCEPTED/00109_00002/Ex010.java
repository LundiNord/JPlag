
//package ex010;
import java.util.*;

class Encomenda implements Comparable<Encomenda>{
    int dias, multas, indice;
    float r;

    Encomenda(int a,int b,int i){
        this.dias = a;
        this.multas = b;
        this.indice = i;
        
        r = (float)dias/multas;
}  

@Override
public int compareTo (Encomenda p){
    if(this.r < p.r) return -1;
    if(this.r > p.r) return 1;
    if(this.indice < p.indice) return -1;
    if(this.indice > p.indice) return 1;   
    return 1;
}   
}

public class Ex010{
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
                        
        //ler os valores
        int n = stdin.nextInt();
        Encomenda[] a = new Encomenda[n];
        for(int i=0; i<n; i++)
            a[i] = new Encomenda(stdin.nextInt(), stdin.nextInt(), i+1);
            
        Arrays.sort	(a);
        
        for(int i=0; i<n-1; i++){
            System.out.print(a[i].indice + " ");
        }
        System.out.println(a[n-1].indice);
}
}

