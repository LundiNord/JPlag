import java.util.*;

public class cigarra {
		
		static LinkedList<Integer> sequencia = new LinkedList<Integer>();
		static LinkedList<Integer> resultado = new LinkedList<Integer>();
	
	public static void main(String[] args) {

		Scanner data = new Scanner(System.in);
		int n = data.nextInt();
		while (n != 0){sequencia.add(n); n = data.nextInt();}
		
		int i = 0;
		while (i < sequencia.size()){
			int l = sequencia.get(i);
			int ultima_ocorrencia = sequencia.lastIndexOf(l);
			resultado.add(l);
			if ( i != ultima_ocorrencia) {i = ultima_ocorrencia + 1;}
			else {i += 1;}
		}
		
		for (int j = 0; j < resultado.size(); j++){
			System.out.println(resultado.get(j));
		}
	}

}
