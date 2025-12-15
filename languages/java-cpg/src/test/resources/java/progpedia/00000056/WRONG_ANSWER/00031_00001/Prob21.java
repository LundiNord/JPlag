import java.util.*;

public class Prob21 {

	static double distancia(double sarda1[], double sarda2[]){
		return Math.sqrt(((sarda1[0] - sarda2[0])*(sarda1[0] - sarda2[0])) + ((sarda1[1] - sarda2[1])*(sarda1[1] - sarda2[1])));
	}

	public static void main(String[] args){
			Scanner inp = new Scanner (System.in);

			int nSardas = inp.nextInt();

			double sarda[][] = new double [nSardas][2];
			int pos[] = new int [nSardas], s;
			double aux, total=0.0; 

			for (int i = 0; i < nSardas; i++){
				for(int j=0;j<2;j++)
					sarda[i][j] = Double.parseDouble(inp.next());
					
				pos[i] = i; 
			}

			for (int i=1; i<nSardas; i++){
				aux = Double.MAX_VALUE;
				s = -1;

				for (int j=0; j<nSardas; j++){
					s = -1;

					if (pos[i] != pos[j]){

						double n = distancia(sarda[i], sarda[j]);
						
						if (n < aux) {
							aux = n;
							s = pos[j];
						}
					}
				}

				total += aux;
				pos[i] = s;
			}

		System.out.printf("%.2f\n", total);
	}
}