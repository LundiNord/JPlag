import java.util.*;
import java.io.*;


class Somas2 {
    static int n;
    static int Solve(int v[],int x,int t){
        if(x<=v[0]) return v[0];
	if(x>=v[t-1]) return v[t-1];
	return v[bsearch(v,0,t-1,x)];
    }
    
    static int bsearch(int v[], int low,int high, int key){
	int middle=0,i=0,j=0;
	//System.out.println("bsearch("+ key + ")");
	while(low<high){
	    //System.out.println("[" + v[low] + "," + v[middle] + "," + v[high] + "]");
	    middle = low +(high-low)/2;
	    if(key == v[middle]) return middle;
	    if(key < v[middle]){ 
		high = middle-1;
		i = middle;
	    }
	    else {
		low = middle+1;
		j = middle;
	    }
	
	    //System.out.println("!!" + low + " " + high + " " + middle);
	}
	int resp1 = Math.abs(key-v[j]);
	int resp2 = Math.abs(key-v[low]);
	int resp3 = Math.abs(key-v[i]);
	//System.out.println("!!" + key + " " + v[i] + "-" + resp3 + " " + v[j] + "-" + resp1 + " " + v[low] + "-" + resp2);
	if(resp1<resp2 && resp1<resp3) return j;
	if(resp2<resp1 && resp2<resp3) return low;
	if(resp3<resp2 && resp3<resp1) return i;
	if(resp1>resp2 && resp1>resp3 && v[i]==v[low]) return low;
	if(resp2>resp1 && resp2>resp3 && v[j]==v[i]) return i;
	if(resp3>resp1 && resp3>resp2 && v[j]==v[low]) return j;
       	if(resp1 == resp2 && v[j]!=v[low]){   
	    System.out.print(v[j] + " ");
	    return low;
	}
	if(resp3 == resp2 && v[i]!=v[low]){
	    System.out.print(v[low] + " ");
	    return i;
	}
	if(resp3 == resp1 && v[i]!=v[j]){
	    System.out.print(v[j] + " ");
	    return i;
	}
	//System.out.println("!!" + key + " " + v[j] + " " + v[low]);
	if(key==v[low]) return low;
	if(key==v[i]) return i;
	return j;
	
	}

    static int Somatorio(int y){
	int soma=0;
	for(int i=y-1;i>0;i--)
	    soma += i;
	return soma;
    }

    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int v[],p,q[],somas[],t,s=0;

	n = stdin.nextInt();
	v = new int[n];
	for(int i=0; i<n; i++)
	    v[i] = stdin.nextInt();
	p = stdin.nextInt();
	q = new int[p];
	for(int j=0; j<p; j++)
	    q[j] = stdin.nextInt();
	t = Somatorio(n);
	somas = new int[t];
	for(int j=0;j<n-1;j++)
	    for(int k=j+1;k<n;k++){
		somas[s] = v[j] + v[k]; 
		s++;
	    }
	Arrays.sort(somas);
	/*for(int i=0;i<t;i++)
	    System.out.print(somas[i] + " ");
	    System.out.println();*/
	for(int k=0;k<p;k++)
	    System.out.println(Solve(somas,q[k],t));
    }
}
