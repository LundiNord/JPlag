import java.util.*;
/**
 *
 * @author Ana
 */
public class Problema2A {
    public static void main(String args[]){
        Scanner in = new Scanner (System.in);
        String pal1 = in.nextLine();
        String pal2 = in.nextLine();
       
        int p1 = pal1.length();
        int p2 = pal2.length();
       
        int i = 0;
       
        while (i != p1 && i != p2){
            if (pal1.charAt(i) == pal2.charAt(i))
                i++;
            else{
		System.out.print(pal1.charAt(i));
		System.out.println(pal2.charAt(i));
		break;
	    }
               
        }
        if (i == p1 || i == p2)
            System.out.println("Nenhum");
       
    }
   
}