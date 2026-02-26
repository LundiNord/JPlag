import java.util.*;

class Aluno {
    boolean visitado = false;
    boolean visitadoT = false;
    LinkedList<Integer> amigos = new LinkedList<Integer>();
    LinkedList<Integer> amigosT = new LinkedList<Integer>();
    int num;
    Aluno(int x){
        num = x;
    }
}

class Sociologia {
    static Aluno[] alunos;
    static int c;

    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        int nC = in.nextInt();
        for(int i=1; i<=nC; i++){
            run(in, i);
        }
    }
    
    static void dfs1(Aluno aluno, LinkedList<Aluno> f) {
        if(!aluno.visitado){
            aluno.visitado = true;
            for(int  a : aluno.amigos)
                dfs1(alunos[a], f);
            f.add(aluno);
        }
    }
    
    static void dfs2(Aluno aluno) {
        if(aluno.visitadoT)
           return;
        c++;
        aluno.visitadoT = true;
        for(int a : aluno.amigosT)
            dfs2(alunos[a]);

    }
    
    static void run(Scanner in, int caso) {
        int n = in.nextInt();
        
        alunos = new Aluno[n+1];
        for(int i=1; i<=n; i++)
            alunos[i] = new Aluno(i);
        
        LinkedList<Aluno> f = new LinkedList<Aluno>();
        
        for(int i=1; i<=n; i++) {
            int a = in.nextInt();
            int na = in.nextInt();
            for( ;na>0; na--) {
                int k = in.nextInt();
                alunos[a].amigos.add(k);
                alunos[k].amigosT.add(a);
            }
        }
        
        for(int i=1; i<=n; i++)
            dfs1(alunos[i], f);
        int x=0, y=0;
        for(Aluno a : f){
            if(a.visitadoT)
                continue;
            c = 0;
            dfs2(a);
            if(c>=4)
                x++;
            else
                y+=c;
        }
        System.out.println("Caso #"+caso+"\n"+x+" "+y);
    }
}
