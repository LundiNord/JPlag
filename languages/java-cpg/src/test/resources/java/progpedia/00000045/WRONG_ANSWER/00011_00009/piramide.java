import java.io.*;
import java.util.*;

public class piramide {
    static int n,d;
    static long count[][];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        d = in.nextInt();
        int c[] = new int [d];
        int p[] = new int [d];
        count = new long [n][n];
       
	for(int i=0;i<n;i++)
	    count[0][i]=1;
	 for(int i=0;i<d;i++) {
            c[i] = in.nextInt() - 1;
            p[i] = in.nextInt() - 1;
            count[c[i]][p[i]] = -1;
        }
	 /*for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++)
                System.out.print(count[i][j] + " ");
            System.out.println();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++)
                System.out.print(count[i][j] + " ");
            System.out.println();
        }
        */
	 caminhos();
	 System.out.println(count[n-1][0]);

    }
    static void caminhos() {
        for(int i=1;i<n;i++)
	    for(int j=0;j<n-1;j++){
		if(count[i][j]!=-1){
		    if(count[i-1][j]!=-1 && count[i-1][j+1]!=-1)
			count[i][j]=count[i-1][j] + count[i-1][j+1];
		    else if(count[i-1][j]!=-1)
			count[i][j]=count[i-1][j];
		    else if(count[i-1][j+1]!=-1)
			count[i][j]=count[i-1][j+1];
		}
		
	    }
    }
}
    
