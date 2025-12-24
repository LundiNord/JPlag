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
		if (inicio < s.inicio) 
			return -1;
		else 
			return 1;
	}

}

public class CoberturaMinima {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tamSegCobrir = in.nextInt();
		int quantSegCons = in.nextInt();
		int esq;
		int dir;
		Segmento seg[] = new Segmento[quantSegCons];
		
		for (int i=0; i<quantSegCons; i++){
			esq=in.nextInt();
			dir=in.nextInt();
			seg[i] = new Segmento(esq, dir);
		}
		
		Arrays.sort(seg);
		
		int end = 0;
		int atual_fim = 0;
		int num_seg = 0;
		while (end < tamSegCobrir){
			for (int i=0; i<quantSegCons; i++)
				if (seg[i].inicio <= end)
					if (seg[i].fim > atual_fim)
						atual_fim = seg[i].fim;
			end = atual_fim;
			num_seg ++;
		}
				
		System.out.println( num_seg );
		in.close();
	}
}

