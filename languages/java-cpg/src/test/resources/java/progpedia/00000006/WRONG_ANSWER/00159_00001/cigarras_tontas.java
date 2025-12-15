import java.util.*;

public class cigarras_tontas {
    public static void main(String args[]){
        Scanner kb = new Scanner(System.in);
        int posicao= kb.nextInt();

        List<Integer> caminho = new LinkedList<Integer>();
        caminho = new ArrayList<Integer>();
        caminho.add(posicao);
        
        while(posicao != 0){
            if (caminho.indexOf(posicao) != -1) {
                int index = caminho.indexOf(posicao);
                for(int i= index+1; i<caminho.size(); i++){
                    caminho.remove(caminho.size()-1);
                }
            }
            else{
                caminho.add(posicao);
            }
            posicao= kb.nextInt();
        }
        for(int i=0; i<caminho.size(); i++){
            System.out.println(caminho.get(i));
        }
    }
}
