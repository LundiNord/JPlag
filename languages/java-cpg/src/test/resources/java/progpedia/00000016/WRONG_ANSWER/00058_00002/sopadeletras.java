
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
        
        char[] linha1 = new char[20];
        char[] linha2 = new char[20];
        
        String a = linha1.toString();
        String b = linha2.toString();
        a = ler.nextLine();
        b = ler.nextLine();
        
        int r = destingue(a, b);
        
        if (r>0){
            char[] resultado = new char[2];
        
            resultado[0] = a.charAt(r);
            resultado[1] = b.charAt(r);
            
            System.out.println(resultado);
        }
        else
            System.out.println("Nenhum");
        
    }
}
