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
        
        //Para o intervalo [0;M]
        while (end < m) {
            
            int maxend = Integer.MIN_VALUE;
            
            for (int i = 0; i < n; i++) {
                
                //Se houverem eventos com inicio antes ou em end atual
                if (e[i].start <= end) {
                    
                    //Estender o intervalo [0;end] até ao máximo valor encontrado
                    if (e[i].end > maxend) maxend = e[i].end;
                }
            }
            counter++;
            end = maxend;
            //System.out.println("iteration!" + end);
        }
        System.out.println(counter);
        
        
    }
}