import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CoberturaMinima {
	 static class intervalo implements Comparable<intervalo> {
	        int x, y;
	 
	        public intervalo(int s, int e) {
	            x = s;
	            y = e;
	        }
	 
	        public String toString() {
	            return x + " " + y;
	        }
	 
	        public int compareTo(intervalo arg0) {
	            if (x < arg0.x || (x == arg0.x && y > arg0.y))
	                return -1;
	            return 1;
	        }
	    }
	 
	    public static void main(String[] args) {
	        int c;
	        @SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
	        int m = in.nextInt();
	        c = in.nextInt();
	       
	        while (c-- > 0) {
	            
	            PriorityQueue<intervalo> Q = new PriorityQueue<intervalo>();
	            int s = in.nextInt();
	            int e = in.nextInt();
	            while ((s != 0 || e != 0 ) && c--> 0) {
	                Q.add(new intervalo(s, e));
	                s = in.nextInt();
	                e = in.nextInt();
	            }
	            int start = 0;
	            LinkedList<intervalo> taken = new LinkedList<intervalo>();
	            while (start < m) {
	                intervalo best = null;
	                while (!Q.isEmpty() && Q.peek().x <= start) {
	                    intervalo cur = Q.remove();
	                    if (best == null || cur.y > best.y)
	                        best = cur;
	                }
	                if (best == null)
	                    break;
	                else {
	                    start = best.y;
	                    taken.add(best);
	                }
	            }
	            if (start < m)
	                System.out.println(0);
	            else {
	                System.out.println(taken.size());
	                for (intervalo i : taken) {
	                    System.out.println(i);
	                }
	            }
	            if (c != 0)
	                System.out.println();
	        }
	    }
	}