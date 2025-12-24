import java.util.Scanner;


public class cigarra {

	public static void main(String[] args) {
		int locais[] = new int[31];
		int i, j, n = 1;
		
		Scanner stdin = new Scanner(System.in);
		
		locais[n] = stdin.nextInt();
	
		while (locais[n] != 0) {
			
			for (i = 1 ; i <= n ; i++) {
				if (locais[n] == locais[i])
					n=i;
			}
			
			n++;	
			locais[n] = stdin.nextInt();
		}
		for (i = 1 ; i < n ; i++) {
			System.out.println(locais[i]);
		}
	}

}
