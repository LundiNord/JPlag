import java.io.*;
import java.util.*;

class Path implements Comparable<Path> {
    public int start;
    public int end;
    
    Path(int s, int e) {
	start = s;
	end = e;
        
    }
    
    @Override
    public int compareTo(Path p) {
	if (start <= p.start) return -1;
	else return +1;
    }
}

public class Prob09{
    static int m;
    static int n;
    static Path s[];

    static int solve(){
        int end=0;
	int aux=0;
	int esp=0;
	int maxi=0;
	
	while(end<m){
	    for(int i=maxi;i<n;i++){
		if(s[i].start <= end)
		    if(s[i].end > aux){
			aux = s[i].end;
			maxi=i;
		    }
	    }
	    end=aux;
	    esp++;
	}
	return esp;
    }

          
    static void readInput(){
	Scanner stdin = new Scanner(System.in);

	m=stdin.nextInt();
	n=stdin.nextInt();

        s = new Path[n];
	
	for(int i=0;i<n;i++)
	    s[i]=new Path(stdin.nextInt(), stdin.nextInt());
	    

	Arrays.sort(s);
    }
   
    public static void main(String args[]){

	readInput();
	System.out.println(solve());
    
    }
}
