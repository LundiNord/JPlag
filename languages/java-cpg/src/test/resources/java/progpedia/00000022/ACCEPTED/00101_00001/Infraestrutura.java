
import java.util.*;

public class Infraestrutura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		int locais = in.nextInt();
		int largMin = in.nextInt();
		int largMax = in.nextInt();
		int compMin = in.nextInt();
		int compMax = in.nextInt();
		int altMin = in.nextInt();
		int origem = in.nextInt();
		int destino = in.nextInt();
		
		
		int count = 0;
		
		
		while(in.hasNextLine()){
			int extremo1 = in.nextInt();
			if(extremo1==-1){
				break;
			}
			int extremo2 = in.nextInt();
			int larguraMax = in.nextInt();
			int comprimentoMax = in.nextInt();
			int alturaMax = in.nextInt();
		
			if(larguraMax >= largMin && comprimentoMax >= compMin && alturaMax >= altMin){
				count++;
			}
		}
		
		System.out.println(count);
		in.close();
		
		
		
		
	}

}
