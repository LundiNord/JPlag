import java.util.*;

class Shoe implements Comparable<Shoe>{
	int days,fine,num;
	float ratio;

	Shoe(int d,int f,int i){
		days = d;
		fine = f;
		num = i;
		ratio = (float)d/f;
	}

	@Override
    public int compareTo(Shoe t){
    	if(ratio<t.ratio)
    		return -1;
    	else if(ratio>t.ratio)
    		return 1;
    	else
    		return 0;
    }
}

class Shoemaker{
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		Shoe[] a = new Shoe[n];

		for(int i=0;i<n;i++)
			a[i] = new Shoe(input.nextInt(),input.nextInt(),i+1);

		Arrays.sort(a);

		for(int i=0;i<n;i++){
				System.out.print(a[i].num+" ");
		}
	}
}