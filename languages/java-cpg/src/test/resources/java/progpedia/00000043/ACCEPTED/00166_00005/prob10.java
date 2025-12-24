import java.util.*;
class Shoe implements Comparable<Shoe>{
    int index;
    int d;
    int m;
    float ratio;
    Shoe(int index, int d, int m, float ratio)
    {
	this.index=index;
	this.d=d;
	this.m=m;
	this.ratio=ratio;
    }
   
    public int compareTo(Shoe a)
    {
	if(ratio<a.ratio)
	    return -1;
	else if(ratio>a.ratio)
	    return +1;
	else
	    return 0;
    }
}
class prob10{
    public static void main(String args[])
    {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	Shoe[] vetor = new Shoe[n];
	for(int i=0;i<n;i++)
	    {
		int d = in.nextInt();
		int m = in.nextInt();
		vetor[i] = new Shoe(i+1,d,m,(float)d/m);
	    }
	Arrays.sort(vetor);

	for(int i=0;i<n;i++)
	    {
		if(i==n-1)
		    System.out.println(vetor[i].index);
		else
		    System.out.print(vetor[i].index + " " );
	    }

    }
}
