import java.util.*;

class Event {
        
        public int start;
        public int end;
        
        Event(int s, int e) {
            start = s;
            end = e;
        }
    
        /*@Override
        public int compareTo(Event e) {
        if (start < e.start) return +1;
        if (start > e.start) return -1;
        return start.compareTo(e.start);
        }*/
}

class DAA009 {

    static int m, n;//, s[], e[];
    
    /*static void calcintervals(int m, int s[], int e[], int n) {
        
        int end = 0;
        
        //System.out.println(times);
    }*/
    
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
            
            //System.out.println(j);
            
            if (e[j].start <= end) {
                if (e[j].end >= e[j+1].end) { counter++;
                    //System.out.println("[" + e[j].start + "," + e[j].end + "]");
                    end = e[j].end; }
                if (e[j].end < e[j+1].end) { counter++;
                    //System.out.println("[" + e[j].start + "," + e[j].end + "]");
                    end = e[j+1].end; }
                
            }
            
        }
        
        System.out.println(counter);
        
        //for (int i=0; i<n; i++) System.out.println(e[i].start + " " + e[i].end);
        
        //int s [] = new int [n];
        //int e [] = new int [n];
        
        /*for (int i=0; i<n; i++) {
            s[i] = in.nextInt();
            e[i] = in.nextInt();
        }*/
        
        //calcintervals(m,s,e,n);
    }
}