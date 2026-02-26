import java.util.*;

class infra_estrutura{

	public static void main(String[] args)
	{
		Scanner std = new Scanner(System.in);
		int nLocal = std.nextInt();
		int lMin = std.nextInt();
		int lMax = std.nextInt();
		int cMin = std.nextInt();
		int cMax = std.nextInt();
		int altMin = std.nextInt();
		int origem = std.nextInt();
		int destino = std.nextInt();
		int inicio = 0;
		int fim = 0;
		int l = 0;
		int c = 0;
		int a = 0;
		int aux = 0;
		boolean f1,f2,f3;
		f1 = false;
		f2 = false;
		f3 = false;
		int conta=0;
		aux = std.nextInt();
		while(aux!=(-1))
		{
			inicio = aux;
			fim = std.nextInt();
			l = std.nextInt();
			c = std.nextInt();
			a = std.nextInt();
			if(l >= lMax)
			{
				f1 = true;
			}
			else
			{
				if(l<=lMax && l>=lMin)
				{
					f1 = true;
				}
				else
				{
					f1 = false;
				}
			}
			if(c >= cMax)
			{
				f2 = true;
			}
			else
			{
				if(c<=cMax && c>=cMin)
				{
					f2 = true;
				}
				else
				{
					f2 = false;
				}
			}
			if(a>=altMin)
			{
				f3 = true;
			}
			else
			{
				f3 = false;
			}
			if(f1 == true && f2 == true && f3 == true)
			{
				conta++;
				f1 = false;
				f2 = false;
				f3 = false;
			}
			aux = std.nextInt();
		}
		System.out.println(conta);
	}
}
