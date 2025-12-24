import java.util.*;

public class A {
    static HashMap<Integer, LinkedList<node>> trajeto = new HashMap<Integer, LinkedList<node>>();

    public static void main(String[] args)
    {
        Scanner stdin = new Scanner(System.in);

        int locais = stdin.nextInt();
        stdin.nextLine();

        int l_min = stdin.nextInt();
        int l_max = stdin.nextInt();
        int c_min = stdin.nextInt();
        int c_max = stdin.nextInt();
        int a_min = stdin.nextInt();
        stdin.nextLine();
        int origem = stdin.nextInt();
        int destino = stdin.nextInt();
        stdin.nextLine();
        int count=0;
        int or_troco, dest_troco, max_l, max_c, a_jaula;
        while(!stdin.hasNext("-1"))
        {
            or_troco = stdin.nextInt();
            dest_troco = stdin.nextInt();
            max_l = stdin.nextInt();
            max_c = stdin.nextInt();
            a_jaula = stdin.nextInt();

            if((max_l >= l_min) && (max_c >= c_min) && (a_jaula >= a_min))
            {
                //System.out.println(or_troco + " " + dest_troco);
                
                count+=1;
            }
            stdin.nextLine();
        }
        System.out.println(count);
    }

}

class node{
    node(){}
    int fim;
    int peso;
}