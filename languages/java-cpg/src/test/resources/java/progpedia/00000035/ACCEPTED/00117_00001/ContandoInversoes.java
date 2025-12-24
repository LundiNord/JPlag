import java.util.Scanner;
public class ContandoInversoes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c = 0;
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int v[] = new int[n];
		for(int i = 0; i < n; i++){
			v[i] = stdin.nextInt();
			//System.out.println(v[i]);
		}
		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				if(v[i]>v[j]) c++;
			}
		}
		System.out.println(c);

	}

}
