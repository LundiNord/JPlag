import java.util.*;

class ProblemaC {

    public static void main(String args[]){
	int local[]=new int[30],i=0,j,n;
	Scanner sc=new Scanner(System.in);
	
	local[i]=sc.nextInt();
	i++;
	n=sc.nextInt();
	//O(1)
	while(n!=0){
	    //O(n)
	    for(j=0;j<i;j++)
		if(local[j]==n)
		    break;
	    //O(n)
	    if(j<i)
		i=j+1;
	    else {
		local[i]=n;
		i++;
	    }
	    n=sc.nextInt();
	    //O(1)
	}

	for(j=0;j<i;j++)
	    System.out.println(local[j]);
	//O(n)
    }
}

