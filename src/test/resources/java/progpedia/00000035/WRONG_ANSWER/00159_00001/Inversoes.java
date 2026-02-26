import java.util.Scanner;

public class Inversoes{
    static int n,v[];
    
    static int solve(int v[]){
	int contador=0,t;
	for(int i=0;i<n;i++)
	    for(int j=i+1;j<n;j++)
		if(v[i]>v[j]){
		    t = v[i];
		    v[i] = v[j];
		    v[j] = t;
		    contador += 1;
		}
	/*for(int k=0;k<n;k++)
	    System.out.print(v[k] + " ");
	    System.out.println();
	*/
	return contador;
    }
    
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);

	n = stdin.nextInt();
	
	v = new int[n];

	for(int i=0;i<n;i++)
	    v[i] = stdin.nextInt();
	System.out.printf("%d\n",solve(v));
    }
}
