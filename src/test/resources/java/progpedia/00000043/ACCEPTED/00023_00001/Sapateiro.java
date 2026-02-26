import java.util.*;

public class Sapateiro {
	
	static class Sapato {
		int indice;
		double racio;

		public Sapato(int indice, double racio){
			this.indice = indice;
			this.racio = racio;
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int nCasos = sc.nextInt();
		int i = 0;
		Sapato sapatos[] = new Sapato[nCasos];
		while(nCasos-- > 0){
			int t1 = sc.nextInt();
			int t2 = sc.nextInt();
			sapatos[i] = new Sapato(i++, (double) t2/t1);
		}
		Arrays.sort(sapatos, new Comparator<Sapato>(){
			public int compare(Sapato s1, Sapato s2){
				if(s1.racio < s2.racio){
					return 1;
				}
				else if(s1.racio == s2.racio){
					return 0;
				}
				else {
					return -1;
				}
			}
		});
		int j;
		for(j = 0; j < sapatos.length-1; j++){
			System.out.print(sapatos[j].indice+1 + " ");
		}
		System.out.println(sapatos[j].indice+1);
	}
}