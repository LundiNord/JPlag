import java.util.Scanner;
public class SopaLetras {
    static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
        String a=ler.nextLine(),b=ler.nextLine();
        boolean flag=false;
        if(a.length()>=b.length())
            for(int i=0;i<b.length();i++)
            {
                if(a.charAt(i)!=b.charAt(i))
                {
                    System.out.print(a.charAt(i));
                    System.out.println(b.charAt(i));
                    flag=true;
                    break;
                }
            }
        else
            for(int i=0;i<a.length();i++)
            {
                if(b.charAt(i)!=a.charAt(i))
                {
                    System.out.print(a.charAt(i));
                    System.out.println(b.charAt(i));
                    flag=true;
                    break;
                }
            }
        if(!flag)
            System.out.println("Nenhum");
    } 
}
