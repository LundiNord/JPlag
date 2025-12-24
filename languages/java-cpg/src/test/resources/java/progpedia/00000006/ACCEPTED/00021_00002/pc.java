import java.util.*;

class sol{
  Scanner kb;
  sol(Scanner kb){this.kb = kb;}
  void solve(){
    boolean visited[] = new boolean[10000];
    int path[] = new int[100];
    int p = 0;
    while(true){
      int n = kb.nextInt();
      if(n==0) break;
      if(visited[n]){
	      p--;
	      while(p>=0){
          if(path[p]==n) break;
          visited[path[p]] = false;
          p--;
	      }
	      p++;
      }else{
	      visited[n] = true;
	      path[p++] = n;
      }
    }
    for(int i = 0; i < p; i++){
      System.out.printf("%d\n",path[i]);
    }
  }
}

public class pc{
  public static void main(String args[]){
    Scanner kb = new Scanner(System.in);
    sol s = new sol(kb);
    s.solve();
  }
}
