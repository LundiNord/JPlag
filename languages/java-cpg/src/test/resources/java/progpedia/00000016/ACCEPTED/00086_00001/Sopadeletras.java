import java.util.Scanner;

public class Sopadeletras {

    
    public static void main(String[] args) {
    
        Scanner stdin = new Scanner(System.in);
        
        String palavra1 = stdin.nextLine();
        String palavra2 = stdin.nextLine();
       
        int min;
        char c1,c2;
        boolean terminou = false;
        int i=0;
       
            
        if(palavra1.length()>palavra2.length()) {
            min = palavra2.length();
        }
        else {
            min = palavra1.length();
        }
           
        while(i<min && terminou!=true) {
               
            c1 = palavra1.charAt(i);
            c2 = palavra2.charAt(i);
                          
            if(c1!=c2) {
                System.out.print(c1);
                System.out.print(c2); 
                terminou = true;
            }
            i++;
           
        }
        if(terminou==false) {
            System.out.println("Nenhum");
        }
        
               
    }
   
    
}   
