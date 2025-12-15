import java.util.*;
class Segment implements Comparable<Segment>{
	int l,r,d;

	Segment(int l, int r){
		this.l = l;
		this.r = r;
		d = r - l;
	}

	@Override
    public int compareTo(Segment t){
    	if(l<t.l)
    		return -1;
    	else if(l>t.l)
    		return 1;
    	else{
    		if(d<t.d)
    			return 1;
    		else if(d>t.d)
    			return -1;
    		else
    			return 0;
    	}
    }
}
class MinCoverage{

	public static void main(String[] args){
		int j;

		Scanner input = new Scanner(System.in);

		int m = input.nextInt();
		int n = input.nextInt();

		Segment[] a = new Segment[n];

		for(int i=0;i<n;i++)
			a[i] = new Segment(input.nextInt(),input.nextInt());

		Arrays.sort(a);

		Segment[] p = new Segment[n];

		int end = 0;
		int i = 0;
		int maxR=0;
		int count=0;

		while(end<m){
			for(j=i;a[j].l<=end;j++){
				if(a[j].r>maxR){
					maxR = a[j].r;
				}
				if(j==n-1)
					break;
			}
			i=j;
			end = maxR;
			count++;
			maxR = 0;
		}

		System.out.println(count);
	}
}