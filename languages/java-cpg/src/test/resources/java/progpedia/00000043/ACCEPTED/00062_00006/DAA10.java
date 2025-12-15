import java.util.*;

class Par implements Comparable<Par>{
    int t,m,index;
    float div;
    Par(int t, int m,int index){
	this.t=t;
	this.m=m;
	this.index=index;
	div=(float)m/t;
    }
    public int compareTo(Par b){
	if(div>b.div) return -1;
	else if(div<b.div) return 1;
	else return 0;
	
    }
}

class DAA10{
    static public void main(String Args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	Par dm[] = new Par[n];
	for(int i=0; i<n; i++)
	    dm[i]= new Par(in.nextInt(), in.nextInt(),i);
	
	Arrays.sort(dm);
	for(int i=0; i<n; i++){
	    if(i<n-1)
		System.out.print(dm[i].index+1+" ");
	    else
		System.out.println(dm[i].index+1);
	}
    }
}
