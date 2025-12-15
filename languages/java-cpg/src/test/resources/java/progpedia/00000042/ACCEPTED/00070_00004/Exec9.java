
import java.util.Scanner;


public class Exec9 {
		
	static void sortArray(Line []array){
		int min=array[0].start;
		for(int i=0;i<array.length;i++){
			for(int j=i;j<array.length;j++){
				if(array[j].start<min){
					min=array[j].start;
					int temp1=array[i].start;
					int temp2=array[i].end;
					array[i].start=array[j].start;
					array[i].end=array[j].end;
					array[j].start=temp1;
					array[j].end=temp2;
				}
				min=array[i].start;
			}
			
		}
		
	}
	
	static int minimumCoverage(Line []array,int totalSize){
	/*	int end=0,best=0,tmp=0,number=0,distance=0,o=0;
		while(end<n){
		for(int i=0;i<matrix.length;i++){
			if(matrix[i][0]-end==distance){
				if (matrix[i][1]>best){
					best=matrix[i][1];
					tmp=i;
				}
				o=1;
				distance=0;
			}
			
			if(i==(matrix.length-1) && o==0){
				distance--;
				i=-1;
			}
			if(i==(matrix.length-1) && o==1){
				o=0;
			}
		}
		end=matrix[tmp][1];
		number++;
		}
		return number;*/
		int end=0,best=0,index=0,counter=0;
		while(end<totalSize){
		for(int i=0;i<array.length;i++){
			if(array[i].start<=end){
				if(array[i].end>best){
					best=array[i].end;
					index=i;
				}
				
			}
		}
		end=array[index].end;
		counter++;
		}
		
		
		
		return counter;
	}
	
	public static void main(String args[]) {
		int n;
		Line array[];
		Scanner stdin = new Scanner(System.in);
		
		int totalSize=stdin.nextInt();
		n=stdin.nextInt();
		array = new Line [n];
		for(int i=0;i<n;i++){
			array[i] = new Line (stdin.nextInt(),stdin.nextInt());
			
		}
		sortArray(array);
		
		System.out.println(minimumCoverage(array,totalSize));
		
	 }
}

class Line{
	int size,start,end;
	public Line(int start,int end){
		this.start=start;
		this.end=end;
		this.size=end-start;
	}
}
