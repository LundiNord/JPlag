import java.util.*;

class Order {
    int duration, fee, index;
    float ratio;
    
    Order (int d, int f, int i) {
        
        duration = d;
        fee = f;
        index = i;
        ratio = (float)d/f;
    }
}

class DAA010 {

    public static void main (String args[]) {
        
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        
        Order o[] = new Order [n];
        
        for (int i=0; i<n; i++) o[i] = new Order(in.nextInt(),in.nextInt(),i+1);
        
        Arrays.sort(o, new Comparator<Order>() {
         
            public int compare(Order o1, Order o2) {
                if (o1.ratio <= o2.ratio) return -1;
                if (o1.ratio > o2.ratio) return +1;
                else return 0;
            }
        });
        
        for (int i=0; i<n; i++) System.out.print(o[i].index + " ");
    }

}