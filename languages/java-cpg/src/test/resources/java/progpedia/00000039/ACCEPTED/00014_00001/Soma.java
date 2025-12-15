import java.util.*;

class Soma
{
    public static void main(String args[])
    {
	//Random r=new Random();
	Scanner stdin=new Scanner(System.in);
	int N=stdin.nextInt();
	//int N=1000;
	int N_array[]=new int[N];
	for(int i=0;i<N;i++)
	    N_array[i]=stdin.nextInt();
	    // N_array[i]=Math.abs(r.nextInt()%1000000) +1;


	int P=stdin.nextInt();
	//int P=1000;
	int P_array[]=new int[P];
	for(int i=0;i<P;i++)
	    P_array[i]=stdin.nextInt();
	    //P_array[i]=Math.abs(r.nextInt()%1000000) +1;

	int i3=0;
	int somas[]=new int[(N*(N-1))/2];
	for(int i=0;i<N-1;i++)
	    {
		for(int i2=i+1;i2<N;i2++)
		    {
			somas[i3]=N_array[i]+N_array[i2];
			i3++;
		    }
	    }
	
	Arrays.sort(somas);

	/*for(int i=0;i<somas.length;i++)
	    System.out.print(" "+somas[i]);
	    System.out.println();*/

	//Info info;
	for(int i=0;i<P;i++)
	    System.out.println(bsearch(somas,0,somas.length-1,P_array[i]));
	    //info=bsearch(somas,0,somas.length-1,P_array[i]);
	



    }
    public static Info bsearch(int array[],int ini,int fim,int P)
    {
	int middle=ini+(fim-ini)/2;

	if(array[middle]==P || array[ini]==P || array[fim]==P)
	    return new Info(P);
	else if(ini==fim || ini>fim)
	    return rangeSearch(array,ini,P);
	else if(P<array[0])
	    return new Info(array[0]);
	else if(P>array[array.length-1])
	    return new Info(array[array.length-1]);
	else
	    {
		int ini_dif=array[ini]-P;
		int fim_dif=array[fim]-P;
		int mid_dif=array[middle]-P;

		if(Potential(ini_dif,mid_dif)<=Potential(mid_dif,fim_dif))
		    return bsearch(array,ini,middle-1,P);
		else
		    return bsearch(array,middle+1,fim,P);
	    }

    }

    public static Info rangeSearch(int array[],int middle,int P)   // VERY DOUBTFUL
    {
	int dif=Math.abs(array[middle]-P);
	
	if(P>array[middle])
	    {
		for(int i=middle+1;i<array.length && Math.abs(P-array[i])<=dif;i++)
		    if(Math.abs(array[i]-P) < Math.abs(array[middle]-P))
			{
			return rangeSearch(array,i,P);
			}
			else if(array[i]!=array[middle])
			return new Info(array[middle],array[i]);
	    }

	else if(P<array[middle])
	    {
		for(int i=middle-1;i>=0 && Math.abs(P-array[i])<=dif;i--)
		    if(Math.abs(array[i]-P) < Math.abs(array[middle]-P))
			{
			    //System.out.println("Found a better one!");
			return rangeSearch(array,i,P);
			}
			else if(array[i]!=array[middle])
			return new Info(array[i],array[middle]);
	    }
	
	return new Info(array[middle]);
	    
    }
    public static int Potential(int ini,int fim)
    {
	if(ini<0 && fim<0)
	    {
		if(ini<fim)
		    return Math.abs(fim);
		else
		    return Math.abs(ini);
	    }
	else if(ini>0 && fim>0)
	    {
		if(ini<fim)
		    return ini;
		else
		    return fim;
	    }
	else
	    return 0;
    }


}




class Info
{
    int small;
    int big;

    Info()
    {
	small=-1;
	big=-1;
    }
    Info(int s)
    {
	small=s;
	big=-1;
    }
    Info(int s,int b)
    {
	small=s;
	big=b;
    }

    public String toString()
    {
	if(big==-1)
	    return new String(small+"");
	else return new String(small+" "+big);
    }

}