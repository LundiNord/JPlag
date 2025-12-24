//Com ajuda da Internet

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class Cobertura {

    static class Line implements Comparable<Line> {

        int start, end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Line arg0) {
            if (start < arg0.start || (start == arg0.start && end > arg0.end)) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        PriorityQueue<Line> Q = new PriorityQueue<Line>();
        for (int i = 0; i < n; i++) {
            int li = in.nextInt();
            int ri = in.nextInt();
            Q.add(new Line(li, ri));
        }

        int start = 0;
        LinkedList<Line> taken = new LinkedList<Line>();
        while (start < m) {
            Line best = null;
            while (!Q.isEmpty() && Q.peek().start <= start) {
                Line cur = Q.remove();
                if (best == null || cur.end > best.end) {
                    best = cur;
                }
            }
            if (best == null) {
                break;
            } else {
                start = best.end;
                taken.add(best);
            }
        }

        if (start < m) {
            System.out.println(0);
        } else {
            System.out.println(taken.size());
        }
        if (m != 0) {
            System.out.println();
        }
    }
}
