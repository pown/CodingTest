package common.utils;

import java.io.File;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This SeleniumDriver class contains all the methods to perform operations on driver instance.
 * @author PRelan
 *
 */
public class SeleniumDriver
{
	static String webDriver="WebDriver";
	static String firefox="FireFox";
	static String internetExplorer="InternetExplorer";
	static String googleChrome="GoogleChrome";
	static DesiredCapabilities capabilities;
	public static WebDriver browser;



	public synchronized WebDriver selectBrowser(String  browserName)
	{
		if(browserName.equalsIgnoreCase(firefox))
		{
			try{
				browser=new FirefoxDriver();
			}catch(Exception e)
			{
				System.out.println("Message"+e.getMessage());
			}

		}
		else if(browserName.equalsIgnoreCase(internetExplorer))
		{
			try{
				File IEDriver=new File("browserDrivers"+File.separator+"IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
				DesiredCapabilities cap=DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				browser=new InternetExplorerDriver(cap);
			}
			catch(Exception f){
				System.out.println("File Not Found"+f.getMessage());

			}

		}
		else if(browserName.equalsIgnoreCase(googleChrome))
		{
			try{
				File chromeDriver=new File("browserDrivers"+File.separator+"chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
				DesiredCapabilities cap=DesiredCapabilities.chrome();
				browser=new ChromeDriver(cap);
			}
			catch(Exception f){
				System.out.println("File Not Found"+f.getMessage());

			}
		}
		return browser;
	}
	/**
	 * This method will close the running driver instance.
	 */
	public void closeBrowser()
	{
		browser.close();
		browser.quit();
	}

}
