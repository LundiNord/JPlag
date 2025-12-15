import java.util.Scanner;

public class Inversos{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int lengthSeq = sc.nextInt();
		int [] sequence = new int[lengthSeq];
		for(int i = 0; i < lengthSeq; i++){
			sequence[i] = sc.nextInt();
		}
		int count = 0;
		for(int i = 0; i < lengthSeq; i++){
			int value = sequence[i];
			for(int j = i+1; j < lengthSeq; j++){
				if(sequence[j] > value)
					break;
				if(sequence[j] < value)
					count++;
			}
		}
		System.out.println(count);
	}
}