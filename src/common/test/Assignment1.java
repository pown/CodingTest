package common.test;

import java.util.Scanner;

/**
 * The below problem calculates the value of series.
 * @author PRelan
 *
 */
public class Assignment1 {

	/**Calculates Series
	 * 
	 * @param number :input value
	 */
	public void calculateSeries(int number)
	{
		long sum=0;
		for(int i =1;i <= number;i++){			  
			sum=(long) (sum+(Math.pow(i,i)/i)); }
		System.out.println(sum);


	}

	public static void main(String[] args) {
		Assignment1 sp=new Assignment1();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the  value");
		int num=scanner.nextInt();
		sp.calculateSeries(num);

	}


}
