import java.io.*;
import java.util.*;

class Pontos implements Comparable <Pontos> {

    float x,y;
    
    
    public Pontos(float a, float b){
	this.x = a;
	this.y = b;
;


    }

    @Override
    public int compareTo (Pontos p){
	if(x <p.x)
	    {
	    return -1;
	    }
	if( x > p.x)
	    {
	    return 1;
	    }
	return 0;


    }
}

public class Sapateiro {
  
    
    public static void main(String args[]) {
	int n,k;
	float z;
	z=0;
	k=1;
	Pontos v[];
	Pontos vetor[];
	Scanner stdin = new Scanner(System.in);
    	    

	n = stdin.nextInt();
	v= new Pontos [n];
	vetor = new Pontos [n];
	for(int i = 0; i<n; i++)
	    {
		v[i] = new Pontos(stdin.nextInt(),stdin.nextInt());
		z=v[i].x/v[i].y;
		vetor[i] = new Pontos(z,k);
		k++;
	    }
	Arrays.sort(vetor);
	for(int i =0; i<n; i++)
	    {
		System.out.print((int)vetor[i].y+" ");
	    }
        
			
    }
	
   
}

