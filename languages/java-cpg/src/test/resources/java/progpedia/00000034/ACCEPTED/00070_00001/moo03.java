import java.util.*;

class moo03{
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);
	
	int n   = in.nextInt();
	int[] s = new int[n];
	
	for(int i=0; i<n; i++)
	    s[i] = in.nextInt();
	
	/*
	for(int i=0; i<n; i++){
	    String j=sort(s[i]);
	}
	*/
        Arrays.sort(s);
	for(int j=0; j<n-1; j++) 
	    System.out.print(s[j]+" ");

	System.out.println(s[n-1]);
    }
}  


