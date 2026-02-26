import java.util.*;


class Pyramids
{
    public static void main(String args[])
    {
    Scanner stdin= new Scanner(System.in);
    int N=stdin.nextInt();
    int D=stdin.nextInt();

    int C[]=new int[D];
    int P[]=new int[D];

    for(int i=0;i<D;i++)
        {
        C[i]=stdin.nextInt();
        P[i]=stdin.nextInt();
        }
    System.out.println(PyramidPaths(N,C,P)+"");

    }
    public static long PyramidPaths(int N,int Camada[],int Position[])
    {
    long Pyramid[][]=new long[N][N];

    Pyramid[0][0]=1;
    for(int i=1;i<N;i++)
        {
        for(int i2=0;i2<=i;i2++)
            {
            if(isD(Camada,Position,Math.abs(i-N),i2+1))
                Pyramid[i][i2]=0;
            else if(i2==0)
                Pyramid[i][i2]=Pyramid[i-1][i2];
            else if(i2==i)
                Pyramid[i][i2]=Pyramid[i-1][i2-1];
            else
                Pyramid[i][i2]=Pyramid[i-1][i2-1]+Pyramid[i-1][i2];
            }
        }
    long sum=0;
    for(int i=0;i<N;i++)
        sum+=Pyramid[N-1][i];

    return sum;

    }

    public static boolean isD(int c[],int p[],int camada,int pos)
    {
    for(int i=0; i<c.length ;i++)
        {
        if(c[i]==camada && p[i]==pos)
            return true;
        }
    return false;
    }


}