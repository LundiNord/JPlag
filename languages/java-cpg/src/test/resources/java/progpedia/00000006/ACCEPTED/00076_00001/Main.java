//package daaproblemas;

import java.util.Scanner;
import java.util.Vector;

public class Main
{

  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    int ponto = 0;
    Vector<Integer> visitados = new Vector<Integer>();
    while ((ponto = s.nextInt()) != 0)
    {
      if(visitados.contains(ponto))
      {
        Integer p = 0;
        while((p = visitados.remove(visitados.size()-1)) != ponto);
      }
      visitados.add(ponto);
        
    }
    for(int i = 0; i < visitados.size(); i++)
      System.out.println(visitados.get(i));
    
  }

}
