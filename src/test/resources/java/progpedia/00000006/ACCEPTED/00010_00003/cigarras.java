import java.util.*;

class sol{
  Scanner kb;
  sol(Scanner kb){this.kb = kb;}
  void solve(){
    HashSet<Integer> visited = new HashSet<Integer>();
    int path[] = new int[30];
    int p = 0;
    while(true){
      int n = kb.nextInt();
      if(n==0) break;
      if(visited.contains(n)){
        p--;
        while(p>=0){
          if(path[p]==n) break;
          visited.remove(path[p]);
          p--;
        }
        p++;
      }else{
        visited.add(n);
        path[p++] = n;
      }
    }
    for(int i = 0; i < p; i++){
      System.out.printf("%d\n",path[i]);
    }
  }
}

public class cigarras{
  public static void main(String args[]){
    Scanner kb = new Scanner(System.in);
    sol s = new sol(kb);
    s.solve();
  }
}

