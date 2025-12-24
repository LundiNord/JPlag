import java.util.Scanner;

class cigarras{
	public static void main(String[] args){
		int[] vec = new int[30];
		Scanner sc = new Scanner(System.in);
		int i=0;

		while((vec[i] = sc.nextInt()) != 0){
			for(int j=0; j<i; j++)
				if(vec[j] == vec[i])
					i = j;

			i++;
		}

		for(int k=0; k<i; k++){
			System.out.println(vec[k]);
		}

		return;
	}
}