import java.util.Scanner;


public class Piramides {

	public static class Pyramid{

		long nLayers;
		boolean isBlocked [][];

		public Pyramid(int n){
			nLayers = n;
			isBlocked = new boolean[n][n];
		}

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int nLayers, nBroken, layer, pos;
		long finalPyra [][];

		nLayers = in.nextInt();

		Pyramid pyramid = new Pyramid(nLayers);

		nBroken = in.nextInt();

		for(int i = 0; i < nBroken; i++){
			layer = in.nextInt();
			pos = in.nextInt();

			pyramid.isBlocked[layer-1][pos-1] = true;
		}

		finalPyra = new long[nLayers][nLayers];

		for(int i = nLayers-1; i >= 0; i--){
			for(int j = 0; j < nLayers - i; j++){

				if(i+1 == nLayers){

					if(pyramid.isBlocked[i][j]){
						System.out.println("0");
						return;
					}

					finalPyra[i][j] = 1;
				}

				else if(!pyramid.isBlocked[i][j]){
					
					if(j-1 >= 0)
						finalPyra[i][j] = finalPyra[i+1][j-1] + finalPyra[i+1][j];

					else
						finalPyra[i][j] = finalPyra[i+1][j];
				}

			}
		}

		long soma = 0;

		for(int i = 0; i < nLayers; i++){

			soma += finalPyra[0][i];
		}
		
		System.out.println(soma);

	}

}
