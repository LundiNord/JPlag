import java.util.Scanner;

public class Inversoes{
    
    public static void main(String[] args){

	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int[] v = new int[n];
	for(int i = 0; i < n; i++)
	    v[i] = in.nextInt();

	int count = 0;
	for(int i = 0; i < n;i++)
	    for(int j = i+1; j < n; j++)
		if(v[i] > v[j])
		    count++;
 
	System.out.println(count);
    }
}
