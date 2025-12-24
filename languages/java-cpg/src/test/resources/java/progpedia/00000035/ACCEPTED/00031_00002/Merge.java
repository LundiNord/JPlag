import java.util.*;

class Merge
{
    public static void main(String args[])
    {
	Scanner stdin = new Scanner(System.in);
	int N=stdin.nextInt();

	int array[]=new int[N];

	for(int i=0;i<N;i++)
	    {
		array[i]=stdin.nextInt();
	    }
	

	System.out.println(MergeCount(array,0,N-1));

    }
    public static int MergeCount(int array[],int ini,int fim)
    {
	if(ini==fim)
	    return 0;
	else
	    {
		int total=0;
		int middle=ini+(fim-ini)/2; //indice do fim da primeira divisao (incluido)


		total+=MergeCount(array,ini,middle);
		total+=MergeCount(array,middle+1,fim);

		int pivo1=ini;  //apontam para os indices
		int pivo2=middle+1;

		int aux[]=new int[fim-ini+1];

		for(int i=0;i<aux.length;i++)
		    {
			if(pivo1>middle)
			    {
				aux[i]=array[pivo2];
				pivo2++;
				
			    }
			else if(pivo2>fim)
			    {
				aux[i]=array[pivo1];
				pivo1++;
				
			    }
			else if(array[pivo1]<=array[pivo2])
			    {
				aux[i]=array[pivo1];
				pivo1++;

			    }
			else if(array[pivo1]>array[pivo2])
			    {
				aux[i]=array[pivo2];
				pivo2++;
				total+=middle-pivo1+1;   //tempo linear
			    }
		    }
		for(int i=0;i<aux.length;i++)
		    {
			array[ini+i]=aux[i];
		    }
		return total;
	    }
    }


    public static void MergeSort(int array[],int ini,int fim)
    {
	if(ini<0 || ini>array.length-1 || fim<0 || fim>array.length-1)
	    throw new IndexOutOfBoundsException();


	if(ini==fim)
	    return;
	else
	    {
		int middle=ini+(fim-ini)/2; //indice do fim da primeira divisao (incluido)
		MergeSort(array,ini,middle);
		MergeSort(array,middle+1,fim);

		int pivo1=ini;  //apontam para os indices
		int pivo2=middle+1;

		int aux[]=new int[fim-ini+1];

		for(int i=0;i<aux.length;i++)
		    {
			if(pivo1>middle)
			    {
				aux[i]=array[pivo2];
				pivo2++;
			    }
			else if(pivo2>fim)
			    {
				aux[i]=array[pivo1];
				pivo1++;
			    }
			else if(array[pivo1]<=array[pivo2])
			    {
				aux[i]=array[pivo1];
				pivo1++;
			    }
			else if(array[pivo1]>array[pivo2])
			    {
				aux[i]=array[pivo2];
				pivo2++;
			    }
		    }
		for(int i=0;i<aux.length;i++)
		    {
			array[ini+i]=aux[i];
		    }

	    }
    }

    


}