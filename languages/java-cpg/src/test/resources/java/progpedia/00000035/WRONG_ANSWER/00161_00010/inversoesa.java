import java.util.*;

public class inversoesa {
	public static int[] v;
	public static int n;
	public static int qi;
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		v = new int[n];
		for(int i=0; i<n; i++)
			v[i]=stdin.nextInt();
		//forcabruta();
		System.out.println(qi);
	}
	
	public static void forcabruta(){
		qi = 0;
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++)
				if (v[i]>v[j]) qi++;
		}
	}

}