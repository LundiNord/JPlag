import java.util.*;

public class sort {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int s[] = new int[n];
		
		for(int i=0;i<n;i++){
			s[i]=in.nextInt();
		}
		
		Arrays.sort(s);
		
		for(int i=0;i<n-1;i++){
			System.out.print(s[i]+" ");
		}
		System.out.println(s[n-1]);
	}
}
