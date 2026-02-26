import java.util.Scanner;
import java.util.ArrayList;
public class Dizzy {

	public static void main(String[] args) {
		Scanner input=new Scanner (System.in);
		ArrayList<Integer> lista=new ArrayList<Integer>();
		int aux;
		aux=input.nextInt();
		while(aux!=0){
			lista.add(aux);
			aux=input.nextInt();
		}
		for(int i=0;i<lista.size();i++){
			for(int j=i;j<lista.size();j++){
				if(lista.get(i).equals(lista.get(j))){
					i=j;
				}
			}
			System.out.println(lista.get(i));
			
		}
	}

}
