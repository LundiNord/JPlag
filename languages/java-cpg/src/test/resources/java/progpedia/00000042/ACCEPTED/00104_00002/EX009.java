
//package ex009;
import java.util.*;



class Segmento implements Comparable<Segmento>{
    int inicio, fim;

     Segmento(int i, int f){
	this.inicio=i;
	this.fim=f;

    }

    @Override
    public int compareTo(Segmento i){
	return this.inicio - i.inicio;
    }
   
}


public class EX009{
    public static void main(String[] args){
	Scanner stdin= new Scanner(System.in);

	int m= stdin.nextInt();
	int n= stdin.nextInt();



	//ler os segmentos
	Segmento[] segmentos= new Segmento [n];
	for(int i=0;i<n;i++)
	    segmentos[i]= new Segmento(stdin.nextInt(), stdin.nextInt());

	//Ordernar o inicio
	Arrays.sort(segmentos);

	
	int end=0;
	int pos=0;
	int soma=0;
        int a=end;
        while(end<m){
            //System.out.println("."+end);
	    while( pos<n && segmentos[pos].inicio <=end ){
		if(segmentos[pos].fim > a)
		    a=segmentos[pos].fim;
		pos++;
	    }
            end=a;
	    soma++;
        }
	

	System.out.println(soma);

    }
}

