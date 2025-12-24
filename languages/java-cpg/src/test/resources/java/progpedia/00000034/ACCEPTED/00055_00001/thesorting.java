import java.util.*;

class thesorting
{
    public static void main(String args[])
    {
	Scanner stdin=new Scanner(System.in);
	Btree t=new Btree();
	int N=stdin.nextInt();
	for(int i=0;i<N;i++)
	    t.add(new Bnode(stdin.nextInt()));

	t.print();
	System.out.println();
    }
}

class Bnode
{
    int value;
    //int copies;
    Bnode left;
    Bnode right;

    Bnode(int v)
    {
	value=v;
	//copies=0;
	left=null;
	right=null;
    }
}


class Btree
{
    Bnode root;
    boolean isfirst;

    Btree()
    {
	root=null;
	isfirst=true;
    }

    public void add(Bnode n)
    {
	if(root==null)
	    root=n;
	else if(n.value<=root.value)
	    root.left=add(root.left,n);
	else
	    root.right=add(root.right,n);

    }

    private Bnode add(Bnode node,Bnode n)
    {
	if(node==null)
	    {
		return n;
	    }
	else if(n.value<=node.value)
	    {
		node.left=add(node.left,n);
		return node;
	    }
	else
	    {
		node.right=add(node.right,n);
		return node;
	    }
    }

    public void print()
    {
	print(root);
	isfirst=true;
    }

    private void print(Bnode n)
    {
	if(n!=null)
	    {
		print(n.left);
		if(isfirst)
		    {
			System.out.print(n.value);
			isfirst=false;
		    }
		else
		    System.out.print(" "+n.value);
		print(n.right);
	    }
    }

}