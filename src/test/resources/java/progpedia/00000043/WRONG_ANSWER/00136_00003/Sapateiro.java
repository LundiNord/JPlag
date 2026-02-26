import java.util.*;

class Encomenda implements Comparable<Encomenda>{
	public int duracao,multa,indice;
	public double ratio;

	Encomenda(int d,int m,int i){
		duracao = d;
		multa = m;
		ratio = (double)duracao/multa;
		indice = i;
	}

	@Override
	public int compareTo(Encomenda e){
		if(ratio < e.ratio) return -1;
		else if(ratio > e.ratio) return +1;
		else if(duracao < e.duracao) return -1;
		else if(duracao > e.duracao) return +1;
		else if(multa < e.multa) return -1;
		else if(multa > e.multa) return +1;
		else if(indice < e.indice) return -1;
		else if(indice > e.indice) return +1;

		return 0;
	}
}


class Sapateiro{
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		Encomenda vetor[] = new Encomenda[n];
		for(int i=0;i<n;i++){
			vetor[i] = new Encomenda(input.nextInt(),input.nextInt(),i+1);
		}	

		Arrays.sort(vetor);
/*
		System.out.println();
		for(int j=0;j<n;j++)
			System.out.println(vetor[j].duracao + " " + vetor[j].multa + " " + vetor[j].ratio + " " + vetor[j].indice);
		*/
			
		int aux=0;	
		for(int k=0;k<n-1;k++){
			System.out.print(vetor[k].indice + " ");
			aux = k;
		}
		System.out.println(vetor[aux+1].indice);
		
	}
}