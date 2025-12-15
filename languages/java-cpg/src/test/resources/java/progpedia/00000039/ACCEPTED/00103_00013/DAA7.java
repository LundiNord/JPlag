import java.util.*;

class DAA7{
    static int bsearch(int[]v, int low, int high, int key){
	int middle;
	int g;
	while(low<high){
	    middle=low+(high-low)/2;
	    if(key<=v[middle])
		high=middle;
	    else 
		low=middle+1;
	}
      
	if(low>0 && low<v.length){
	   
	    if(v[low]==key)
		System.out.println(v[low]);
	    else if(Math.abs(key-v[low-1])<Math.abs(v[low]-key))
		System.out.println(v[low-1]);
	    else if(Math.abs(key-v[low-1])>Math.abs(v[low]-key))
		System.out.println(v[low]);
	    else
		System.out.println(v[low-1]+" "+v[low]);
	}
	else if(key>v[v.length-1])
	    System.out.println(v[v.length-1]);
	else
	    System.out.println(v[0]);
	return 0;
    }

    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int []v = new int[n];
	for(int i=0; i<n; i++)
	    v[i]=in.nextInt();
	
	int m = in.nextInt();
	int []p = new int[m];
	for(int i=0; i<m; i++)
	    p[i]=in.nextInt();
	
	int cal = n*(n-1)/2;
	int []sol = new int[cal];
	int c=0;
	for(int i=0; i<n; i++)
	    for(int k=i+1; k<n; k++){
		sol[c]=v[i]+v[k];
		c++;
	    }
	Arrays.sort(sol);
	for(int i=0; i<m; i++)
	    bsearch(sol,0,cal,p[i]);
    }
}
    
