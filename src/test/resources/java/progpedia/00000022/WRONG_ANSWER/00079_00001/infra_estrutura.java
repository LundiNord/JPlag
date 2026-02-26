import java.util.*;

public class infra_estrutura {
	public static void main(String []Args) {
		Scanner input = new Scanner(System.in);

		int NLojas = input.nextInt();
		int largMin = input.nextInt();
		int largMax = input.nextInt();
		int compMin = input.nextInt();
		int compMax = input.nextInt();
		int altMin = input.nextInt();
		int origem = input.nextInt();
		int destino = input.nextInt();

		int n = 0;
		int v1 = input.nextInt();
		while(v1 != -1) {
			int v2 = input.nextInt();
			int LMax = input.nextInt();
			int CMax = input.nextInt();
			int altMax = input.nextInt();
			if(LMax >= largMin && CMax >= compMin && altMax >= altMin)
				if(v1 != origem || v2 != destino)
					n++;
			v1 = input.nextInt();				
		}
		System.out.println(n);
	}
}