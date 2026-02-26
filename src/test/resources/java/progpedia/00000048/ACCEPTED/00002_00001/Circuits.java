import java.util.*;



class Circuits
{
    public static void main(String args[])
    {
	Random r= new Random();
	Scanner stdin = new Scanner(System.in);

	//int m[][]=new int[100][100];

	int N = stdin.nextInt();
	int L = stdin.nextInt();

	Network result= new Network();
	/*
	for(int i=0;i<100;i++)
	    {
		for(int i2=0;i2<100;i2++)
		    if(Math.abs(r.nextInt()%2)==1 && i<i2)
			{
			    m[i][i2]=1;
			    result.add(new Link(i,i2));
			}
	    }

	*/

	for(int i=1;i<=N;i++)
	    result.add(i);
	for(int i=0;i<L;i++)
	    {
		//network[i]=new Link(stdin.nextInt(),stdin.nextInt());
		result.add(new Link(stdin.nextInt(),stdin.nextInt()));
	    }


	

	System.out.println(result.size()+"");


    }
    
}

class Network
{
    
    ArrayList<Pool> pools;

    Network()
    {
	pools= new ArrayList<Pool>();
    }


    // A LINK POINT IS UNIQUE TO EACH POOL ----> there arent repetitions of the same point



    public void add(Link l)
    {
	boolean pointA[]=new boolean[pools.size()];
	boolean pointB[]=new boolean[pools.size()];

	if(pools.size()==0)
	    {
		pools.add(new Pool());
		pools.get(0).add(l.A);
		pools.get(0).add(l.B);
		//System.out.println(pools.get(0));
		return;
	    }


	boolean hasA=false;
	int A_index=-1;
	boolean hasB=false;
	int B_index=-1;


	for(int i=0;i<pools.size();i++)
	    {
		Pool pos=pools.get(i);

		pointA[i]=pos.contains(l.A);
		if(pointA[i]==true)
		    {
			hasA=true;
			A_index=i;
		    }
		pointB[i]=pos.contains(l.B);
		if(pointB[i]==true)
		    {
			hasB=true;
			B_index=i;
		    }
	    }

	if(hasA && hasB==false)
	    {
		//pools.get(A_index).add(l.A);
		pools.get(A_index).add(l.B);
	    }
	else if(hasA==false && hasB)
	    {
		pools.get(B_index).add(l.A);
		//pools.get(B_index).add(l.B);
	    }
	else if(hasA && hasB && A_index!=B_index)
	    {
		Pool p=pools.get(B_index);
		pools.get(A_index).Merge(p);
		pools.remove(B_index);
		//System.out.println(pools.get(A_index));
	    }

	else if(hasA==false && hasB==false)
	    {
		Pool p= new Pool();
		p.add(l.A);
		p.add(l.B);
		pools.add(p);
	    }

	/*
	System.out.println("-----------------------------------");

	for(int i=0;i<pools.size();i++)
	    System.out.println("Pool "+i+": "+pools.get(i));
	*/
	

    }


    public void add(int n)
    {

	int index=-1;
	for(int i=0;i<pools.size();i++)
	    if(pools.get(i).contains(n))
		return;
	Pool p= new Pool();
	p.add(n);
	pools.add(p);
    }





    public int size()
    {
	return pools.size();
    }


}

class Pool
{
    private ArrayList<Integer> points;

    Pool()
    {
	points= new ArrayList<Integer>();
    }

    public boolean contains(int a)
    {
	return points.contains(a);
    }

    public void add(int a)
    {
	points.add(a);
    }

    public int size()
    {
	return points.size();
    }

    public int get(int index)
    {
	return points.get(index);
    }

    public void Merge(Pool a)
    {
	for(int i=0;i<a.size();i++)
	    {
		int n=a.get(i);
		if(!points.contains(n))
		    points.add(n);
	    }
    }

    public void MergeAndNullify(Pool a)
    {
	for(int i=0;i<a.size();i++)
	    {
		int n=a.get(i);
		if(!points.contains(n))
		    points.add(n);
	    }
	a=null;
    }


    public String toString()
    {
	String s= new String();
	for(int i=0;i<points.size();i++)
	    s+=new String(points.get(i)+" ");
	return s;
    }


}


class Link
{
    int A;
    int B;

    Link(int a,int b)
    {
	A=a;
	B=b;
    }

}
