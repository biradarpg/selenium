package com.crm.qa.logs;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//types of log
// what is log capturing info/activities at the time of execution
/* 1 info
 * 2 Waring
 * 3 error
 * 4 fatal
 *	 TRACE
	DEBUG
	INFO
	WARN
	ERROR
	FATAL
	ALL
 * how to generate logs? use Apache log4j
 * How it works it reads log4j configuration from log4j.properties
 * where to create? Create inside resources folder
 * 
 */

public class LoginTest {
	WebDriver driver;
	public static Logger log = Logger.getLogger(LoginTest.class.getName());
	@BeforeMethod
	public void setUp(){
		log.info("****************************** Starting test cases execution  *****************************************");
		String log4jpath=System.getProperty("user.dir")+"\\src\\main\\resourses\\log4j.properties";
		PropertyConfigurator.configure(log4jpath);
		System.setProperty("webdriver.chrome.driver", "G:\\Selenium\\chromeDriver\\chromedriver (2).exe");
		driver=new ChromeDriver();
		log.info("launching chrome broswer");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://www.freecrm.com");
		log.info("entering application URL");
		log.warn("Hey this just a warning message");
		log.fatal("hey this is just fatal error message");
		log.debug("this is debug message");
	}
	@Test(priority=1)
	public void freeCrmTitleTest(){
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** freeCrmTitleTest *****************************************");
		String title=driver.getTitle();
		System.out.println(title);
		log.info("login page title is--->"+title);
		Assert.assertEquals(title, "Free CRM software in the cloud powers sales and customer service");
		
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** freeCrmTitleTest *****************************************");
		
	}
	@Test(priority=2)
	public void freeCrmLogoTest(){
		
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** freemCRMLogoTest *****************************************");
		
			boolean flag=driver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed();
			Assert.assertTrue(flag);
			
			log.info("****************************** ending test case *****************************************");
			log.info("****************************** freemCRMLogoTest *****************************************");
	}
	@AfterMethod
	public void tearDown(){
		driver.close();
		log.info("****************************** Browser is closed *****************************************");
	}

}
