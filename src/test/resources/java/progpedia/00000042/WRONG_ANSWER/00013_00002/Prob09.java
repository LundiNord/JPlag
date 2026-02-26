import java.util.*;

class Prob09
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	ArrayList<Segment> list = new ArrayList<Segment>();

	int duration = input.nextInt();
	int n = input.nextInt();
	
	
	/*
	*/       
	for (int i=0;i<n;i++) {
	    list.add(new Segment(input.nextInt(), input.nextInt()));
	}
	Collections.sort(list);
	
	for (int i=0;i<n;i++) {
	    System.out.println(list.get(i).startTime + " " + list.get(i).endTime);
	}
	System.out.println(returnMinSegments(list,duration));
    }
    
    public static String solveProblem(ArrayList<Segment> segments, int m) {
	int counter=returnMinSegments(segments,m);
	if (counter<0)
	    return "Impossivel";
	else
	    return counter +"";
    }
    
    public static int returnBestIndex(ArrayList<Segment> segments, Segment segment) {
	int bestEndTime=Integer.MIN_VALUE;
	int bestIndex=-1;
	
	for (int i=0;i<segments.size();i++) {
	    
	    if (segments.get(i).startTime > segment.endTime)
		break;
	    else if (segments.get(i).endTime > bestEndTime) {
		bestEndTime = segments.get(i).endTime;
		bestIndex=i;
	    }
	    
	}
	return bestIndex;
    }
    
    public static int returnMinSegments(ArrayList<Segment> segments, int m) {
	
	Segment s = segments.remove(0);
	int counter=1;
	int index=-1;
	while (s.endTime<m) {
	    index=returnBestIndex(segments,s);
	    System.out.println(segments.get(index).startTime + " " + segments.get(index).endTime);
	    if (s.endTime>=m)
		return counter;
	    if (index>-1) {
		counter++;
		s = segments.remove(index);
	    }
	    else
		return 0;
	}
	return counter;
    }
    
}

class Segment implements Comparable<Segment>
{
    int startTime=0;
    int endTime=0;
    
    Segment() {
	this.startTime=Integer.MAX_VALUE;
	this.endTime=Integer.MAX_VALUE;
    }

    Segment(int startTime, int endTime) {
	this.startTime=startTime;
	this.endTime=endTime;
    }
    
    @Override
	public int compareTo(Segment s) {
	if (this.startTime>s.startTime) 
	    return 1;
	else if (this.startTime==s.startTime) {
	    if (this.endTime > s.endTime)
		return -1;
	}
    return -1;
    }
}
