import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
	int x1;
	int x2;
	
	Point( int x1, int x2 ) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	public int compareTo( Point v ) {
		if( x1 == v.x1 ) {
			return v.x2 -x2;
		}
		else
		return x1 - v.x1;
	} 
}

class Cover {
	
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		int s 	  = in.nextInt();
		int num_s = in.nextInt();
		Point[] v = new Point[num_s];
		
		for( int i = 0; i < num_s; i++ )
		v[i] = new Point( in.nextInt(), in.nextInt() );
		
		Arrays.sort( v );
		
		int start = 0;
		int end   = 0;
		int count = 0;
		int j = 0;
		while( end <= s && j < v.length-1) {
			if( v[j].x1 <= end && v[j].x2 > start ) {
				count++;
				end   = v[j].x2;
				start = v[j].x1;
			}
			j++;
		}
		
		//for( int i = 0; i < num_s; i++ )
		//System.out.println( v[i].x1+" "+v[i].x2 );
		
		System.out.println( count );
		
			
	}
}
