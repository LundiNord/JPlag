
public class Distancias {

	public static void main (String args[]){
		
		System.out.println("Cidade mais central: " + cidadeCentral());

	}//main
	
	static int cidadeCentral(){
		int nc = 3;
		double d[][] = {{0.0, 34.5, 100.0}, {34.5, 0.0, 64.0}, {100.0, 64.0, 0.0}};
		int cidadeCentral;
		float media;
		float menorMedia = 0;
		
		media = 0;
		for (int j=1; j<nc; j++)
			media += d[0][j];
			menorMedia = media/(nc-1);
			cidadeCentral= 0; 
			System.out.println(menorMedia);

		for (int i=1; i<nc; i++) {
			media=0;
			for (int j=0; j<nc; j++) {
				media += d[i][j];
			}
			media = media/(nc-1);
			System.out.println(media);
			if (media < menorMedia) {
				menorMedia = media;
				cidadeCentral= i; 
			}
		}
		return cidadeCentral;

	}
}//class
