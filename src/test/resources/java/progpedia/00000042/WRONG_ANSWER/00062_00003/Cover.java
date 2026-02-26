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

		int counter = 0;

		int size=0;

		int ri = 0;
	

		for(int i=0; i<ncasos; i++){
			// System.out.println("Antes do if "+segmentos[i].begin+" "+segmentos[i].end+" "+end);

			int tempsize = segmentos[i].end -segmentos[i].begin;

			if(i+1<ncasos && segmentos[i+1].begin==segmentos[i].begin){
				if(segmentos[i].end > segmentos[i+1].end){
					end = ri =  segmentos[i].end;
					counter++;
				}
				else{
					end = ri = segmentos[i+1].end;
					counter++;
				}
				// System.out.println("Primeiro if = "+end+" "+ri+" "+counter);
			}

			else if(segmentos[i].begin <= end && segmentos[i].end > ri){

				// System.out.println(segmentos[i].begin+" "+segmentos[i].end+" "+ end);

				end = ri = segmentos[i].end;
				// size = tempsize;
				// ri = segmentos[i].end;
				counter++;
			}
			if(end>=total){
				break;
			}
		}


		System.out.println(counter);
	}
}