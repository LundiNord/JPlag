//package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Shoemaker 
{
	private static Scanner stdin;
	private static List<Job> jobs;		/* list containing job's schedule*/
	private static int n;				/* number of jobs */

    public static void main(String[] args)
    {
    	stdin = new Scanner(System.in);
    	
    	n    = stdin.nextInt();
    	jobs = new ArrayList<Job>(n);
    	
    	// read jobs
    	for(int i = 0; i < n; i++)
    	{
    		jobs.add(new Job(i+1, stdin.nextInt(), stdin.nextInt()));
    	}
    	
    	Collections.sort(jobs, new Comparator<Job>()
		{
    		public int compare(Job j1, Job j2)
    		{
    			double p1 = j1.days_ / (double)j1.fine_;
    			double p2 = j2.days_ / (double)j2.fine_;
    			
    			if (p1 < p2) 		 return -1;
    			if (p1 > p2) 		 return +1;
    			if (j1.id_ < j2.id_) return -1;
    			if (j1.id_ > j2.id_) return +1;
    			return 0;
    		}
		});
    	
    	// print job's schedule
    	for(Job j : jobs)
    	{
    		System.out.print(j.id_);
    		
    		if(j.id_ < jobs.size())
    		{
    			System.out.print(" ");
    		}
    	}
    	
    	System.out.println();
    }
}

class Job
{
	int id_ 
	, days_
	, fine_;
	
	public Job(int id, int days, int fine)
	{
		this.id_   = id;
		this.days_ = days;
		this.fine_ = fine;
	}
}