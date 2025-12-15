import java.io.*;
import java.util.*;

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
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(reader.readLine());
			Task[] tasks = new Task[n];
			for (int i = 0; i < n; i++) {
				String line = reader.readLine();
				String[] nums = line.split(" ");
				tasks[i] = new Task(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), i+1);
			}
			Arrays.sort(tasks, new Comparator<Task>() {
				public int compare (Task t1, Task t2) {
					if (t1.racio != t2.racio) return (int) (t2.racio - t1.racio);
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