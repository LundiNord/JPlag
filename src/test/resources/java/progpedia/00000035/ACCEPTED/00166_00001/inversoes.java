import java.util.*;

class inversoes {
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int v[] = new int[n];

		for(int i = 0; i < n; i++) 
			v[i] = stdin.nextInt();

		int contador = 0;

		for (int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				if(v[i] > v[j])
					contador = contador +1;
			}
		}

		System.out.println(contador);


	}
}