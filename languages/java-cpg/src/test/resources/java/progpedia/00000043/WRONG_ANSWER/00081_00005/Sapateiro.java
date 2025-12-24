
import java.io.*;
import java.util.*;

 
 

 class Sapato implements Comparable<Sapato>{
	int duracao;
	int multa;
	float mminima;
	int id;

	 Sapato(int d, int m, int i){
	    duracao = d;
	    multa = m;
	    id = i + 1;
	    mminima = (float)(m / d); //multa/duracao da multa minima
	}
     @Override
     public int compareTo(Sapato s1){
	 if(this.mminima>s1.mminima)
	     return -1 ;
	 if(this.mminima<s1.mminima)
	     return +1;
	
		
	 return 0;
	
	
     }
}
class Sapateiro{  
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	int n= stdin.nextInt();
	int[] seq=new int[n];
	Sapato[] s = new Sapato[n];
	
	for(int i=0;i<n; i++){	
	    s[i] = new Sapato(stdin.nextInt(), stdin.nextInt(), i);
	}
	Arrays.sort(s) ;
	 
	 int i;
	for(i=0; i<n-1; i++){
	    seq[i]=s[i].id;
	    System.out.print(seq[i]+ " "); 
	}
	System.out.println(s[i].id);

    }
}
