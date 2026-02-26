import java.util.*;



class Sarda{
	double posx;
	double posy;
	
	Sarda(double x, double y){
		posx = x;
		posy = y;
	}
}

public class Sardas {
	
	static Sarda[] sardas;
	static double[][] matriz;
	static double answer = 0;
	static int nsardas;
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		nsardas = in.nextInt();
		
		sardas = new Sarda[nsardas];
		matriz = new double[nsardas][nsardas];
		
		for (int i = 0; i <nsardas; i++) {
			double x = in.nextDouble();
			double y = in.nextDouble();
			sardas[i] = new Sarda(x,y);
		}
		
		for (int i = 0; i < nsardas; i++) {
			for (int j = i+1; j < nsardas; j++) {
				double peso = Math.sqrt(Math.pow(sardas[i].posx - sardas[j].posx, 2) + Math.pow(sardas[i].posy - sardas[j].posy, 2));
				matriz[i][j] = peso;
				matriz[j][i] = peso;
			}
		}
		
		prim(matriz);
		System.out.printf("%.2f\n",answer);
	}

	static void prim(double[][] grafo) {
		double[] custo = new double[nsardas];
		boolean[] fixo = new boolean[nsardas];
		
		for (int i = 0; i < nsardas; i++) {
			fixo[i] = false;
			custo[i] = Double.MAX_VALUE;
		}
		
		custo[0] = 0;
		for (int left = nsardas; left>0; left--) {
			int no = -1;
			for (int i = 0; i < nsardas; i++) {
				if (!fixo[i] && (no==-1 || custo[i]<custo[no])) {
					no = i;
				}
			}
			fixo[no] = true;
			
			if (custo[no] == Double.MAX_VALUE) {
				answer = Double.MAX_VALUE;
				break;
			}
			
			answer += custo[no];
			
			for (int i = 0; i < nsardas; i++) {
				if (custo[i]> grafo[no][i]) {
					custo[i] = grafo[no][i];
				}
			}
		}
	}
}
