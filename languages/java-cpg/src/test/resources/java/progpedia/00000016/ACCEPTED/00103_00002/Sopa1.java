import java.io.*;
import java.util.*;

class Sopa1{
    public static void main(String args[]){
        Scanner in= new Scanner(System.in);
        String palavra1= in.nextLine();
        String palavra2= in.nextLine();
        int n=Math.min(palavra1.length(), palavra2.length());
        char a1, a2;
        int nenhum=0;
        for(int i=0; i<n; i++){
            if(palavra1.charAt(i)!=palavra2.charAt(i)){
                a1=palavra1.charAt(i);
                a2=palavra2.charAt(i);
                System.out.print(a1);
                System.out.print(a2);
                System.out.println();
                return;
            }
            else{ nenhum++;}
        }
        if(nenhum==n) System.out.println("Nenhum");
    }
}

        
