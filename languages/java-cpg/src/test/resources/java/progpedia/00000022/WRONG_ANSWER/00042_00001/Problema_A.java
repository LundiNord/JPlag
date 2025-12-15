import java.util.*;

class Edge {
    int O, D, W;
    Edge(int o, int d, int w) {
        O = o; D = d; W = w;
    }
}
class Action {
    Stack <Edge> Conta; int mL, ML, mC, MC, mA; int nV, O, D;
    Action(Scanner S) {
        nV = S.nextInt(); Conta = new Stack<Edge>();

        mL = S.nextInt(); ML = S.nextInt();
        mC = S.nextInt(); MC = S.nextInt();
        mA = S.nextInt();

        O = S.nextInt(); D = S.nextInt();
        while (S.hasNext()) {
           int v1 = S.nextInt(); if(v1 == -1) {break;}
           else{
               int v2 = S.nextInt(); int L = S.nextInt();
               int C = S.nextInt(); int A = S.nextInt();
               if((L >= mL && L <= ML) && (C >= mC && C <= MC) && A >= mA) {
                   Edge E = new Edge(v1, v2, C); System.out.println(E.O + " " + E.D + " ");
                   Conta.push(E);
               }
           }
        }
        System.out.println(Conta.size());
    }
}

public class Problema_A {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        Action A = new Action(S);
    }

}
