

import java.util.LinkedList;
import java.util.Scanner;

public class Trabalho {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdin= new Scanner(System.in);
		LinkedList<Integer> caminho = new LinkedList<Integer>();
		while (true){
			int x=stdin.nextInt();
			
			if (x==0){
			break;
			}
			
			caminho.addLast(x);
		}
		while(true){
			int x= caminho.removeFirst();
			while (caminho.contains(x)){
				caminho.removeFirst();
			}
			System.out.println(x);
			if (caminho.isEmpty()){
				break;
			}
		}

	}

}
