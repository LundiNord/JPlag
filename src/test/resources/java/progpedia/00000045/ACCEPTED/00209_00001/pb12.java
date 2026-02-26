import java.util.*;

class pb12{
  static boolean p[][];
  static int n;
  static long c[][];

  public static void calc(int a, int b){
    if(p[a][b]) c[a][b] = 0;
    else if (a == n-1) c[a][b] = 1;
    else{
      c[a][b] += c[a+1][b];
      c[a][b] += c[a+1][b+1];
    }

  }


  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    n = stdin.nextInt();
    p = new boolean[n][n];
    c = new long[n][n];
    int d = stdin.nextInt();
    for(int i = 0; i<d; i++) p[n - stdin.nextInt()][stdin.nextInt()-1] = true;


    for(int i=n-1;i+1>0;i--){
  	   for(int j=0;j<=i;j++){
  		    calc(i,j);
  	    }
  	}
    System.out.println(c[0][0]);
  }
}
