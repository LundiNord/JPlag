import java.util.Scanner;
import java.util.Vector;

/**
 * 
 */

/**
 * @author artur
 *
 */
public class CigarrasTontas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		int local;
		Vector<Integer> locais = new Vector<Integer>();
		
		
		
		Scanner in = new Scanner(System.in);
		
//		local = in.nextInt();
		
		while ((local = in.nextInt()) != 0) {
			
			
			
			for (int i = 0; i < locais.size(); i ++) {
				if (local == locais.elementAt(i)) {
					for (int j = locais.size() - 1; j >= i; j--) { 
						locais.remove(j);
					}
					
				}
			}
			
			locais.add(local);
			
		}

		for (Integer integer : locais) {
			System.out.println(integer);
		}
		in.close();
	

	}

}
