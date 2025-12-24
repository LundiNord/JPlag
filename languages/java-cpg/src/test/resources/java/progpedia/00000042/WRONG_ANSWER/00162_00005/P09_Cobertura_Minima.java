
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Herlander Silva
 */
class Segment implements Comparable<Segment> {

    int l;
    int r;

    public Segment(int l, int r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public int compareTo(Segment n) {
        if (this.l - n.l == 0) {
            return n.r - this.r;
        }
        return this.l - n.l;
    }
}

public class P09_Cobertura_Minima {

    static Segment[] segment;

    static void printArray(Segment[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.println(i + ": " + v[i].l + " " + v[i].r);
        }
    }

    static int minCover(int cover, Segment[] segments) {
        int count = 0;
        int end = 0;
        for (int i = 0; i < segments.length - 1; i++) {
            end = segments[i].r;
//            System.out.println(end);
            count++;
            for (int j = i + 1; end >= segments[j].l && j < segments.length - 1; j++) {
                if (end <= segments[j].r) {
                    i = j - 1;
                }
            }
        }
        return count;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner stdin;
        stdin = new Scanner(System.in);
//        @SuppressWarnings("resource")
//        stdin = new Scanner(new File("Problemas/P09_Cobertura_Minima.in"));
        int cover = stdin.nextInt();
        int dim = stdin.nextInt();

        segment = new Segment[dim];
        int idx = 0;

        while (idx < dim) {
            segment[idx] = new Segment(stdin.nextInt(), stdin.nextInt());
            idx++;
        }

        Arrays.sort(segment);
//        printArray(segment);

        System.out.println(minCover(cover, segment));

    }

}
