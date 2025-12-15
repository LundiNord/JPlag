

import java.awt.Point;
import java.util.*;

public class sapateiro {
	static int size;

	static final Comparator<DayCost> DAYCOST_ORDER = new Comparator<DayCost>() {
		public int compare(DayCost e1, DayCost e2) {
			if(e1.getRatioDaysCost() == e2.getRatioDaysCost())
				return (int)(e1.getDays() - e1.getDays());
			else
				return (int)(e2.getRatioDaysCost() - e1.getRatioDaysCost());
		}
	};

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		size = input.nextInt();

		ArrayList<DayCost> points = new ArrayList<DayCost>(size);

		for (int i = 0; i < size; i++) {
			points.add(new DayCost(i + 1, input.nextInt(), input.nextInt()));
		}
		
		Collections.sort(points, DAYCOST_ORDER);
		
		for(int i=0; i < points.size();i++)
		{
			System.out.print(""+points.get(i).getIndex());
			if(i < points.size() - 1)
			{
				System.out.print(" ");
			}
		}
		//System.out.println(points);

	}// End of main
}

class DayCost {
	private int index;
	private double days;
	private double cost;
	private double ratioDaysCost;

	public DayCost(int index, double days, double cost) {
		this.index = index;
		this.days = days;
		this.cost = cost;
		this.ratioDaysCost = this.cost / this.days;
	}

	public int getIndex() {
		return index;
	}

	public double getDays() {
		return days;
	}

	public double getCost() {
		return cost;
	}

	public double getRatioDaysCost() {
		return ratioDaysCost;
	}

	public String toString() {
		return "" + this.index + " " + this.days + " " + this.cost + " "
				+ this.ratioDaysCost;
	}
}
