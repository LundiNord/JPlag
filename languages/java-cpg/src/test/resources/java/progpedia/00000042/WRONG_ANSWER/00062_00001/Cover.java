import java.util.*;

class Segment{
	int begin;
	int end;

	Segment(int b, int e){
		begin = b;
		end = e;
	}
}

class Cover{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int total = in.nextInt();

		int ncasos = in.nextInt();

		Segment segmentos[] = new Segment[ncasos];

		for(int i=0; i<ncasos; i++){
			int begin = in.nextInt();
			int end = in.nextInt();
			Segment segmento = new Segment(begin, end);
			segmentos[i]=segmento;
		}

		Arrays.sort(segmentos, new Comparator<Segment>(){
			public int compare(Segment s1, Segment s2){
				return s1.begin-s2.begin;
			}
		});
		// System.out.println();

		int end = 0;

		int big = 0;

		int index = 0;

		int counter = 0;

		for(int i=0; i<ncasos; i++){
			// System.out.println("Antes do if "+segmentos[i].begin+" "+segmentos[i].end);
			if(segmentos[i].begin <= end && segmentos[i].end > big){

				// System.out.println(segmentos[i].begin+" "+segmentos[i].end+" "+ end);

				end = segmentos[i].end;
				big = segmentos[i].end;
				counter++;
			}
		}

		System.out.println(counter);
	}
}