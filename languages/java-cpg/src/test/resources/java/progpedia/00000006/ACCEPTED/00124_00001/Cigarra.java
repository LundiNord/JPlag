import java.util.*;
 
 class Cigarra {
	 public static void main(String[] args){
		 Scanner in = new Scanner (System.in);
		 int v[] = new int[30];
		 int local,i,n;
		 n=0;
		 i=1;
		 local= in.nextInt();
		 v[0]=local;
		 local= in.nextInt();

		 while(local!=0){
			 for(int j=0;j<=n;j++){
				 if(v[j]==local){
					 i=j;
					 n=j-1;
				 }		
			 }
			 v[i]=local;
			 
			 
			 local=in.nextInt();
			 i++;
			 n++;
		 }
		 
		 for(int j=0;j<i;j++){
			 System.out.println(v[j]);
		 }
		 
		 
	 }

}
