import java.util.HashMap;
import java.util.Scanner;
public class Cigarras{
    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        int primeiro=in.nextInt();
        HashMap<Integer, Integer> mapa=new HashMap<Integer, Integer>();
        int p, t;
        p=primeiro;
        t=in.nextInt();
        while(t!=0){
            mapa.put(p, t);
            p=t;
            t=in.nextInt();}
        mapa.put(p, t);
        p=primeiro;
        while(p!=0){
            System.out.println(p);
            p=mapa.get(p);}}}
