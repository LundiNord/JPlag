import java.util.ArrayList;
import java.util.Scanner;


public class CT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		int pos = scan.nextInt();
		while (pos !=0){			
			int index = list.indexOf(pos);
			int tam = list.size();
			if (list.contains(pos)) {
				for (int i = index; i <tam; i++){					
					list.remove(index);
				}
			}
			list.add(pos);
			pos = scan.nextInt();
		}
		for (int i : list){
			System.out.println(i);
		}

	}

}
