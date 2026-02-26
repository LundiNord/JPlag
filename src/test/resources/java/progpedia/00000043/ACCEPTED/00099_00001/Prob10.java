import java.util.*;
import java.io.*;

// Classe para guardar um clube
class Shoe implements Comparable<Shoe> {
    public int days;
    public int cost;
    public int num;
    public float daycost;

    Shoe(int d, int c, int n) {
     days = d;
     cost = c;
     num = n;
     daycost = (float)d/c;

    } 
    
    @Override
    public int compareTo(Shoe s) {
    if (daycost > s.daycost) return +1;
    if (daycost < s.daycost) return -1;
    return 0;
    }
}

public class Prob10{
    static int n;
    static Shoe s[];

    static void readInput(){

    Scanner stdin = new Scanner(System.in);

    n = stdin.nextInt();

    s = new Shoe[n];

    for(int i=0;i<n;i++){
        s[i] = new Shoe(stdin.nextInt(), stdin.nextInt(), i+1);
       
    }

    }

    public static void main(String args[]){
    
    readInput();

    Arrays.sort(s);

    for(int i=0;i<n;i++){
         System.out.print(s[i].num);
        if(i<n-1) System.out.print(" ");
    }

    System.out.println();
    }

}
