package common.test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Assignment2 {
	int[][] coordinateValues;
	public static void main(String[] args) {

		insertValues(readArrayValues());
	}	
	/**
	 * Calculates the distance of each co-ordinates and store them in Linked Hash Map
	 * @param XYCoordinates
	 */
	public static void insertValues(int [][]XYCoordinates)
	{
		int arr[][]=XYCoordinates;
		LinkedHashMap<int[][],Long> in=new LinkedHashMap<int[][],Long>();
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){

				//Checks if the comparison is  made between 2 similar co-ordinates				    
				if((arr[i][0]==arr[j][0]) & (arr[i][1]==arr[j][1])){
					//Do Nothing as the co-ordinates will be same                      	 
				}
				else
				{
					//Calculates distance between two co-ordinates	 
					calDistance(arr[i][0],arr[i][1],arr[j][0],arr[j][1]);

					//Inserts the values in Linked Hash map
					in.put(new int[][]{{arr[i][0],arr[i][1]},{arr[j][0],arr[j][1]}},calDistance(arr[i][0],arr[i][1],arr[j][0],arr[j][1]));
				}
			}
		}
		printValues(in);
	}

	/**
	 * Prints the values associated with all the co-ordinates along with the highest value 
	 * @param hash:Values to be printed
	 */
	public static void printValues(LinkedHashMap<int[][],Long>hash)
	{
		LinkedHashMap<int[][],Long>mapValues=hash;
		Iterator it=mapValues.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			int[][] key = (int[][])entry.getKey();
			Long value = (Long)entry.getValue();
			System.out.print("Distance between co-ordinates ");
			for(int i=0; i<key.length; i++) {
				System.out.print("["+key[i][0]+","+key[i][1]+"]" );
			}
			System.out.print(" is"+"-->"+value);
			System.out.println();
		}	 	
		System.out.println("Longest Distance calculated is:"+getHighestValue(mapValues)); 	

	}

	/**
	 * Calculates  distance between co-ordinates
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return calculated distance between the co-ordinates
	 */
	public static long calDistance(int x1,int y1,int x2,int y2)
	{
		long value;
		value=(long)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
		return value;
	}

	/**
	 * Returns the maximum value in the Linked Hash Map.
	 * @param hash Accepts a parameter as linked hash map.
	 * @return
	 */
	public static  long getHighestValue(LinkedHashMap<int[][],Long>hash)		
	{
		return Collections.max(hash.values());
	}

	/**
	 * Accepts the values of X & Y co-ordinates from user and stores it into an array. 
	 * @return 2D Array which can be used 
	 */
	public static int[][] readArrayValues()
	{
		int test[][]=new int [10][2];
		Scanner scanner=new Scanner(System.in);
		for(int i=1;i<=10;i++)
		{
			System.out.println("Enter the Value X"+i);
			test[i-1][0]=scanner.nextInt();
			System.out.println("Enter the Value Y"+i);
			test[i-1][1]=scanner.nextInt();		
		}
		return test;
	}
}
