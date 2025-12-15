import java.util.Scanner;
public class InfraEstrutura {
    public static Scanner ler= new Scanner(System.in);
    public static void main(String[] args) {
        int n=lerProb();
        System.out.println(n);
    }
    public static int lerProb()
    {
        int k=0;
        ler.nextInt();
        int lMin=ler.nextInt(),lMax=ler.nextInt(),cMin=ler.nextInt(),cMax=ler.nextInt(),altura=ler.nextInt(),l,c,h;
        ler.nextInt();ler.nextInt();
        int n=ler.nextInt();
        while(n!=-1)
        {
            ler.nextInt();
            l=ler.nextInt();
            c=ler.nextInt();
            h=ler.nextInt();
            if(l>=lMin && c>=cMin && h>=altura)
                k++;
            n=ler.nextInt();
        }
        return k;
    }
}
