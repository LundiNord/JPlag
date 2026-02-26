import java.util.*;
import java.io.*;

class onn{

	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		in.nextLine();
		int [] numeros = new int[n];
		for(int i=0;i<n;i++)
		{
			numeros[i]=in.nextInt();
		}

		Arrays.sort(numeros);

		for(int i=0;i<n;i++)
		{
			System.out.print(numeros[i]+" ");
		}
	}
	
	/*public static void mergeSort(int[] numeros,int low, int high)
	{
		if(low<high)
		{
			int half = (high-low)/2+low;
			mergeSort(numeros,low,half);
			mergeSort(numeros,half+1,high);
			int[] A = new int[half-low+1];
			int[] B = new int[high-half];

			for(int i=0;i<=half-low;i++)
			{
				A[i]=numeros[low+i];
			}

			for(int i=0;i<=high-half-1;i++)
				{
					B[i] = numeros[half+1+i];
				}

			int i=0;
			int j=0;

			for(int k = low; k<=high; k++)
			{
				if(i<A.length && j<B.length)
				{
					if(A[i]<B[i])
					{
						numeros[k]=A[i++];
					}
					else
					{
						numeros[k]=B[j++];
					}
				}

				else if(i<A.length)
				{
					numeros[k] = A[i++];
				}

				else if(j<B.length)
				{
					numeros[k]=B[j++];
				}
			}
		}*/
	}

