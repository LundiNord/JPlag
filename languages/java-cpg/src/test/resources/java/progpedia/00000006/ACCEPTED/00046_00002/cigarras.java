import java.util.*;


public class cigarras {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<Integer> caminho = new LinkedList<Integer>();
		int para_remover;
		int passagem;
		
		Scanner stdin = new Scanner(System.in);
		
		while(!stdin.hasNext("0")){
			passagem = stdin.nextInt();
			
			if(caminho.contains(passagem)){
				
				para_remover = caminho.indexOf(passagem);
				while(true){
					if(caminho.getLast()!=caminho.get(para_remover))
						caminho.removeLast();
					else
						break;
				}
			}
			else
				caminho.add(passagem);
		}
		for(Integer i : caminho){
			System.out.println(i);
		}
	}

}
