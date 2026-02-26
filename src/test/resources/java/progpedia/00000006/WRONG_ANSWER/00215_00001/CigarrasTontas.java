import java.util.*;

class Percurso {
    int nL;
    int[] P;
    int Cursor;
    Percurso(Scanner S) {
        Cursor = 0;
        P = new int[30];
        for (int i = 0; i < 30; i++) {
            P[i] = S.nextInt();
            if(P[i] == 0) {nL = i+1; break;}
        }
    }
    void SemRep() {
        System.out.println("OUTPUT:");
        while(Cursor < nL-1) {
            int V = P[Cursor];
            for (int i = Cursor + 1; i < nL; i++) {
                if(P[i] == V) {Cursor = i ; break;}
            }
            System.out.println(V);
            Cursor++;
        }
    }
}
class CigarrasTontas {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        Percurso P = new Percurso(S);
        P.SemRep();
    }
}