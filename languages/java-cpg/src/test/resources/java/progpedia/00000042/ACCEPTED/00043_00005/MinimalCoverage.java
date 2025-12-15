import java.io.*; 
import java.util.*;
 
public class MinimalCoverage {
 
    static class Line implements Comparable<Line> {
        int start, end, count;
 
        public Line(int s, int e) {
            start = s;
            end = e;
        }
 
 
        public int compareTo(Line P) {
            if (start < P.start || (start == P.start && end < P.end))
                return -1;
            return 1;
        }
    }
 
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
	int m = stdin.nextInt();
	int n = stdin.nextInt();
	int count = 0;
	int fim = 0;
	int melhor=0;
	
	
        Line v[] = new Line[n];
	
	for (int i = 0; i<n; i++)
	    v[i] = new Line(stdin.nextInt(),stdin.nextInt());

	Arrays.sort(v);

	while (fim<m) {
	    melhor = fim;
	    for(int i = 0 ; i<n ; i++)
		if (v[i].start <= fim && v[i].end > melhor)
		    melhor = v[i].end;
	    fim = melhor;
	    //System.out.println("fim passou a " + fim);
	    count++;
	}
	
/*	
		for(int j = 0 ; j<n-1 ; j++){
			if ( v[j].start <= fim ) {
				if(v[j].end > max)
				max = v[j].end;
				}
		fim = max;
	}
}
}*/

	System.out.println(count);
	
		
       	

}
}

