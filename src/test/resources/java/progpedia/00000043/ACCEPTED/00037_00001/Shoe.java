import java.util.*;

class Packages{
	float ratio;
	int index;

	Packages(float r, int i){
		ratio = r;
		index = i;
	}
}


class Shoe{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();

		Packages shoes[] = new Packages[n];

		float rate = 0;

		for(int i=0; i<n; i++){
			int days = in.nextInt();
			int ticket = in.nextInt();

			rate = (float) days/ticket;

			shoes[i] = new Packages(rate, i);
		}

		Arrays.sort(shoes, new Comparator<Packages>(){
			public int compare(Packages p1, Packages p2){
				if(p1.ratio < p2.ratio) return -1;
				if(p1.ratio > p2.ratio) return 1;
				return 0;
			}
		});

		for(int i=0; i<n-1; i++){
			System.out.print(shoes[i].index+1+" ");
		}
		System.out.println(shoes[n-1].index+1);
	}
}