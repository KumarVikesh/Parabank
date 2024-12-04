package com.parabank.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.parabank.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig rc = new ReadConfig();
	
	String url = rc.getBaseUrl();
	String browser = rc.getBrowser();
	
	public static WebDriver driver;
	public static Logger logger;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
	
	@BeforeClass
	public void setup()
	{
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		
		logger = LogManager.getLogger("ParaBank");
	}
	
	@AfterClass
	public void tearDown()
	{
		//driver.close();
		driver.quit();
	}
	
	/*public void captureScreenShot(WebDriver driver, String testName) throws IOException
	{
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir"+"//Screenshot//" + testName + ".png"));
		
		FileUtils.copyFile(src, dest);
	}*/
} 
