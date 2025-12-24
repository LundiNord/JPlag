import java.util.*;


class Cover
{
    public static void main(String args[])
    {
	Scanner stdin= new Scanner(System.in);
	int M=stdin.nextInt();
	int N=stdin.nextInt();
	Line lines[]=new Line[N];

	for(int i=0;i<N;i++)
	    lines[i]=new Line(stdin.nextInt(),stdin.nextInt());

	Arrays.sort(lines);
	System.out.println(MinCoverage(lines,M));
    }


    public static int MinCoverage(Line array[],int M)
    {

	int position=0;
        int iteration=0;
	int start=0;

	while(start<M)
	    {
		if(start==0)
		    {
			start=array[0].H;
			iteration++;
		    }
		else
		    {
			int high=start;
			int i=position;
			for(;i<array.length && (array[i].L<=start) ;i++)
			    {
				if(array[i].H>high)
				    high=array[i].H;
			    }
			position=i-1;
			start=high;;
			iteration++;
		    }
	    }

	return iteration;
    }



}


class Line implements Comparable<Line>
{
    int L;
    int H;

    Line(int l,int h)
    {
	L=l;
	H=h;
    }

    @Override
	public int compareTo(Line a)
	{
	    if(L<a.L)
		return -1;
	    else if(L>a.L)
		return 1;
	    else
		{
		    if(H-L < a.H-a.L)
			return 1;
		    else if(H-L > a.H-a.L)
			return -1;
		    else return 0;
		}
	}

}