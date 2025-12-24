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

		prim(nos);
	}

	static int extrairMinimo(int indice){
		double min = Double.MAX_VALUE;
		int indicez = 0;
		for(int j = 1; j<nsardas; j++){
			if(min>matriz[indice][j] && matriz[indice][j]>0 && nos[j].visitado==false){
				min=matriz[indice][j];
				indicez = j;
			}
		}
		
		return indicez;
	}
	static double tintagasta;
	static void prim(No[] nos){
		int noseguinte=extrairMinimo(0);
		tintagasta += matriz[0][noseguinte];
		nos[0].visitado=true;
		int a = 0;
		for(int i = 1 ; i<nos.length-1; i++){
			a=noseguinte;
			nos[a].visitado=true;
			noseguinte = extrairMinimo(noseguinte);
			tintagasta += matriz[a][noseguinte];
		}
		System.out.printf("%.2f\n", tintagasta);
	}

}
