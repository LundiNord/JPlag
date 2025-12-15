//package PraticeExercises;

import java.util.Scanner;
import java.util.PriorityQueue;

public class Problema9 {


	private static Scanner input;

	public static class Segment  implements Comparable<Segment> {
		int start, end;
		//construtor do segmento
		public Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		// por causa da fila de prioridade implementacao do compareTo
		public int compareTo(Segment segment) {
			if (this.start < segment.start){
				return -1;
			} else if (this.start == segment.start && this.end > segment.end){
				return -1;
			}
			return 1;
		}
	}

	public static void main(String[] args) {
		input = new Scanner(System.in);

		int m = input.nextInt();
		int segmentNr = input.nextInt();
		int count = 0;

		PriorityQueue<Segment> priorityQ = new PriorityQueue<Segment>();

		for (int i = 0; i < segmentNr; i++) {
			int li = input.nextInt();
			int ri = input.nextInt();
			priorityQ.add(new Segment(li, ri));
		}
		int start = 0;

		while (start < m) {
			Segment best = null;
			while (!priorityQ.isEmpty() && priorityQ.peek().start <= start) {
				Segment current = priorityQ.remove();
				if (best == null) {
					best = current;
				} else if(current.end > best.end){
					best = current;
				}
			} 
			
			start = best.end;
			count++;
		}
		
	
		System.out.println(count);
	}
}