import java.io.*;
import java.util.*;

class Encomenda implements Comparable<Encomenda>{
    float ratio;
    int dias, multa, index;
    
    Encomenda(int dias, int multa, float ratio, int index){
        this.dias = dias;
        this.multa = multa;
        this.ratio = ratio;
        this.index = index;
    }
    
    @Override
	public int compareTo(Encomenda a){
	
      if (ratio == a.ratio)
	  if (index > a.index) return 1;
	  else return -1;
      else if (ratio > a.ratio) return 1;
      else return -1;
      
    }
}

public class p010{
    
    public static void main(String args[]){
	
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	
    Encomenda s[] = new Encomenda[n];
    for (int i=0; i<n; i++){
        int d = stdin.nextInt();
        int m = stdin.nextInt();
        s[i] = new Encomenda (d,m,(float)d/m, i);
    }
    
    Arrays.sort(s);
    
    for (int i=0; i<n-1; i++)
	System.out.print(s[i].index + 1 +" ");
    System.out.println(s[n-1].index +1);
    
    }
}

//realizado com a ajuda de Alexandre Meneses e Nuno Carvalho