import java.util.*;

class Event {
        
        public int start;
        public int end;
        public int size;
        
        Event(int s, int e) {
            start = s;
            end = e;
            size = e-s;
        }
}

class DAA009 {

    static int m, n;
    
    public static void main (String args[]) {
        
        Scanner in = new Scanner(System.in);
        
        int m = in.nextInt();
        int n = in.nextInt();
        
        Event e[] = new Event [n];
        
        for (int i=0; i<n; i++) {
        
            e[i] = new Event(in.nextInt(),in.nextInt());
        }
        
        Arrays.sort(e, new Comparator<Event>() {
         
            public int compare(Event e1, Event e2) {
                if (e1.start <= e2.start) return -1;
                if (e1.start > e2.start) return +1;
                else return 0;
            }
        });
        
        int end = 0;
        int counter = 0;
        
        for (int j=0; j<n && end < m; j++) {
            
            if (e[j].start <= end) {
                 
                int bestend = e[j].end;
                
                //System.out.println("["+e[j].start+","+e[j].end+"]");
                if (e[j].end > end) {
                    
                    counter++;
                    
                    if (bestend < e[j+1].end) end = e[j+1].end;
                    else end = e[j].end;
                }
                    
            }
            
        }
        
        System.out.println(counter);
        
        
    }
}