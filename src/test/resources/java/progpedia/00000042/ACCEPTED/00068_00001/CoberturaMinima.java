import java.util.Arrays;
import java.util.Scanner;

class Segmento implements Comparable<Segmento>{
	public int inicio ;
	public int fim ;
	
	Segmento(int i, int f) {
		inicio = i;
		fim = f;
	}
	
	@Override
    public int compareTo(Segmento s) {
		if (inicio < s.inicio) return -1;
		else return 1;
	}

}




public class CoberturaMinima {
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int n = in.nextInt();
		Segmento S[] = new Segmento[n];
		
		for (int i=0; i<n; i++)
			S[i] = new Segmento(in.nextInt(), in.nextInt());
		
		Arrays.sort(S);
		
		int end = 0;
		int atual_fim = 0;
		int num_seg = 0;
		while (end < M){
			for (int i=0; i<n; i++)
				if (S[i].inicio <= end)
					if (S[i].fim > atual_fim)
						atual_fim = S[i].fim;
			end = atual_fim;
			num_seg ++;
			}
				
		System.out.println( num_seg );
		in.close();
	}
}
