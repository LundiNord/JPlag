import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Seg{
	
	int i1,f;
	
	Seg(int ii, int ff){
		i1=ii; f=ff;
	}
	
}

public class ten {


	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);
		int max=s.nextInt();
		int n=s.nextInt();
	
		Seg segs[]=new Seg[n];
		for(int i=0;i<n;i++)
			segs[i]=new Seg(s.nextInt(), s.nextInt());
		
		Arrays.sort(segs, new Comparator<Seg>() {
			public int compare(Seg n1, Seg n2) {
				int c = n1.i1 - n2.i1;
				if(c==0)
					c= n2.f - n1.f;
				return c;
			}
		});
		
		System.out.println(calc(segs,max));
	}
	
	static int calc(Seg[] segs, int max) {
		
		int end=0;
		int count=0;
		boolean temp=true;
		while(temp){
			temp=false;
			for(int i=0;i<segs.length;i++)
				if(segs[i].i1>=end && segs[i].f<=max){
					count++;
					end=segs[i].f;
					temp=true;
				}
		}
		return count;
}
}

