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
		
		sardas = new Sarda[nsardas+1];
		matriz = new double[nsardas+1][nsardas+1];
		
		for (int i = 1; i <=nsardas; i++) {
			double x = in.nextDouble();
			double y = in.nextDouble();
			sardas[i] = new Sarda(x,y);
		}
		
		for (int i = 1; i <= nsardas; i++) {
			for (int j = i+1; j <= nsardas; j++) {
				double peso = Math.sqrt(Math.pow(sardas[i].posx - sardas[j].posx, 2) + Math.pow(sardas[i].posy - sardas[j].posy, 2));
				matriz[i][j] = peso;
				matriz[j][i] = peso;
			}
		}
		
		prim(matriz);
		System.out.println(answer);
	}

	static void prim(double[][] grafo) {
		double[] custo = new double[nsardas+1];
		boolean[] fixo = new boolean[nsardas+1];
		
		for (int i = 1; i <= nsardas; i++) {
			fixo[i] = false;
			custo[i] = Double.MAX_VALUE;
		}
		
		custo[1] = 0;
		for (int i = nsardas; i>0; i--) {
			int no = -1;
			for (int j = 1; j <= nsardas; j++) {
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
			for (int j = 1; j <= nsardas; j++) {
				if (custo[i]> grafo[no][i]) {
					grafo[no][i] = custo[i];
				}
			}
		}
	}
}
