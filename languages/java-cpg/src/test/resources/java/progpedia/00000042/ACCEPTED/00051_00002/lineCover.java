
import java.util.*;

	class Line
	{
		int min;
		int max;
		Boolean used;
		Line(int min,int max)
		{
			this.min = min;
			this.max = max;
			used = false;
		}

	}

public class lineCover
{

	public static void main (String args[])
	{
		Scanner input = new Scanner(System.in); // Declaration of input scanner.
		int mainMaxLine = input.nextInt(); // Max of the main line to be covered.
		int numberLines = input.nextInt(); // Number of lines to be read.
		input.nextLine();
		Line arrayLines[] = new Line[numberLines]; // Array of lines to be read.
		for(int i=0;i<numberLines;i++) // Reading and creating linea.
		{
			int min = input.nextInt();
			int max = input.nextInt();
			input.nextLine();
			arrayLines[i] = new Line(min,max);
		}
		Arrays.sort(arrayLines, new Comparator<Line>() // Array sort with a custom parameter.
		 { 
      		public int compare(Line l1, Line l2) // Custom compare function.
      		{ 
	        	int c = l1.min - l2.min; // Compares the min.
	        	if (c == 0) // If the size is the same.
	        	{
	          		c = l2.max - l1.max; // Compares the start of each line.
	    
	        	}
        		return c;
        	}
        });
        int usedLines = 0;
        int current = 0;
        int bestLine = 0;
        int i=0;
        while(current < mainMaxLine)
        {
        	while (i<numberLines && arrayLines[i].min <= current)
			{
				if(arrayLines[i].max >= arrayLines[bestLine].max)
				{
					bestLine = i;
				}
				i++;
			}
			usedLines++;
			current = arrayLines[bestLine].max;
			//System.out.println(current + " " + bestLine);
        }
        System.out.println(usedLines);
	}
}