import java.util.LinkedList;
import java.util.Scanner;

public class Sopaletras {

  
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        int min;
        //LinkedList<Character> result = new LinkedList<Character>();
        String resultados = "";
        boolean terminou = false;
        
        String palavra1 = stdin.nextLine();
        String palavra2 = stdin.nextLine();
        
        if(palavra1.length()>palavra2.length()) {
            min = palavra2.length();
        }
        else {
            min = palavra1.length();
        }
        
        for(int i=0; i<min && terminou!=true ; i++) {
            
            char letra1, letra2;
            letra1 = palavra1.charAt(i);
            letra2 = palavra2.charAt(i);
            
            if(letra1!=letra2) {
                resultados = resultados + letra1 + letra2;
                terminou = true;
                
            }  
        }
        
        if(resultados.equals("")) {
            System.out.println("Nenhum");
        }
        else {
            System.out.println(resultados);
        }
        
        
        
    }
    
}
