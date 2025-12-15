import java.util.*;

class Order {
	int dias;
	int custo;
	Integer index;
	
	Order(int dias, int custo, int index) {
		this.dias = dias;
		this.custo = custo;
		this.index = index;
	}
}

class intcompare implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
    	Double const1 = (Double) (o1.custo *1.0/ o1.dias);
    	Double const2 = (Double) (o2.custo *1.0/ o2.dias);
    	if (const1.equals(const2)) {
    		return o2.index.compareTo(o1.index);
    	}
        return const1.compareTo(const2);
    }
}

class Prob11 {
	static int n;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		if (n==0) return;
		Order[] orders = new Order[n];
		
		for(int i=0; i<n; i++) {
			orders[i] = new Order(in.nextInt(), in.nextInt(), i+1);
		}
		
		getMinCost(orders);
	}
	
	private static void getMinCost(Order orders[]) {
		Arrays.sort(orders, new intcompare());
		
		System.out.print(orders[n-1].index);
		
		for(int i=n-2; i>=0; i--) {
			System.out.print(" " + orders[i].index);
		}
	}
}