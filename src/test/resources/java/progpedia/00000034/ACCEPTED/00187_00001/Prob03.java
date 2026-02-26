import java.util.*;
public class Prob03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numero = input.nextInt();
		int v[] = new int[numero];
		for (int i=0;i<numero;i++){
			v[i]=input.nextInt();			
		}
		Arrays.sort(v);
		for (int j=0;j<numero-1;j++){
			System.out.print(v[j] + " ");
			if (j==v.length-2){
				System.out.print(v[j+1]);
			}
		}
		System.out.println();
	}
}