import java.util.*;

public class ProbC {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int locais[] = new int[30];
		int local = in.nextInt();
		int index = 0;

		while (local != 0) {
			for (int i = 0; i < index; i++) {
				if (local == locais[i])
					index = i;
			}
			locais[index]=local;
			local = in.nextInt();
			index++;
		}

		for (int i = 0; i < index; i++) {
			System.out.println(locais[i] + "\n");
		}
	}

}
