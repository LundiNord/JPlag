/*  Second Practicl Work in DAA 2008/2009
*	Sylwia Bugla m.073016011
*	file ProblemA.java
*/
import java.util.Arrays;
import java.lang.System;

 	
public class ProblemA {
        
	private static int NumberOfScenarios = 0;
	private static int NumberOfStudents = 0;
	private static int pcase = 1;
	public static String inputd = "4#4#1 3 2 4 3#4 0#2 2 1 3#3 2 2 1#6#1 2 3 5#2 2 3 4#4 1 2#3 2 2 1#6 1 5#5 2 6 1#8#1 4 6 2 4 5#3 1 2#2 2 3 4#4 1 5#6 0#5 3 4 8 7#7 1 5#8 2 5 3#10#1 4 6 2 4 5#3 2 2 1#9 0#2 2 3 4#4 2 5 9#6 1 1#5 3 4 8 7#7 1 5#8 1 5#10 1 9#";
   
    public static void main(String[] args) {
    	String [] Data;
    	Data = inputd.split("#");
    	    	
    	for (int linenumber = 0;linenumber < Data.length; linenumber++){
			   if (linenumber == 0){
				   NumberOfScenarios = Integer.parseInt(Data[linenumber].trim());
			   }else{
				   while (NumberOfScenarios > 0){
					    NumberOfStudents = Integer.parseInt(Data[linenumber].trim());
					   //Creating array of connections
					   int[][] nData = new int[NumberOfStudents+1][NumberOfStudents+1];
					   while (NumberOfStudents > 0){
						  String strLine = Data[++linenumber];
						  //filling the array
						  nData = cratearray(strLine,nData);
						  NumberOfStudents--;							   
					   }
						//proper algorithm
						System.out.println("Caso #"+pcase);
						checkConnections(nData);
						linenumber++;   
						NumberOfScenarios--;
						pcase++;
					}
			   }				
		    }
      }
    public static int[][] cratearray(String strLine, int[][] nData){
		String [] temp = null;
	    temp = strLine.split(" ");
	    
	    int i = Integer.parseInt(temp[0].trim());
	    	for (int j = 2; j < temp.length; j++)
	    		nData[i][Integer.parseInt(temp[j].trim())] = 1; //1 - the path exist
	    		nData[i][i]=1;    		
	return nData;    		
	} 
	
	public static void checkConnections(int[][] nData){
		nData = Floyd(nData);		
		groupSearching(nData);		
	}	
	
	public static void groupSearching(int[][] nData){
		int i,j;
		int n=nData.length;
		int[][] groupsArray = new int[n][0];
		
		for (i=1; i<n; i++){
			for (j=1; j<n; j++) {
				if (i==j) {
					continue;
				}else{
					if (nData[i][j] == 1){
						if (nData[i][j]==nData[j][i]){
							// i and j are in the same group
							groupsArray=groupsArray(groupsArray,i,j,"the_same");
							nData[j][i]=0;
						}else{
							//i and j are in different group
							groupsArray=groupsArray(groupsArray,i,j,"different");
						}
					}					
				}
			}
		}
		printresult(groupsArray);
	}
	
	public static int[][] groupsArray(int[][] array, int i, int j, String a){
		int empty = array[0].length;
		int m,n,ii,ij,temp,temp1,indexi,indexj,itemp;
		int[] B = {0};
										
		if(a == "the_same"){
			
			if(empty == 0){
				array[0] = insertElement(array[0],i, 0);
				array[0] = insertElement(array[0],j, 0);
				//break;
			}else{
				//search in the array
				for(m=0;m<array.length;m++){
					// Ensure array sorted
					Arrays.sort(array[m]);	
					//checking i
					indexi = arraySearch(array[m],i);//checks if i is in array -- indexi<0 if not
					//checking for j
					indexj = arraySearch(array[m],j);//checks if i is in array -- indexi<0 if not
					if(indexi >= 0 && indexj >= 0)break;
					if(indexi < 0 && indexj < 0){
						if(array[m].length == 0){
						//add to new row	
							array[m] = insertElement(array[m],i, -(indexi+1));
							array[m] = insertElement(array[m],j, -(indexj+1));
							break;
						}else continue;
					}
					if(indexi >= 0 && indexj < 0){
						temp = m;ij=indexj;
						//search in the rest of the array for j
						temp1 = 0;
						for(n=m+1;n<array.length;n++){
							Arrays.sort(array[n]);
							itemp = arraySearch(array[n],j);
							if(itemp>=0){
								array[temp] = merge(array[temp],array[n]);
								array[n]=B;
								temp1=1;
								break;
							}
						}
						if(temp1==1)break; else {
							Arrays.sort(array[temp]);
							array[temp] = insertElement(array[temp],j, -(ij+1));
							break;							
						}						
					}
					
					if(indexi < 0 && indexj >= 0){
						temp = m;ii=indexi;
						//search in the rest of the array for i
						temp1 = 0;
						for(n=m+1;n<array.length;n++){
							Arrays.sort(array[n]);
							indexi = arraySearch(array[n],i);
							if(indexi>=0){
								array[temp] = merge(array[temp],array[n]);
								array[n]=B;
								temp1 = 1;
								break;
							}
						}
						if(temp1==1)break; else {
							array[temp] = insertElement(array[temp],i, -(ii+1));
							break;					
						}
					}					
				}				
			}	
			
		}else{
			//case of different
			if(empty == 0){
				array[0] = insertElement(array[0],i, 0);
				array[1] = insertElement(array[1],j, 0);
			}else{
				//search in the array
				for(m=0;m<array.length;m++){
					// Ensure array sorted
					Arrays.sort(array[m]);	
					//checking i
					indexi = arraySearch(array[m],i);//checks if i is in array -- indexi<0 if not
					//checking for j
					indexj = arraySearch(array[m],j);//checks if i is in array -- indexi<0 if not
			
					if(indexi >= 0 && indexj >= 0)break;
					if(indexi < 0 && indexj < 0){
						if(array[m].length == 0 && array[m+1].length == 0){
						//add to new row	
							array[m] = insertElement(array[m],i, -(indexi+1));
							array[m+1] = insertElement(array[m+1],j, -(indexj+1));
							break;
						}else continue;
					}
					if(indexi >= 0 && indexj < 0){
						//search in the rest of the array for j
						//if find then quit, if not then add to the next empty row
						ij=0;temp1=-1;
						for(n=m+1;n<array.length;n++){
							if(array[n].length== 0 && temp1 == -1 )temp1=n;
							Arrays.sort(array[n]);
							itemp = arraySearch(array[n],j);
							if(itemp>=0) ij=1;
						}
						//if didn't find -- add to the next empty row.
						//if found -- break;
						if (ij==1)break;
						else{
							Arrays.sort(array[temp1]);
							array[temp1] = insertElement(array[temp1],j, 0);
							break;
						}
					}
					
					if(indexi < 0 && indexj >= 0){
						//search in the rest of the array for i
						ij=0;temp1=-1;
						for(n=m+1;n<array.length;n++){
							if(array[n].length== 0 && temp1 == -1 )temp1=n;
							Arrays.sort(array[n]);
							itemp = arraySearch(array[n],i);
							if(itemp>=0) ij=1;								
						}
						//if didn't find -- add to the next empty row.
						//if found -- break;
						if (ij==1)break;
						else{
							Arrays.sort(array[temp1]);
							array[temp1] = insertElement(array[temp1],i, 0);
							break;
						}						
					}					
				}				
			}
		}
	return array;   
	}
	private static int[] insertElement(int original[], int element, int index) {
		int length = original.length;
        int destination[] = new int[length+1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index+1, length-index);
        return destination;
    }
	
	public static int[] merge(int[] array1, int[] array2){
		int[] C= new int[array1.length+array2.length];
			   System.arraycopy(array1, 0, C, 0, array1.length);
			   System.arraycopy(array2, 0, C, array1.length, array2.length);
	return C;
	}

	public static int arraySearch(int[] array, int val){
		// Search for element in array
	    int index = Arrays.binarySearch(array, val);
		return index ;	
	}
	
	public static int[][] Floyd(int[][] nData){
		int i,j,k;
		int n=nData.length;
		//Floyd algorithm 
		for (k=0; k<n; k++)
			for (i=0; i<n; i++)
				for (j=0; j<n; j++) {
					if((nData[i][k]==1)&&(nData[k][j]==1)) {
						nData[i][j]=1;
					}
				}
	return nData;
	}
	public static void printresult(int[][] nData){
		int i,j,t1,t2;
		int group = 0;
		int out=0;		
				
		for (i=0; i<nData.length; i++)	{
			t1=0;t2=0;
			if(nData[i].length >= 4){
				group++;
			}else{
				if((nData[i].length == 1) || (nData[i].length == 0)){
					for (j=0; j<nData[i].length; j++){
						if (nData[i][j] != 0)t1++ ;
					}											
				}else{
					t2=nData[i].length;
				}					
			}
			out = out + t1 + t2;	
		}		
	System.out.println(group+" "+out);
	}
   
}
