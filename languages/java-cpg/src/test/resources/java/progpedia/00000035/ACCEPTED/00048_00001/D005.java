import java.util.Scanner;

public class D005 {
  static int n, count,k;
  static int v[];

  static void readInput() {

    Scanner stdin = new Scanner(System.in);

    n = stdin.nextInt();

    v = new int[n];

    for (int i=0; i<n; i++) {
      v[i] = stdin.nextInt();
    }
  }

  //-----------------------------------------------------------

  static void solve() {
    count = 0;
    for (int i=0; i<n; i++)
      for (int j=i+1; j<n; j++)
        if (v[i] > v[j]) {
          count++;
        }
    System.out.println(count);
  }

  //-----------------------------------------------------------

  public static void main(String[] args) {
    readInput();
    solve();
  }
}
