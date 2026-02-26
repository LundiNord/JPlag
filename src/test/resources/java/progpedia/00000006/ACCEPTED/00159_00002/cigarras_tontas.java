import java.util.*;

public class cigarras_tontas {
    public static void main(String args[]){
        Scanner kb = new Scanner(System.in);

        int posicao= kb.nextInt();
        LinkedList<Integer> caminho = new LinkedList<Integer>();
        while(posicao != 0){
        	caminho.add(posicao);
        	posicao= kb.nextInt();
        }
        for(int i=0; i<caminho.size(); i++){
        	i= caminho.lastIndexOf(caminho.get(i));
        	System.out.println(caminho.get(i));
        }
    }
}
