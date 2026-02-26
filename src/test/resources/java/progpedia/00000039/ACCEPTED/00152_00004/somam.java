import java.util.*;
import java.io.*;

class somam{
    public static void main(String []args){
	Scanner input=new Scanner(System.in); 

	int n=input.nextInt();
	int q=n*(n-1)/2;
	int somas[]=new int[q];
	int vec[]=new int[n];

	for(int i=0;i<n;i++)
	    vec[i]=input.nextInt();

	int n2=input.nextInt();
	int vec2[]=new int[n2];
	int x = 0;

	for(int i=0;i<n2;i++)
	    vec2[i]=input.nextInt();
	
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		somas[x]= vec[i]+vec[j];
		x++;
	    }
	}
	Arrays.sort(somas);

	for(int i=0;i<n2;i++){
	   
	   bs(somas,0,q-1,vec2[i]);
	}
    }


    public static void bs(int somas[],int low,int high,int key){

	while(high-low>1){
	    int middle=low+(high-low)/2;
	    if(somas[middle]<key)
		low=middle;
	    else
		high=middle;
	}
	if(key-somas[low]==somas[high]-key && somas[low]!=key && somas[high]!=key)
	    System.out.println(somas[low]+" "+somas[high]);
	else{
	    if(key-somas[low]<somas[high]-key)
		System.out.println(somas[low]);
	    else
		System.out.println(somas[high]);
	}


















	/*	int min=Integer.MAX_VALUE;
	int middle=0;
	int aux = 0;
	if(somas[low]>=key) System.out.println(somas[low]);
	if(somas[high]<=key) System.out.println(somas[high]);


	while(low<high){
	  
	    middle=low+(high-low)/2;
	    // System.out.println("MEIO: " + middle);
	    if(somas[middle]>=key) high=middle;
	    else{
		low=middle+1;
		
	    }	
	}
	if(middle != 0) aux = middle-1;
	else aux = middle + 1;

       	if(somas[high]>=key && somas[low]<=key)
		return aux;
	int soma1;
	int soma2;
	soma1 = Math.abs(key-somas[middle]);
	System.out.println(soma1);
	soma2 = Math.abs(key-somas[aux]);
	System.out.println(soma2);


	if(soma1 < soma2) System.out.println(somas[middle]);
	else if(soma1 > soma2) System.out.println(somas[aux]);
	else if(soma1 == soma2) System.out.println(somas[aux] + " " + somas[middle]);*/
		
    }
}
