import java.util.*;

public class prob5 {
    public static void main(String args[]) {
	    Scanner in = new Scanner(System.in);
	
        int n   = in.nextInt();
        int v[] = new int[n];
        for (int i=0; i<n; i++)
            v[i] = in.nextInt();

        int count = 0;
        for(int i=0; i<=n-1; i++)
		    for(int j=i+1; j<=n-1; j++)
			    if(v[i] > v[j])
				    count++;
	    
        System.out.println(count);
    }
}