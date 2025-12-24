import java.util.*;


public class sapatos {

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		sapatos[] encomendas = new sapatos[n];

		for(int i=0; i<n; i++){
			sapatos bino = new sapatos(in.nextInt(),in.nextInt());
			encomendas[i] = bino;
		}

		int x=n; 
		List<Integer> answer = new ArrayList<Integer>();

		while(x-->0){
			int max=0, index=0;
			for(int i=0; i<n; i++){
				if(encomendas[i].valor>max && encomendas[i].cheked==false){
					index=i;
					max=encomendas[i].valor;
				}
			}
			answer.add(index+1);
			encomendas[index].cheked=true;
		}
		
		for(int i=0; i<n-1; i++){
			System.out.print(answer.get(i)+" ");
		}
		System.out.println(answer.get(n-1));
	}

	private int dias;
	private int multa;
	private int valor;
	private boolean cheked;
	public sapatos(int dias, int multa){
		this.dias = dias;
		this.multa = multa;
		this.valor = multa/dias;
		this.cheked = false;

	}
}
