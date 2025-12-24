//package Main;

import java.util.Arrays;
import java.util.Scanner;

public class ClosestSums 
{
	private static Scanner stdin;
	private static int[] sums;

	public static void main(String[] args)
	{
		int[] array;
		int size;
		int num_queries;
		int query;
		int sums_length;		
		
		stdin = new Scanner(System.in);
		size  = stdin.nextInt();
		array = new int[size];
		
		// read number set to array
		for(int i = 0; i < size; i++)
		{
			array[i] = stdin.nextInt();
		}
		
		sums = new int[size * (size-1)/2];
		sums_length = 0;
		
		// sum all the pairs: O(n^2)
		for(int i = 0; i < size; i++)
		{
			for(int j = i + 1; j < size; j++)
			{
				sums[sums_length++] = array[i] + array[j];
			}
		}
		
		// Sort array sums: O(n^2 * log(n^2))
		Arrays.sort(sums);
		
		// read number of queries
		num_queries = stdin.nextInt();
		
		// for each query, apply binary search: O(log n^2)
		for(int i = 0; i < num_queries; i++)
		{
			query = stdin.nextInt();
			
			binarySearch(0, sums_length - 1, query);
		}
	}

	private static void binarySearch(int low, int high, int query) 
	{
		int middle;
		
		while(low <= high)
		{
			middle = low + (high - low)/2;
			
			if(query == sums[middle])
			{
				System.out.println(sums[middle]);
				break;
			}
			else if(query < sums[middle])
			{
				high = middle;
				
				if(high - low <= 1)
				{
					if(Math.abs(query - sums[low]) > Math.abs(query - sums[high]))
					{
						System.out.println(Math.abs(sums[high]));
						break;
					}
					else if(Math.abs(query - sums[low]) == Math.abs(query - sums[high]))
					{
						System.out.println(sums[low] + " " + sums[high]);
						break;
					}
					else
					{
						System.out.println(Math.abs(sums[low]));
						break;
					}
				}
			}
			else
			{
				low = middle;
				
				if(high - low <= 1)
				{
					if(Math.abs(query - sums[low]) > Math.abs(query - sums[high]))
					{
						System.out.println(Math.abs(sums[high]));
						break;
					}
					else if(Math.abs(query - sums[low]) == Math.abs(query - sums[high]))
					{
						System.out.println(sums[low] + " " + sums[high]);
						break;
					}
					else
					{
						System.out.println(Math.abs(sums[low]));
						break;
					}
				}					
			}					
		}
	}
}
