import java.util.*;

public class coverage {


	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int end_of_seg = stdin.nextInt();
		int number_event = stdin.nextInt();
		Event events[] = new Event[number_event];
		int occupied_until = 0;
		int event_used = 0;
		
		for(int i = 0; i<number_event;i++){
			events[i] = new Event(stdin.nextInt(), stdin.nextInt());
		}

		Arrays.sort(events);
		
		for(int j = 0; j < number_event; j++){
			if(events[j].start >= occupied_until && events[j].end <= end_of_seg){
				occupied_until = events[j].end;
				event_used++;
			}

		}
		System.out.println(event_used);
	}
}

class Event implements Comparable<Event>{

	int start;
	int end;

	Event(int start, int end){
		this.start=start;
		this.end=end;
	}


	
	public int compareTo(Event compareEvent){
		Event comparing = ((Event) compareEvent);
		if(this.start==comparing.start)
			return comparing.end - this.end;
		return this.start - comparing.start;
	}

}