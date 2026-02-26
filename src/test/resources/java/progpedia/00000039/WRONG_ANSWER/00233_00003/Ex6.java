import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Ex6 {
	
	public static int binarySearch(int[] nums, int check, int x){
        int hi = nums.length - 1;
        int lo = 0;
        int guess = -1;
        while(hi >= lo){
                guess = lo + ((hi - lo) / 2);
                
                if(nums[guess] > check){
                        hi = guess - 1;
                }else if(nums[guess] < check){
                        lo = guess + 1;
                }else{
                	if(guess == x && guess + 1 < nums.length){
                    	guess++;
                    }
                    else if(guess == x && guess - 1 > 0){
                    	guess--;
                    }

                    return guess;
                }
        }
        if(guess == x && guess + 1 < nums.length){
        	guess++;
        }
        else if(guess == x && guess -1 > 0){
        	guess--;
        }
        return guess;
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		int [] list = new int [n];
		
		
		for(int i = 0; i<n ; i++){
			list[i] = in.nextInt();
		}
		
		Arrays.sort(list);
		
		
		int p = in.nextInt();
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		int high = 9999999;
		int index = 1;
		
		for(int i = 0; i<p ; i++){
			
			int soma = in.nextInt();
			
			for(int x = 0; x<n ; x++){
				int check = -1;
				if(list[x] > soma){
					check = list[x] - soma;
				}
				else{
					check = soma - list[x];
				}
		
				
				int so = binarySearch(list, check, x);
			
				
				
				if(Math.abs(soma - list[so] - list[x]) < high && so != x){
					results.clear();
					results.add(new Integer(list[x] + list[so]));
					high = Math.abs(soma - list[so] - list[x]);
					
					index = 1;
				}
				else if(Math.abs(soma - list[so] - list[x]) == high && so != x){
					boolean notIn = true;
					for(int y = 0; y<index; y++){
						if(results.get(y).intValue() == list[x] + list[so]){
							notIn = false;
						}
					}
					
					if(notIn){
						results.add(list[x] + list[so]);
						index++;
					}
					
					
				}
				
				if(so-1 > 0){
				
				if(Math.abs(soma - list[so-1] - list[x]) < high && so-1 != x){
					results.clear();
					results.add(new Integer(list[x] + list[so-1]));
					high = Math.abs(soma - list[so-1] - list[x]);
					index = 1;
				}
				else if(Math.abs(soma - list[so-1] - list[x]) == high && so-1 != x){
					boolean notIn = true;
					for(int y = 0; y<index; y++){
						if(results.get(y).intValue() == list[x] + list[so-1]){
							notIn = false;
						}
					}
					
					if(notIn){
						results.add(list[x] + list[so-1]);
						index++;
					}
				}
				
				}
				
				
				if(so+1 < 0){
				
				if(Math.abs(soma - list[so+1] - list[x]) < high && so+1 != x){
					results.clear();
					results.add(new Integer(list[x] + list[so+1]));
					high = Math.abs(soma - list[so+1] - list[x]);
					index = 1;
				}
				else if(Math.abs(soma - list[so+1] - list[x]) == high && so+1 != x){
					boolean notIn = true;
					for(int y = 0; y<index; y++){
						if(results.get(y).intValue() == list[x] + list[so+1]){
							notIn = false;
						}
					}
					
					if(notIn){
						results.add(list[x] + list[so+1]);
						index++;
					}
				}
				}
			
				

			}
			Object [] re = results.toArray();
			
			Arrays.sort(re);
			
			for(int y = 0; y<index; y++){
				if(y+1==index){
					System.out.println(((Integer) re[y]).intValue());
				}
				else{
					System.out.print(((Integer) re[y]).intValue() + " ");
				}
			}

			results.clear();
			high = 9999999;
			index = 1;
			
		}
		
		in.close();
	}

}
