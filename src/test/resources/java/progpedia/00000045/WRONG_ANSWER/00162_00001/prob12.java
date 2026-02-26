import java.util.*;

class prob12{
    
    static int n;
    static boolean piramide[][];
    static int p[][];



    public static void values(int x,int y){

	if(x == n-1)p[x][y]++;
	else{
	    if(!piramide[x+1][y]) p[x][y]+= p[x+1][y];
	    if(!piramide[x+1][y+1])p[x][y]+= p[x+1][y+1];
	 }
    }

    public static void main(String args[]){
    
    Scanner in = new Scanner(System.in);
        
     n = in.nextInt();
        
     piramide = new boolean[n][n];

     p = new int[n][n];
     
     int broken = in.nextInt();
        
     for(int i=0;i<broken;i++){
	 int x = in.nextInt();
	 int y = in.nextInt();
	 piramide[n-x][y-1] = true;
     }


	
	for(int i=n-1;i>=0;i--){
	    for(int j=0;j<=i;j++){
		values(i,j);
	    }
	}

	/*	System.out.println();
       
      	for(int i=n-1;i>=0;i--){
	    for(int j=0;j<=i;j++){
		System.out.print(p[i][j]);
		}
	    System.out.println();
	}


	System.out.println();
	*/
	
	System.out.println(p[0][0]);


       
   
        
    }
    

  }
