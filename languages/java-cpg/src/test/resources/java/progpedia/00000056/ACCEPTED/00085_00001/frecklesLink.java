import java.util.*;
import java.lang.*;
//Definition of point.
class Point
{
	double x;
	double y;
	boolean check;
	Point(double x,double y)
	{
		this.x = x;
		this.y = y;
		check = false;
	}
}
//Definition of check.
class Link
{
	Point a;
	Point b;
	boolean check;
	double distance;
	Link(Point a,Point b)
	{
		this.a = a;
		this.b = b;
		this.distance = Math.sqrt((b.x-a.x)*(b.x-a.x) + (b.y-a.y)*(b.y-a.y));
		this.check = false;
	}
}
public class frecklesLink
{
	static Point points[];	// An array of points.
	static int nPoints;		// Number of points we have.
	static Link links[];		// An array of links.
	static int linkCont;	// The counter of the links array.
	static double cost;		// The cost we want to print.
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);		// Scanner.
		nPoints = input.nextInt();					// Number of points we have.
		points = new Point[nPoints];				// Defining array of points size.
		for(int i=0;i<nPoints;i++)					// Reading data, creating points and placing them in the array.
		{
			double x = input.nextDouble();
			double y = input.nextDouble();
			points[i] = new Point(x,y);
		}
		int size = nPoints * (nPoints - 1);			// Max size of the links array.
		linkCont = 0;								// Actual size of links array, starts at 0.
		cost = 0;									// The cost we want to print.
		links = new Link[size];						// Defining links array size.
		linkPoints(points[0]);						// Calling linkPoints function on the first point.
		System.out.println(String.format( "%.2f",cost));					// Printing.
	}
	// This function takes a point, sets check=true and adds to the links array all its un-added links that travel from that point to un-checked points.
	static void addLinks(Point a)
	{	
		a.check = true;
		for(int i=0;i<nPoints;i++)
		{
			if(points[i].check == false)
			{
				links[linkCont] = new Link(a,points[i]);
				linkCont++;
			}
		}
	}
	static void linkPoints(Point a)
	{
		addLinks(a);
		double minDist = 1000000;
		int choosen = -1;
		for(int i=0;i<linkCont;i++)
		{
			if(links[i].check == false && links[i].b.check == false)
			{
				if(links[i].distance < minDist)
				{
					minDist = links[i].distance;
					choosen = i;
				}
			}
		}
		if(choosen != -1)
		{
			links[choosen].check = true;
			cost += minDist;
			linkPoints(links[choosen].b);
		}		
	}
}