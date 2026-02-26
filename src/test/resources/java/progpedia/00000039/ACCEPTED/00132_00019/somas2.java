import java.util.*;

public class somas2{
	
	public static void main(String[] args){
		ArrayList<Integer> somasArrayList = new ArrayList<Integer>();
		ArrayList<Integer> reference = new ArrayList<Integer>();
		ArrayList<Integer> target = new ArrayList<Integer>();
		
		Scanner input=new Scanner(System.in);
		//quantidade de numeros
		int n=input.nextInt();

		for (int i=0;i<n;i++)
			reference.add(input.nextInt());
		
		//quantidade perguntas
		int p=input.nextInt();
		
		for (int i=0;i<p;i++)
			target.add(input.nextInt());
		
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				somasArrayList.add(reference.get(i)+reference.get(j));
			}	
		}
		
		
		
		Collections.sort(somasArrayList);
		/*
		System.out.println(reference);
		System.out.println(target);
		*/
		for(int targetVal: target)
		{	
			for(int k = 0; k < somasArrayList.size(); k++)
			{
				if(k==0 && somasArrayList.get(0) > targetVal){
					System.out.println(somasArrayList.get(k));
					break;
				}else if(somasArrayList.get(k).intValue() == targetVal)
				{
					System.out.println(somasArrayList.get(k));
					break;
				}else if(somasArrayList.get(k) > targetVal){
					//System.out.println("\tEntered: " + somasArrayList.get(k));
					
					if( Math.abs(somasArrayList.get(k-1) - targetVal) == Math.abs(somasArrayList.get(k) - targetVal))
						System.out.println(somasArrayList.get(k-1)+" "+somasArrayList.get(k));
					else if( Math.abs((somasArrayList.get(k-1) - targetVal)) > Math.abs((somasArrayList.get(k) - targetVal)))
						System.out.println(somasArrayList.get(k));
					else
						System.out.println(somasArrayList.get(k-1));
					
					break;
					
				}else if(k==somasArrayList.size() -1 && somasArrayList.get(0) < targetVal){
					System.out.println(somasArrayList.get(k));
					break;
				}
			}
		}
		
	}
}//Fim da classe principal




