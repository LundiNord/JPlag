import java.io.*;
import java.util.*;

class MyReader {
	static BufferedReader reader;
 	static StringTokenizer tokenizer;

	MyReader (InputStream input) {
		reader = new BufferedReader(new InputStreamReader(input));
		tokenizer = new StringTokenizer("");
	}

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			String line = reader.readLine();
			tokenizer = new StringTokenizer((line.compareTo("\0")==0)?"":line);
		}
		return tokenizer.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}

class Task {
	int dias;
	int multa;
	double racio;
	int pos;

	Task (int d, int m, int p) {
		dias = d;
		multa = m;
		racio = (double) multa/dias;
		pos = p;
	}
}

public class prat11 {
	public static void main (String[] args) {
		try {
			MyReader reader = new MyReader(System.in);
			int n = reader.nextInt();
			Task[] tasks = new Task[n];
			for (int i = 0; i < n; i++) {
				tasks[i] = new Task(reader.nextInt(), reader.nextInt(), i+1);
			}
			Arrays.sort(tasks, new Comparator<Task>() {
				public int compare (Task t1, Task t2) {
					if (t2.racio > t1.racio) return 1;
					else if (t2.racio < t1.racio) return -1; //bem melhor que retornar (int) diferenca
					return t1.pos - t2.pos;
				}
			});
			for (int i = 0; i < n-1; i++) {
				System.out.print(tasks[i].pos + " ");
			}
			System.out.print(tasks[n-1].pos + "\n");
		}
		catch (Exception e) {}
	}
}