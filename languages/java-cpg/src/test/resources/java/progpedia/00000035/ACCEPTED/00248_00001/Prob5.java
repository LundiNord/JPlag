import java.util.Scanner;

public class Prob5 {
	
	public static void main(String[] args) {
		int n;
		int [] array;
		int inverts = 0;
		
		Scanner in = new Scanner(System.in);

		n = in.nextInt();
		array = new int [n];

		for(int i= 0; i < n; i++){
			array[i] = in.nextInt();
		}
		
		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				if(array[i] > array[j])
					inverts++;
			}
		}
		
		System.out.println(inverts);

	}

}
