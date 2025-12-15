import java.util.*;

public class cigarras {
    public static void main (String[] args)
    {
        Scanner stdin = new Scanner(System.in);
        LinkedList<Integer> caminho = new LinkedList<Integer>();
        int rem;
        int temp= Integer.parseInt(stdin.nextLine());
        caminho.add(temp);
        while(!stdin.hasNext("0"))
        {
            temp = Integer.parseInt(stdin.nextLine());
            if(caminho.contains(temp))
            {
                rem = caminho.indexOf(temp);
                while(true)
                {
                    if(caminho.getLast()!=caminho.get(rem))
                        caminho.removeLast();
                    else
                        break;
                }
            }
            else
                caminho.add(temp);
        }
        for(Integer i : caminho)
        {
            System.out.println(i);
        }
    }
}
