import java.util.*;

public class CoberturaMinima {
    private static int segmentSize;
    private static Segment[] segments;
	
    public static void main(String[] args) {
	readInput();
	dataProcessing();
    }

    private static void readInput() {
	Scanner in = new Scanner(System.in);
	segmentSize = in.nextInt();
	int n = in.nextInt();
	segments = new Segment[n];

	for (int i=0; i<n; i++) {
	    segments[i] = new Segment(in.nextInt(), in.nextInt());
	}
    }
	
    private static void dataProcessing() {
	sortSegments();
	int end = 0;
	int maxLocalEnd;
	int i = 0;
	int counter = 0;
	
	while (end < segmentSize) {
	    maxLocalEnd = end;
	    if (segments[i].start <= end) {
		int j;
		for (j=i; (j<segments.length) && (segments[j].start<=end); j++) {
		    if (segments[j].end > maxLocalEnd) { 
			maxLocalEnd = segments[j].end;
		    } 
		}
		end = maxLocalEnd;
		counter++;
		i = j;
	    }
	}
	writeOutput(counter);
    }
	
    private static void sortSegments() {
	Arrays.sort(segments, new Comparator<Segment>() {
		public int compare(Segment s1, Segment s2) {
		    if (s1.start == s2.start) {
			return s1.end - s2.end;
		    }
		    return s1.start - s2.start;
		}
	    });
    }

    private static void writeOutput(int c) {
	System.out.println(c);
    }
}

class Segment {
    int start;
    int end;
    
    Segment(int s, int e) {
	start = s;
	end = e;
    }
}
