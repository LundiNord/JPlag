import java.util.*;

class No{
	LinkedList<Integer> adjac;
	int ext;


	No(){
		adjac = new LinkedList<Integer>();
		ext=0;

	}
}

public class biologia {
	static int num_nos;
	static int[][] matriz;
	static No[] nos;
	static int diametro=0;
	static LinkedList<Integer> centrais;
	static LinkedList<Integer> perifericos;
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		num_nos = in.nextInt();
		matriz = new int[num_nos+1][num_nos+1];
		nos = new No[num_nos+1];

		for(int i=0;i<=num_nos;i++){
			nos[i]=new No();
		}
		int num_arest = in.nextInt();

		for(int i=0;i<num_arest;i++){
			int a = in.nextInt();
			int b = in.nextInt();
			nos[a].adjac.add(b);
			nos[b].adjac.add(a);
		}

		for(int i=1;i<=num_nos;i++){
			criaMatriz(i);
		}


		centrais = new LinkedList<Integer>();
		perifericos = new LinkedList<Integer>();

		for(int i=1;i<=num_nos;i++){
			if(nos[i].ext==diametro)
				perifericos.add(i);

			if(nos[i].ext==raio())
				centrais.add(i);
		}

		System.out.println(diametro);
		System.out.println(raio());
		Collections.sort(centrais);
		System.out.print(centrais.removeFirst());
		if(!centrais.isEmpty()){
		for(int i : centrais){
			System.out.print(" "+ i);
		}
		System.out.println();
		}
		else
			System.out.println();

		Collections.sort(perifericos);
		System.out.print(perifericos.removeFirst());
		if(!perifericos.isEmpty()){
		for(int i : perifericos){
			System.out.print(" "+ i);
		}
		System.out.println();
		}
		else
			System.out.println();




	}

	static void criaMatriz(int n){


			LinkedList<Integer> n_visit = new LinkedList<Integer>();
			n_visit.add(n);
			boolean visitados[] = new boolean[num_nos+1];
			visitados[n]=true;
			while(!n_visit.isEmpty()){
				int proximo = n_visit.removeFirst();
				for(int i : nos[proximo].adjac){
					if(!visitados[i]){
						n_visit.add(i);
						visitados[i]=true;

						matriz[i][n]=matriz[proximo][n]+1;
						matriz[n][i]=matriz[i][n];
						if(matriz[i][n]>nos[i].ext)
							nos[i].ext=matriz[i][n];

						if(matriz[i][n]>diametro)
							diametro=matriz[i][n];
					}
				}
			}
		}

	static int raio(){
		int min=diametro;
		int max_local=0;

		for(int i=1;i<=num_nos;i++){
			for(int j=1;j<=num_nos;j++){
				if((matriz[i][j])>max_local)
					max_local = matriz[i][j];
			}
			if(max_local<min)
				min=max_local;

			max_local=0;
		}

		return min;
	}
}
