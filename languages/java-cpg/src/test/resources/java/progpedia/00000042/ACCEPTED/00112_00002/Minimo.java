import java.io.*;
import java.util.*;

class Pontos implements Comparable <Pontos> {

    int x,y;
    
    public Pontos(int a, int b){
	this.x = a;
	this.y =b;

    }

    @Override
    public int compareTo (Pontos p){
	if(x< p.x)
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

public class Minimo {
  
    
    public static void main(String args[]) {
	int m,n,end, best, counter,index;
	counter = 0;
	best= 0;
	end = 0;
	index = 0;
	Pontos v[];
	Scanner stdin = new Scanner(System.in);
    	    
	m = stdin.nextInt();
	n = stdin.nextInt();
	v= new Pontos [n];
	for(int i = 0; i<n; i++)
	    {
		v[i] = new Pontos(stdin.nextInt(),stdin.nextInt());
	    
	    }
	Arrays.sort(v);

	while(end <  m )
	    {
		
		for(int i = index; i<n; i++)
		    {	
			if(v[i].x<=end)
			    {
				if(v[i].y>best)
				    {
					best = v[i].y;
					index=i;
				    }	
			    }
			
		    }
		end = v[index].y;
		counter++;
	    }
	
	
	
		
	 System.out.println(counter);
			
    }
	
   
}

