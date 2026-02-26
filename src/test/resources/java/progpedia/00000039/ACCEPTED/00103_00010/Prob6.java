import java.util.*;
import java.io.*;
import java.lang.Math;

public class Prob6{
    public static int soma[];
    public static void main(String args[]){
	int j,i,np, ns,sum=0,k=0 ;
	int p[],s[];
	
	
	Scanner in=new Scanner(System.in);
	
	np=in.nextInt();
	p=new int[np];
	for(i=0;i<np;i++)
	    p[i]=in.nextInt();

	ns= in.nextInt();
	s= new int[ns];
	for(i=0;i<ns;i++)
	    s[i]=in.nextInt();
	
	for(i=0;i<np-1;i++){
	    //System.out.println("antes:" + sum);
	    sum=sum+(np-1)-i;
	    //System.out.println("depois:" + sum);
	}
	//System.out.println(sum);
	soma= new int[sum];
	for(i=0;i<np;i++){
	    for(j=i+1;j<np;j++){
		soma[k]=p[i]+p[j];
		//System.out.println(p[i]+ "pi" + p[j]+ "pj" +soma[k] +"ola"+ k);
		k++;
	    }
	}

	Arrays.sort(soma);
        /*for(i=0;i<soma.length;i++)
	    System.out.println(soma[i]);
	*/
	for(i=0;i<ns;i++){
	    bSearch(s[i]); 
	}
    }

    public static void bSearch(int condicao){
	int middle=0,low=0, high= soma.length;
	if(condicao > soma[soma.length-1]){
		System.out.println(soma[soma.length-1]);
		return;
	    }
	while(low<high){
	    middle = low +(high-low)/2;
	    // System.out.println("middle" + middle + "low" + low + "high" +high);
	  
	    if(soma[middle]>=condicao)
		high=middle;
	    else
		low=middle+1;
	    //System.out.println("low" + low + "high" + high);
	}

	    
	if(low>0){
	    if(soma[low]-condicao > Math.abs(soma[low-1] -condicao)){
		System.out.println( soma[low-1]);
		return;
	    }
	}
	if(low>0)
	    if(Math.abs(soma[low-1]-condicao) == Math.abs(soma[low]-condicao)){
		System.out.println(soma[low-1] +" "+soma[low]);
		return;
	    }

	System.out.println( soma[low]);
    }
    
}


