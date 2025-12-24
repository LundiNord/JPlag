import java.util.*;

class Sarda {
	double x, y;
	double dist;
	boolean used;

	Sarda(double x ,double y){
		this.x = x;
		this.y = y;
		used = false;
		dist = 999999;
	}

}

class P24 {

	public static void main(String[] args) {


		//Iniciar o scanner
		Scanner teclado = new Scanner(System.in);

 		//Ler o numero de sardas
		int nS = teclado.nextInt();

		//Matrix de distancias
		double distancias[][] = new double[nS][nS];


		//Vector para Sardas
		Sarda sardas[] = new Sarda[nS];

		for(int i = 0; i <nS;i++){
			double x = teclado.nextDouble();
			double y = teclado.nextDouble();
			sardas[i] = new Sarda(x,y);
		}

		for (int i = 0; i < nS ;i++ ) {
			for (int j = i + 1; j< nS ;j++ ) {

				double x1 = sardas[i].x;
				double x2 = sardas[j].x;
				double y1 = sardas[i].y;
				double y2 = sardas[j].y;

				double dist = Math.sqrt( Math.pow(x2-x1,2) + Math.pow(y2-y1,2) );

				distancias[i][j] = distancias[j][i] = dist;

			}
		}

		
		double mst = prim(sardas[0], sardas, nS, distancias);

		System.out.printf("%.2f\n",mst);



	}


	public static double prim(Sarda r, Sarda[] sardas,int nS, double distancias[][]){

		double custo = 0.0;

		r.dist = 0;

		int u = 0;


		for (int i=0;i<nS;i++) {

			double min = 9999999;

			for (int j=0;j<nS ;j++ ) {
				if ( (sardas[j].used == false) && (sardas[j].dist ) < min ){   min = sardas[j].dist ; u = j; }
			}

			//System.out.println("u: " + u + " min:" + min);

			sardas[u].used = true;

			custo+= min; 
			

			for (int v=0;v<nS ;v++ ) {
				if ( (sardas[v].used == false) && (distancias[u][v] ) < sardas[v].dist ){ sardas[v].dist = distancias[u][v];  }
			}


		}

		return custo;
	
	}



}