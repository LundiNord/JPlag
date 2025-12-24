import java.util.*;


class Proteins
{
    public static void main(String args[])
    {
	Scanner stdin = new Scanner(System.in);

	int N=stdin.nextInt();

	int E=stdin.nextInt();

	Link links[]=new Link[E];

	for(int i=0;i<E;i++)
	    links[i]=new Link(stdin.nextInt(),stdin.nextInt());
	
	SolveThisForMe(N,E,links);
    }
    public static void SolveThisForMe(int N,int E,Link links[])
    {
	ListCover adj[] = new ListCover[N];

	for(int i=0;i<N;i++)
	    adj[i]=new ListCover();

	int m[][]=new int[N][N];

	for(int i=0;i<E;i++)
	    {
		adj[links[i].a-1].list.add(links[i].b-1);
		adj[links[i].b-1].list.add(links[i].a-1);
	    }
	
	for(int i=0;i<N;i++)   //Para todos os nos
	    {
		//System.out.println("In Relation to Vertex "+(i+1));
		LinkedList<Info> list = new LinkedList<Info>();
		list.add(new Info(1,i));   
		int visited[]=new int[N];
		visited[i]=1;

		//-------------------------------------------------------------
		while(list.size()!=0)
		    {
			Info first=list.getFirst();
			
			//System.out.println("first="+(first+1));
			adj[first.n].list.setRef(0);

			//System.out.println("\nIteration="+iteration);
			//System.out.println();
			while(!adj[first.n].list.isLast())
			    {
				int n=adj[first.n].list.getRef();
				//System.out.println("visited["+(n+1)+"]="+visited[n]);
				if(visited[n]==0)
				    {
					list.add(new Info(first.iteration+1,n));
					//System.out.println("visited["+(n+1)+"]=1  m["+(i+1)+"]["+(n+1)+"]="+iteration+"  m["+(n+1)+"]["+(i+1)+"]="+iteration);
					visited[n]=1;
					m[i][n]=first.iteration;
					m[n][i]=first.iteration;
				    }
				

				adj[first.n].list.incRef();
			    }

			int lastadj=adj[first.n].list.getRef();
			//System.out.println("visited["+(lastadj+1)+"]="+visited[lastadj]);
			if(visited[lastadj]==0)
			    {
				list.add(new Info(first.iteration+1,lastadj));
				//System.out.println("visited["+(lastadj+1)+"]=1  m["+(i+1)+"]["+(lastadj+1)+"]="+iteration+"  m["+(lastadj+1)+"]["+(i+1)+"]="+iteration);
				visited[lastadj]=1;
				m[i][lastadj]=first.iteration;
				m[lastadj][i]=first.iteration;
			    }


			list.removeFirst();
			//iteration++;
			/*
			System.out.print("[");
			for(int i2=0;i2<list.size();i2++)
			    if(i2==list.size()-1)
				System.out.print(list.get(i2)+1);
			    else
				System.out.print((list.get(i2)+1)+",");
			System.out.println("]");
			*/
		    }

		//System.out.println();

		//-------------------------------------------------------------

	    }
	//PrintMatrix(m);
	
	int max[]=new int[N];
	for(int i=0;i<N;i++)
	    {
		max[i]=max(m[i]);
	    }

	int diameter=max(max);
	int radius=min(max);
	System.out.println(diameter);
	System.out.println(radius);

	LinkedList<Integer> central=new LinkedList<Integer>();
	LinkedList<Integer> perifral=new LinkedList<Integer>();

	for(int i=0;i<max.length;i++)
	    {
		if(max[i]==diameter)
		    central.add(i+1);
		else if(max[i]==radius)
		    perifral.add(i+1);
	    }

	while(perifral.size()!=0)
	    {
		if(perifral.size()==1)
		    System.out.println(perifral.getFirst());
		else
		    System.out.print(perifral.getFirst()+" ");
		perifral.removeFirst();
	    }

	while(central.size()!=0)
	    {
		if(central.size()==1)
		    System.out.println(central.getFirst());
		else
		    System.out.print(central.getFirst()+" ");
		central.removeFirst();
	    }

	    


    }
    public static int min(int seq[])
    {
	int min=seq[0];
	for(int i=0;i<seq.length;i++)
	    if(min>seq[i])
		min=seq[i];
	return min;
    }

    public static int max(int seq[])
    {
	int max=0;
	for(int i=0;i<seq.length;i++)
	    if(seq[i]>max)
		max=seq[i];
	return max;
    }



    public static void PrintMatrix(int m[][])
    {
	for(int i=0;i<m.length;i++)
	    for(int i2=0;i2<m[0].length;i2++)
		{
		    if(i2==m[0].length-1)
			System.out.println(m[i][i2]);
		    else
			System.out.print(m[i][i2]+" ");
		}

    }

}
class Info
{
    int iteration;
    int n;
    Info(int i,int N)
    {
	iteration=i;
	n=N;
    }
}


class ListCover
{
    MyLinkedList<Integer> list;
    ListCover()
    {
	list= new MyLinkedList<Integer>();
    }

    public String toString()
    {
	String s = new String();
	list.setRef(0);
	while(!list.isLast())
	    {
		s+=(list.getRef()+1)+" ";
		list.incRef();
	    }
	s+=(list.getRef()+1)+"\n";
	return s;
    }
}


class Link
{
    int a;
    int b;
    Link(int A,int B)
    {
	a=A;
	b=B;
    }
}


class Node<T>
{
    T val;
    Node<T> prev;
    Node<T> next;

    Node(T v)
	{
	    val=v;
	    next=null;
	    prev=null;
	}

    Node(T v,Node<T> p,Node<T> n)
	{
	    val=v;
	    prev=p;
	    next=n;
	}

}


class MyLinkedList<T>
{
    Node<T> first;
    Node<T> last;
    int size;

    Node<T> ref;

    MyLinkedList()
	{
	    first=null;
	    last=null;
	    size=0;

	    ref=null;
	}

    public int size()
    {
	return size;
    }


    public void addFirst(T val)
    {
	if(size==0)
	    {
		first=new Node<T>(val);
		last=first;
	    }
	else
	    {
		first.prev=new Node<T>(val,null,first);
		first=first.prev;
	    }
	size++;
    }

    public void addLast(T val)
    {
	if(size==0)
	    {
		first=new Node<T>(val);
		last=first;
		
	    }
	else
	    {
		
		last.next=new Node<T>(val,last,null);
		last=last.next;
	    }
	size++;
    }


    public void removeFirst()
    {
	if(size==0)
	    throw new IndexOutOfBoundsException();
	
	else if(size==1)
	    {
		first=null;
		last=null;
	    }
	
	else
	    {
		first=first.next;
		first.prev=null;
	    }

	size--;
    }

    public void removeLast()
    {
	if(size==0)
	    throw new IndexOutOfBoundsException();
	else if(size==1)
	    {
		first=null;
		last=null;
	    }
	
	else
	    {
		last=last.prev;
		last.next=null;
	    }
	size--;
    }

    public T get(int index)
    {
	if(index<0 || index>=size)
	    throw new IndexOutOfBoundsException();
	else if(size==1)
	    {
		return first.val;
	    }
	else
	    {
		if(index<=size/2)
		    {
			Node<T> p=first;
			for(int i=0;i<size;i++)
			    {
				if(i==index)
				    return p.val;
				else
				    {
					p=p.next;
				    }
			    }
		    }
		else
		    {
			Node<T> p=last;
			for(int i=size-1;i>=0;i--)
			    {
				if(i==index)
				    return p.val;
				else
				    {
					p=p.prev;
				    }
			    }
		    }
	    }
	return null;
    }

    public void remove(int index)
    {
	if(index<0 || index>=size)
	    throw new IndexOutOfBoundsException();
	else if(index==0)
	    {
		removeFirst();
	    }
	else if(index==size-1)
	    {
		removeLast();
	    }
	else
	    {
		if(index<=size/2)
		    {
			Node<T> p=first;
			for(int i=0;i<size;i++)
			    {
				if(i==index)
				    {
					p.prev.next=p.next;
					p.next.prev=p.prev;
					size--;
					break;
				    }
				else
				    {
					p=p.next;
				    }
			    }
		    }
		else
		    {
			Node<T> p=last;
			for(int i=size-1;i>=0;i--)
			    {
				if(i==index)
				    {
					p.prev.next=p.next;
					p.next.prev=p.prev;
					size--;
					break;
				    }
				else
				    {
					p=p.prev;
				    }
			    }
		    }
	    }
	
    }


    public int indexOf(T val)
    {
	if(size==0)
	    return -1;
	else
	    {
		Node<T> p=first;
		for(int i=0;i<=size-1;i++)
		    {
			if(p.val.equals(val))
			    return i;
			else
			    p=p.next;
		    }
	    }
	return -1;
    }

    public void add(T val,int index)
    {
	if(index<0 || index>=size)
	    throw new IndexOutOfBoundsException();
	else if(index==0)
	    {
		addFirst(val);
	    }
	else if(index==size-1)
	    {
		addLast(val);
	    }
	else
	    {
		if(index<=size/2)
		    {
			Node<T> p=first;
			for(int i=0;i<size;i++)
			    {
				if(i==index)
				    {
					Node <T> subs=new Node<T>(val,p.prev,p);
					p.prev.next=subs;
					p.prev=subs;
					
					size++;
					break;
				    }
				else
				    p=p.next;
			    }
		    }
		else
		    {
			Node<T> p=last;
			for(int i=size-1;i>=0;i--)
			    {
				if(i==index)
				    {
					Node <T> subs=new Node<T>(val,p.prev,p);
					p.prev.next=subs;
					p.prev=subs;

					size++;
					break;
				    }
				else
				    p=p.prev;
			    }
		    }
	    }

	
    }

    public void add(T val)
    {
	addLast(val);
    }




    public void clear()
    {
	first=null;
	last=null;
	size=0;
    }



    public void setRef(int index)
    {
	if(index<0 || index>=size)
	    throw new IndexOutOfBoundsException();
	else if(index==0)
	    {
		ref=first;
	    }
	else if(index==size-1)
	    {
		ref=last;
	    }
	else
	    {
		if(index<=size/2)
		    {
			Node<T> p=first;
			for(int i=0;i<size;i++)
			    {
				if(i==index)
				    {
					ref= p;
					break;
				    }
				else
				    {
					p=p.next;
				    }
			    }
		    }
		else
		    {
			Node<T> p=last;
			for(int i=size-1;i>=0;i--)
			    {
				if(i==index)
				    {
					ref= p;
					break;
				    }
				else
				    {
					p=p.prev;
				    }
			    }
		    }
	    }

    }

    public void incRef()
    {
	if(ref==last)
	    throw new IndexOutOfBoundsException();
	else
	    ref=ref.next;
    }
    public void decRef()
    {
	if(ref==first)
	    throw new IndexOutOfBoundsException();
	else
	    ref=ref.prev;
    }

    public T getRef()
    {
	return ref.val;
    }

    public boolean isLast()
    {
	if(ref==last)
	    return true;
	else
	    return false;
    }

    public boolean isFirst()
    {
	if(ref==first)
	    return true;
	else
	    return false;
    }

    public String toString()
    {
	String s =new String("[");
	Node<T> p=first;


	for(int i=0;i<size && p!=null;i++)
	    {

		if(i==size-1)
		    s+=" "+p.val.toString();
		else
		    s+=" "+p.val.toString()+",";

		p=p.next;
	    }

	return s+"]";
    }
    public void print()
    {
	Node<T> p=first;
	System.out.print("[");
	for(int i=0;i<size && p!=null;i++)
	    {
		if(i==size-1)
		    System.out.print(" "+p.val.toString());
		else
		    System.out.print(" "+p.val.toString()+",");
		p=p.next;
	    }
	System.out.println("]");
    }

}
