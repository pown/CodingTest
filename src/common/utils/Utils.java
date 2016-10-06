package common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
	
	public static int generateMonth(String month)
	  {
		  int monthVal=0;
		  try
		  {
		  Date date = new SimpleDateFormat("MMM").parse(month);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
	      monthVal=cal.get(Calendar.MONTH);
	      System.out.println(monthVal+1);
		  }
		  catch(ParseException p)
		  {
			  p.printStackTrace();
		  }
		  
		  return monthVal+1;
	  }
	
	/**
	 * Selects the Date,Month and Year entered by the User
	 * @param driver 
	 * @param presentDate : Checks the present date.
	 * @param dateToBeSelected :Date Entered by the user.
	 */

	public static void selectDate(WebDriver driver,String presentDate,String dateToBeSelected){
		String MONTHINRTEXT="›";
		String MONTHDCRTEXT="‹";
		String YEARINRXTEXT="»";
		String YEARDCRXTEXT="«";
		String Day=dateToBeSelected.split("-")[0];
		String Month=dateToBeSelected.split("-")[1];
		String ORIGINALYEAR=presentDate.split("-")[2];
		int MONTHVAL=(Integer.parseInt(presentDate.split("-")[1])-Integer.parseInt(Month));
		selectValue(driver,MONTHVAL,MONTHINRTEXT,MONTHDCRTEXT);
		int YEARVAL=Integer.parseInt(ORIGINALYEAR)-Integer.parseInt(dateToBeSelected.split("-")[2]);
		selectValue(driver,YEARVAL,YEARINRXTEXT,YEARDCRXTEXT);
		WebElement element=driver.findElement(By.xpath(".//div[@class='calendar popup']/table/tbody/tr/td[text()="+Day+"]"));
		if(element.isEnabled() && element.isDisplayed())
		{
			element.click();
		}

	}

	/**
	 * Selects the appropriate Month and Year Value 
	 * @param driver
	 * @param value : Decided whether the value is to be incremented or decremented
	 * @param INRVAL :Text associated with incremental value
	 * @param DCRVAL :Text associated with decremental value.
	 */
	public static void  selectValue(WebDriver driver,int value,String INRVAL,String DCRVAL)
	{
		WebElement element;
		if(value==0){}
		else if (value < 0) {
			element=driver.findElement(By.xpath(".//div[@class='calendar popup']/table/thead/tr[2]/td[text()='"+INRVAL+"']"));
			int i=value;
			while(i<0){
				element.click();
				i++;
			}
		} 
		else {
			element=driver.findElement(By.xpath(".//div[@class='calendar popup']/table/thead/tr[2]/td[text()='"+DCRVAL+"']"));
			int i=value;
			while(i>=1)	{
				element.click();
				i--;
			}
		}
	}
	
	public static String getTodaysDate()
	{
		String dateval="";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		//get current date time with Date()
		Calendar today = Calendar.getInstance();
		
		// If you want the day/month in String format  
		dateval = today.get(Calendar.DAY_OF_MONTH)+"-"+(today.get(Calendar.MONTH)+1)+"-"+today.get(Calendar.YEAR);
	  	return dateval;
	}
	
	

}
