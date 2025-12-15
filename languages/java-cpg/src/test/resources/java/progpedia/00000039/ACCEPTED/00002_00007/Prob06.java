import java.util.*;

class Prob06 
{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int n = input.nextInt();
	ArrayList<Integer> list = new ArrayList<Integer>();
	ArrayList<Integer> queries= new ArrayList<Integer>();
	for (int i=0;i<n;i++) {
	    list.add(input.nextInt());
	}
	int p = input.nextInt();
	for (int i=0;i<p;i++) {
	    queries.add(input.nextInt());
	}
	solve(list,queries);
    }
    
    public static void solve(ArrayList<Integer> list, ArrayList<Integer> queries) {
	
	
	ArrayList<Integer> values = new ArrayList<Integer>();
	int sum=0;
	for (int j=0;j<list.size();j++) {
	    for (int l=j+1;l<list.size();l++) {
		sum=list.get(j)+list.get(l);
		values.add(sum);
	    }
	}
	Collections.sort(values);
	/*
	int contador=0;
	for (int value : values) {
	    System.out.println(contador + "   " + value);
	    contador++;
	}
	System.out.println();*/
	
	for (int i=0;i<queries.size();i++) {
	    int key=queries.get(i);
	    int index=bsearch(values,0,values.size()-1,key);
	    ArrayList<Integer> answer = new ArrayList<Integer>();
	    
	    if (index>0){
		int dif1=Math.abs(values.get(index-1)-key);
		int dif2=Math.abs(values.get(index)-key);
		
		if (dif1<dif2){
		    answer.add(values.get(index-1));
		}
		else if (dif2<dif1){
		    answer.add(values.get(index));
		}
		else if (dif1==dif2){
		    answer.add(values.get(index-1));
		    answer.add(values.get(index));
		}
	    }
	    else{
		answer.add(values.get(index));
	    }
	    Set<Integer> answerHs = new LinkedHashSet<Integer>();
	    answerHs.addAll(answer);
	    answer.clear();
	    answer.addAll(answerHs);

	    for (int n=0;n<answer.size();n++) {
		System.out.print(answer.get(n));
		if (n==answer.size())
		    System.out.println();
		else if (n<answer.size()-1)
		    System.out.print(" ");
		
	    }
	    System.out.println();
	}
    }
    public static int bsearch(ArrayList<Integer> list, int low, int high, int key) {
	int middle=0;
	while(low<high) {
	    middle = low + (high-low)/2;
	    if (list.get(middle)==key)
		return middle;
	    else if (list.get(middle)>key)
		high=middle;
	    else 
		low=middle+1;
	}
	if (list.get(low)<key)
	    return high;
	return low;
    }
    
}
