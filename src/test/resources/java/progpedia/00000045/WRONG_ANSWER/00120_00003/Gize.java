import java.util.*;

class Gize{
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int niveis;

		int firstLayer = niveis = in.nextInt();

		int missStones = in.nextInt();

		double piramid[][] = new double[firstLayer][niveis];

		for(int i=niveis-1; i>=0; i--){
			for(int j=i; j>=0; j--){
				piramid[i][j]=1;
			}
		}

		for(int i=0; i<missStones; i++){
			int level = in.nextInt()-1;
			int place = in.nextInt()-1;
			piramid[niveis-level-1][place] = 0;
		}

		//       // Matriz
		// System.out.println();
		// for(int i=0; i<niveis; i++){
		// 	for(int j=0; j < i+1; j++){
		// 		System.out.print(piramid[i][j]+" ");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();

		for(int i=niveis-1; i>=0; i--){
			for(int j=i; j>=0; j--){
				if(i<niveis-1 && piramid[i][j]!=0)
					// System.out.println
					piramid[i][j] = piramid[i+1][j]+piramid[i+1][j+1];
				

			}
		}

		System.out.printf("%.0f\n",piramid[0][0]);

		// //       Matriz
		// System.out.println();
		// for(int i=0; i<niveis; i++){
		// 	for(int j=0; j < i+1; j++){
		// 		System.out.print(piramid[i][j]+" ");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();

		

	}
}