import java.util.*;

public class CigarrasTontas {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] visit = new int[30];
		int num, cap = 1, aux = 0;
		Boolean flag;
		num = in.nextInt();
		visit[0] = num;
		while (num != 0) {
			num = in.nextInt();
			flag = false;
			aux = cap;
			for (int i = 0; i < cap; i++) {
				if (flag == true)
					visit[i] = 0;
				else if (visit[i] == num) {
					flag = true;
					aux = i;
				}
			}
			if (flag == false)
				visit[cap] = num;
			cap = aux + 1;
		}
		for (int i = 0; i < 30; i++)
			if (visit[i] != 0)
				System.out.println(visit[i]);
	}
}