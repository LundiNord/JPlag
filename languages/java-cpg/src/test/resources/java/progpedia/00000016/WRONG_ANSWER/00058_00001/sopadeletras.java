
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nuno
 */
public class sopadeletras {

    static int destingue(String a, String b){
               
        for (int i=0; i<a.length(); i++)
            if (a.charAt(i) != b.charAt(i))
                return i;
        
        return (-1);
    }
    
    public static void main(String[] args) {
        
        Scanner ler = new Scanner(System.in);
        
        String linha2, linha1;
        
        linha1 = ler.nextLine();
        linha2 = ler.nextLine();
        
        int r = destingue(linha1, linha2);
        
        if (r>0){
            System.out.print(linha1.charAt(r));
            System.out.println(linha2.charAt(r));
        }
        else
            System.out.println("Nenhum");
        
    }
}
