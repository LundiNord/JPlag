import java.util.*;

class sopa {
	public static void main (String [] args) {
		Scanner kb = new Scanner(System.in);
		String p1, p2;
		int i, flag;

		p1 = kb.nextLine();
		p2 = kb.nextLine();

		i = 0;
		flag = 0;
		while (i != p1.length() && i != p2.length()) {
			if (p1.charAt(i) != p2.charAt(i)) {
				System.out.println(p1.charAt(i) + "" + p2.charAt(i));
				flag = 1;
				break;
			}
			i++;
		}

		if (flag == 0) System.out.println("Nenhum");
	}
}