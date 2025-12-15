import java.util.*;

public class encomenda_floyd
{
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
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        
        
        
        int[][] m_adj = new int[100][100];
        int or_troco, dest_troco, max_L, max_C, max_A;
        while(true)
        {
            or_troco = stdin.nextInt();
            if(or_troco == -1)
                break;
            dest_troco = stdin.nextInt();
            max_L = stdin.nextInt();
            max_C = stdin.nextInt();
            max_A = stdin.nextInt();

            if((L_min <= max_L && max_L <= L_max) && (C_min <= max_C && max_C <= C_max) && (max_A >= A_min))
            {
                if(!indexes.contains(or_troco)){
                    indexes.add(or_troco);
                }
                
                if(!indexes.contains(dest_troco)){
                    indexes.add(dest_troco);
                }
                m_adj[indexes.indexOf(or_troco)][indexes.indexOf(dest_troco)] = max_C;
                m_adj[indexes.indexOf(dest_troco)][indexes.indexOf(or_troco)] = max_C;
            }
            
        }
        for(int k = 0;k<m_adj.length;k++)
        {
            for(int i=0;i<m_adj.length;i++)
            {
                for(int j=0;j<m_adj.length;j++)
                {
                    m_adj[i][j] = Math.max(m_adj[i][j], Math.min(m_adj[i][k], m_adj[k][j]));
                }
            }
        }

        try
        {
            System.out.println(m_adj[indexes.indexOf(origem)][indexes.indexOf(destino)]);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println(0);
        }
    }
}