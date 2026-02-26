import java.io.*;
import java.util.*;

class Racio implements Comparable<Racio> {
  public float racio;
  public int pos;

  Racio(float v1, float v2, int pos) {
    this.racio = v2/v1;
    this.pos = pos+1;
  }

  @Override
  public int compareTo(Racio r) {
    if (racio < r.racio) return +1;
    if (racio > r.racio) return -1;

    return r.pos;
  }

}

class D010 {
  static float d[], m[];

 //--------------------------------------------------

  public static void main(String args[]) {

    Scanner stdin = new Scanner(System.in);

    int n = stdin.nextInt();
    Racio r[] = new Racio[n];

    d = new float[n];
    m = new float[n];

    for (int i=0; i<n; i++) {
      d[i] = stdin.nextFloat();
      m[i] = stdin.nextFloat();
      r[i] = new Racio(d[i], m[i], i);
    }

    Arrays.sort(r);



    for (int i=0; i<n; i++)
      System.out.println(r[i].pos);

  }
}
