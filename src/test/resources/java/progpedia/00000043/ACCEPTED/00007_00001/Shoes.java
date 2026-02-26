import java.util.*;


class Shoes
{
    public static void main(String args[])
    {
	/*Fraction a= new Fraction(0,4,3);
	  a.Normalize();
	  Fraction b= new Fraction(1,1000,1);
	  b.Normalize();
	  System.out.println(a.compareTo(b));

	  System.out.println(a.num+"/"+a.den);*/
	
	Scanner stdin = new Scanner(System.in);
	int N=stdin.nextInt();
	Fraction list[]=new Fraction[N];

	int den;
	int num;

	for(int i=0;i<N;i++)
	    {
		den=stdin.nextInt();
		num= stdin.nextInt();
		list[i]=new Fraction(i,num,den);
		list[i].Normalize();
		//System.out.println(" "+list[i].num+"/"+list[i].den);
	    }

	
	Arrays.sort(list);


	for(int i=0;i<N;i++)
	    if(i==0)
		{
		    System.out.print(""+(list[i].index+1));
		    //System.out.print(list[i].num+"/"+list[i].den);
		}
	    else
		{
		    //System.out.print(" "+list[i].num+"/"+list[i].den);
		    System.out.print(" "+(list[i].index+1));
		}
	
	System.out.println();
    }

    



}


class Fraction implements Comparable<Fraction>
{
    
    int num;
    int den;
    int index;

    Fraction(int i, int n,int d)
    {
	index=i;
	num=n;
	den=d;
    }
    public void Normalize()
    {
	int div=new Fraction(0,0,0).gcd(num,den);
	num/=div;
	den/=div;
    }

    public int gcd(int a,int b)
    {
	if(b==0)
	    return a;
	return gcd(b,a%b);
    }


    @Override
    public int compareTo(Fraction a)
    {
	if(num==a.num && den==a.den)
	    {
		if(index<a.index)
		    return -1;
		else
		    return 1;
	    }
	else
	    {
	
		if((double)num/(double)den > (double)a.num/(double)a.den)
		    return -1;
		else return 1;
	    }
    }

}