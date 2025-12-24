import java.util.Scanner;

public class Sapateiro {
	
	static void merge(double v[], int start, int middle, int end) {
		int i, p1, p2;
		double aux[] = new double[end+1];

		p1=start;     // "Apontador" do array da metade esquerda
		p2=middle+1;  // "Apontador" do array da metade direita
		i = start;    // "Apontador" do array aux[] a conter juncao
		while (p1<=middle && p2<=end) {            // Enquanto de para comparar
			if (v[p1] <= v[p2]) aux[i++] = v[p1++];  // Escolher o menor e adicionar
			else                aux[i++] = v[p2++];
		}
		while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
		while (p2<=end)    aux[i++] = v[p2++];
		for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
    }
	
	static void mergesort(double v[], int start, int end) {
    	int middle;
    	if (start<end) {                 // Parar quando tamanho do array < 2
    		middle = (start+end)/2;        // Calcular ponto medio
    		mergesort(v, start, middle);   // Ordenar metade esquerda
    		mergesort(v, middle+1, end);   // Ordenar metade direita
    		merge(v, start, middle, end);  // Combinar duas metades ordenadas
    	}
    }

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			double tdias[] = new double[n];
			double multa[] = new double[n];
			for(int i = 0; i < n; i++){
				tdias[i] = in.nextInt();
				multa[i] = in.nextInt();
			}
			
			double ratio[] = new double[n];
			for(int i = 0; i < n; i++){
				ratio[i] = tdias[i]/multa[i];
			}
		
			double aux[] = new double[n];
			for(int i = 0; i < n; i++)
				aux[i] = ratio[i];
		
			mergesort(aux,0,n-1);
		
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					if(aux[i] == ratio[j]){
						ratio[j] = -1;
						System.out.print(" " + j+1 + " ");
					}
			
			System.out.println();
		}

}
