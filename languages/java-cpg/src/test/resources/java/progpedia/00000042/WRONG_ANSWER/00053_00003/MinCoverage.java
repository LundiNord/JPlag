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
    	else
    		return 0;
    }
}
class MinCoverage{

	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		int m = input.nextInt();
		int n = input.nextInt();

		Segment[] a = new Segment[n];

		for(int i=0;i<n;i++)
			a[i] = new Segment(input.nextInt(),input.nextInt());

		Arrays.sort(a);

		Segment[] p = new Segment[n];

		int i = 1;
		int j = 0;
		p[j] = a[j];
		int end = p[j].d;
		int count = 1;

		while(end<m){
			if(a[i].l<=end){
				if(a[i].l == p[j].l)
					if(a[i].r>p[j].r){
						end -= p[j].d;
						p[j] = a[i];
						end += p[j].d;
					}
				if(a[i].r>end){
					j++;
					p[j] = a[i];
					end += (a[i].r-end);
					count++;
				}
			}
			i++;
		}

		System.out.println(count);
	}
}