
import java.util.Scanner;


public class DAA9 {
		
	static void sortArray(Seg []array){
		int min=array[0].l;
		for(int i=0;i<array.length;i++){
			for(int j=i;j<array.length;j++){
				if(array[j].l<min){
					min=array[j].l;
					int temp1=array[i].l;
					int temp2=array[i].r;
					array[i].l=array[j].l;
					array[i].r=array[j].r;
					array[j].l=temp1;
					array[j].r=temp2;
				}
				min=array[i].l;
			}
			
		}
		
	}
	
	static int minimumCoverage(Seg []array,int totalSize){

		int end=0,best=0,index=0,counter=0;
		while(end<totalSize){
		for(int i=0;i<array.length;i++){
			if(array[i].l<=end){
				if(array[i].r>best){
					best=array[i].r;
					index=i;
				}
				
			}
		}
		end=array[index].r;
		counter++;
		}
		
		
		
		return counter;
	}
	
	public static void main(String args[]) {
		int n;
		Seg array[];
		Scanner in = new Scanner(System.in);
		
		int totalSize=in.nextInt();
		n=in.nextInt();
		array = new Seg [n];
		for(int i=0;i<n;i++){
			array[i] = new Seg (in.nextInt(),in.nextInt());
			
		}
		sortArray(array);
		
		System.out.println(minimumCoverage(array,totalSize));
		
	 }
}

class Seg{
	int size,l,r;
	public Seg(int l,int r){
		this.l=l;
		this.r=r;
		this.size=r-l;
	}
}
