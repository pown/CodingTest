package common.test;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.utils.PropertyFileUtil;
import common.utils.SeleniumDriver;
import common.utils.Utils;

/**
 * Selects the date
 * Date is provided in test.properties file
 * @author PRelan
 *
 */

public class Assignment3 {
	SeleniumDriver seleniumDriver;
	public WebDriver driver=null;
	Properties runtimevalue = null;
	String DATETOBESELECTED="";

	@BeforeTest
	public void setUp()
	{   
		this.runtimevalue = PropertyFileUtil.loadProperties(System.getProperty("user.dir")+File.separator+"inputproperties"+File.separator+"test.properties");
		seleniumDriver=new SeleniumDriver();
		driver=seleniumDriver.selectBrowser(PropertyFileUtil.getPropertyValue(runtimevalue, "browser"));
		driver.manage().window().maximize();

	}

	//3.1.1. Take input a date in the format dd-MM-yyyy.
	//3.1.2. Navigate to http://calendarview.org/
	@Test(priority=0)
	public void openBrowser()
	{
		DATETOBESELECTED=PropertyFileUtil.getPropertyValue(runtimevalue,"selectDate");
		driver.get(PropertyFileUtil.getPropertyValue(runtimevalue, "URL"));
		WebDriverWait wait=new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[@id='popupDateField']")));

	}

	@Test(dependsOnMethods={"openBrowser"})
	public void testDateResult()
	{
		//3.1.3. Click show calendar in “Popup Calendar” section.
		WebElement element=driver.findElement(By.xpath(".//div[@id='popupDateField']"));
		element.click();

		//3.1.4. Verify today’s date is highlighted.
		//get the date from Element
		String todayDate=Utils.getTodaysDate();
		element=driver.findElement(By.xpath(".//div[@class='calendar popup']/table/thead/tr/td[@class='title']"));
		String month=element.getText();
		String year=month.split("\\s")[1];
		month=month.split("\\s")[0];


		//Get the day from WebElement
		element=driver.findElement(By.xpath(".//div[@class='calendar popup']/table/tbody/tr/td[@class='selected today']"));
		String day=element.getText();
		String date1=day+"-"+Utils.generateMonth(month)+"-"+year;

		//Assert date value with the original value
		boolean dateval=todayDate.equals(date1);
		org.testng.Assert.assertTrue(dateval);

		//3.1.5. Select the given date with minimum clicks if the date is not in current month.
		Utils.selectDate(driver,date1,DATETOBESELECTED);

	}	

	//3.1.6. Verify the date.
	@Test(dependsOnMethods={"testDateResult"})
	public void verifyResults(){
		String selectedDate=driver.findElement(By.xpath(".//div[@id='popupDateField']")).getText();
		selectedDate=selectedDate.split("-")[2]+"-"+selectedDate.split("-")[1]+"-"+selectedDate.split("-")[0];
		boolean val=selectedDate.equals(DATETOBESELECTED);
    	Assert.assertTrue(val);
	
	}

	@AfterTest
	public void cleanUp()
	{
		seleniumDriver.closeBrowser();
	}


}
