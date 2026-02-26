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
	int i = 0;
	int counter = 0;
	
	while (end < segmentSize) {
	    if (segments[i].start <= end) {
		if (i < segments.length-1) {
		    if (segments[i+1].start == segments[i].start) {
			i++;
			continue;
		    }
		}
		if (segments[i].end > end) { 
		    end = segments[i].end;
		    counter++;
		}
		i++;
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
