import java.io.*;
import java.util.*;
import java.math.*;
class prob7{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	int S[]=new int[N];
	for(int i=0;i<N;i++)
	    S[i]=sc.nextInt();
	int P=sc.nextInt();
	int Pi[]=new int[P];
	for(int k=0;k<P;k++)
	    Pi[k]=sc.nextInt();
	int tam=(N-1)*N/2 + 2;
	int so[]=new int[tam];
	int t=0;
	for(int a=0;a<N;a++){
	    for(int b=0;b<a;b++){
		so[t]=S[a]+S[b];
		t++;
	    }
	}
	so[t++] = -5000001;
	so[t++] =  5000001;
	Arrays.sort(so);
	//	for(int q=0;q<tam;q++)
	//  System.out.print(so[q]+" ");
	for(int l=0; l<P;l++)
	    bsearch(so,0,tam,Pi[l]);
       
    }

   static int bsearch(int v[], int low, int high, int key){
       int mid;
       while(low<high){
	   int middle=low+(high-low)/2;
	   if(v[middle]>=key)
	       high = middle;
	   else
	       low=middle+1;
       }

       if(Math.abs(v[low]-key)==Math.abs(v[low-1]-key))
	   System.out.println(v[low-1]+" "+v[low]);
       else if(Math.abs(v[low]-key)<Math.abs(v[low-1]-key))
	   System.out.println(v[low]);
       else
	   System.out.println(v[low-1]);
       return -1;
   }
}
