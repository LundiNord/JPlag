import java.util.Arrays;
import java.util.Scanner;

public class Exec10 {
	public static void main(String args[]){
		int n;
		Ticket array[];
		Scanner stdin = new Scanner(System.in);
		n=stdin.nextInt();
		array = new Ticket[n];
		for(int i=0;i<n;i++){
			array[i] = new Ticket(stdin.nextInt(),stdin.nextInt(),i);
		}
		Arrays.sort(array);
		for(int i=0;i<array.length-1;i++){
			System.out.print(array[i].position+1 + " ");
		}
		System.out.println(array[n-1].position+1);
		
	}
}

class Ticket implements Comparable<Ticket>{
	public int days,fine,position;
	public float ratio;
	public Ticket(int days,int fine,int position){
		this.days=days;
		this.fine=fine;
		this.position=position;
		ratio= (float) fine/days;
	}
	
	@Override
    public int compareTo(Ticket p) {
	if (ratio < p.ratio) return 1;
	if (ratio > p.ratio) return -1;
	return 0;
    }
}
