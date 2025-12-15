import java.util.*;

class No{
	double x;
	double y;
	boolean visitado = false;

	No(double xx, double yy){
		x=xx;
		y=yy;
	}
}

public class Sardas {
	static No nos[];
	static int nsardas;
	static double[][] matriz;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		nsardas = in.nextInt();
		nos = new No[nsardas];
		matriz = new double[nsardas][nsardas];
		for(int i=0; i<nsardas; i++){
			double um = (double) in.nextFloat();
			double dois = (double) in.nextFloat();
			nos[i] = new No(um,dois);
		}
		for(int i=0; i<nsardas; i++){
			for(int j = i+1; j<nsardas; j++){
				double peso=Math.sqrt(Math.pow(nos[i].x - nos[j].x, 2) + Math.pow(nos[i].y - nos[j].y, 2));
				matriz[i][j]=peso;
				matriz[j][i]=peso;
			}
		}
		System.out.println();
		for (int i = 0; i < nsardas; i++) {
			for (int j = 0; j < nsardas; j++) {
				System.out.printf("%.2f | ",matriz[i][j]);
			}
			System.out.println();
		}

		prim(nos);
	}

	static double extrairMinimo(){
		double min = Double.MAX_VALUE;
		int aux=0;
		for(int i = 0; i<nsardas; i++){
			if(nos[i].visitado==true){
				for(int j = 0; j<nsardas; j++){
					if(min>matriz[i][j] && matriz[i][j]>0 && nos[j].visitado==false){
						min=matriz[i][j];
						aux=j;
					}
				}
			}
		}
		nos[aux].visitado = true;
		return min;
	}
	static double tintagasta;
	static void prim(No[] nos){
		nos[0].visitado=true;
		tintagasta += extrairMinimo();
		for(int i = 1 ; i<nos.length-1; i++){
			tintagasta +=extrairMinimo();
		}
		System.out.printf("%.2f\n", tintagasta);
	}

}
