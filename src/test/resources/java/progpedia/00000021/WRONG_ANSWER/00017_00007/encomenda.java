import java.util.*;

public class encomenda {
    static HashMap<Integer,LinkedList<node>> trajecto = new HashMap<Integer, LinkedList<node>>();

    public static void main(String[] args)
    {
        Scanner stdin = new Scanner(System.in);
        //Ler primeira linha
        int L_min = stdin.nextInt();
        int L_max = stdin.nextInt();
        int C_min = stdin.nextInt();
        int C_max = stdin.nextInt();
        int A_min = stdin.nextInt();

        stdin.nextLine();

        //Ler segunda linha
        int origem = stdin.nextInt();
        int destino = stdin.nextInt();

        stdin.nextLine();

        int or_troco, dest_troco, max_L, max_C, max_A;
        while(!stdin.hasNext("-1"))
        {
            or_troco = stdin.nextInt();
            dest_troco = stdin.nextInt();
            max_L = stdin.nextInt();
            max_C = stdin.nextInt();
            max_A = stdin.nextInt();

            if((L_min <= max_L && max_L <= L_max) && (C_min <= max_C && max_C <= C_max) && (max_A >= A_min))
            {
                contruir_grafo(or_troco, dest_troco, max_C, 1);
            }
        }
        System.out.println(pesquisa(origem, destino, trajecto, C_max));
    }
    static int pesquisa(int origem, int destino,HashMap<Integer,LinkedList<node>> trajecto, int C_Max)
    {
        try{
        HashMap<Integer,Integer> distancia = new HashMap<Integer, Integer>();
        distancia.put(destino, 0);
        ArrayList<Integer> visitados = new ArrayList<Integer>();

        for(Integer i: trajecto.keySet())
        {
            distancia.put(i, 0);
        }
        distancia.put(origem, C_Max);

        while(trajecto.size()>visitados.size())
        {
            int maior=-1;
            int vertice=0;
            for(int i: distancia.keySet())
            {
                if(!visitados.contains(i))
                {
                    if(distancia.get(i)>maior)
                    {
                        vertice = i;
                        maior = distancia.get(i);
                    }
                }
                    
            }
            visitados.add(vertice);
            for(node j: trajecto.get(vertice))
            {
                if(!visitados.contains(j.fim))
                {
                    distancia.put(j.fim, Math.max(Math.min(distancia.get(vertice),j.peso),distancia.get(j.fim)));
                }
            }
        }

        return distancia.get(destino);
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public static void contruir_grafo(int or_troco, int dest_troco, int C_Max, int s)
    {
        if(trajecto.containsKey(or_troco))
        {
            LinkedList<node> tmp = trajecto.get(or_troco);
            node temp = new node();
            temp.fim = dest_troco;
            temp.peso = C_Max;
            tmp.add(temp);
            trajecto.put(or_troco, tmp);
            s*=-1;
        }
        else
        {
            LinkedList<node> tmp = new LinkedList<node>();
            node temp = new node();
            temp.fim = dest_troco;
            temp.peso = C_Max;
            tmp.add(temp);
            trajecto.put(or_troco, tmp);
            s*=-1;
        }

        if(s<0)
            contruir_grafo(dest_troco, or_troco, C_Max, s);
    }



}

class node
{
    int fim;
    int peso;
    node(){}
}