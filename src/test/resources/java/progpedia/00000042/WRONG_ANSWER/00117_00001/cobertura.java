import java.io.*;
import java.util.*;
class Segmento{
    int li , ri;

  
    Segmento(int t , int f){
	li = t;
	ri = f;
    }
}
class cobertura {
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int m , n , end = 0 ,start = 0 , contador = 0;
        m = stdin.nextInt();
	n = stdin.nextInt();
	Segmento seg[] = new Segmento[n];
	for(int i = 0 ; i < n ; i++)
	    seg[i] = new Segmento((stdin.nextInt()) , (stdin.nextInt()));
        
	ordena(seg , n);

	/*for(int i = 0; i < n ; i++){
	    System.out.println(seg[i].li + " " + seg[i].ri);

	    }*/
	int k = 0;
	while(end < m){
	    start = end;
	    if(seg[k].li <= start && seg[k].ri > end){
		end = seg[k].ri;
		contador++;
	    }
	    k++;
	}
	System.out.println(contador);
	    
    }
    static void ordena(Segmento seg[] , int n){
	Segmento aux;
	for(int i = 0 ; i < n ; i++){
	    for(int j = i + 1 ; j < n ; j++){
		if(seg[i].li > seg[j].li){
		    aux = seg[i];
		    seg[i] = seg[j];
		    seg[j] = aux;
		}else if(seg[i].li == seg[j].li){
		    if((seg[i].ri - seg[i].li) < (seg[j].ri - seg[j].li)){
			aux = seg[i];
			seg[i] = seg[j];
			seg[j] = aux;
		    }
		}
	    }
	}
	
    }
    
}
