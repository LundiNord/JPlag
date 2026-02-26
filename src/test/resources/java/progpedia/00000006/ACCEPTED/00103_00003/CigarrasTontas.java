import java.util.Scanner;
public class CigarrasTontas {

	public static void main(String[] args) {
		 
		Scanner in = new Scanner(System.in);
		int aux = in.nextInt(),index =0,i,tam = 0;
		int v[] = new int[300];
		int res[] = new int[300];
		res[0] = aux;
		while(aux!=0){
			aux = in.nextInt();
			index++;
			res[index] = aux;
			for(int j = 0;j<index;j++){
				if(aux == res[j]){
					int temp = j;
					j = index;
					index = temp;
				}
			}
		}
		for(int j = 0; j < index; j++){
			System.out.println(res[j]);
		}
	}

}
