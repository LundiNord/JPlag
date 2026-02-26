import java.util.*;
 
public class ProblemaSapateiro {
    private static class Sapatos implements Comparable<Sapatos> {
        private int id;
        private double ratio;
 
        private Sapatos(int id, int noDaysOfDelay, int finePerDay) {
            this.id = id;
            ratio = (double) finePerDay / noDaysOfDelay;
        }
 
        public int getId() {
            return id;
        }
 
        @Override
        public int compareTo(Sapatos o) {
            if (o == null) {
                return -1;
            }
 
            int r = Double.compare(o.ratio, ratio);
            if (r != 0) {
                return r;
            } else {
                if (id < o.id) {
                    return -1;
                } else if (id > o.id) {
                    return 1;
                }
            }
 
            return 0;
        }
    }
 
    public static void main(String[] args) {
    	Scanner stdin = new Scanner(System.in);
    	int n = stdin.nextInt();
        List<Sapatos> sapatos = new ArrayList<Sapatos>();
        int d;
        int m;
        
        for(int i = 0; i<n; i++){
        	d = stdin.nextInt();
        	m = stdin.nextInt();
        	sapatos.add(new Sapatos(i+1, d, m));
        }
    	
        Collections.sort(sapatos);
        
        int index = 0;
        
        for(Sapatos sapato: sapatos){
        	index++;
        	if(index < sapatos.size())
        		System.out.print(sapato.getId() + " ");
        	else
        		System.out.println(sapato.getId());
        }
    }
}