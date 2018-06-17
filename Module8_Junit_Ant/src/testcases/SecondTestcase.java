package testcases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SecondTestcase {
	@BeforeClass  //for @BeforeClass and @AfterClass annotation the method must be static
	public static void begning(){ 
		System.out.println("********** BEgning *******");
		//Assume.assumeTrue(checkLogin());
	}
	@AfterClass
	public  static void ending(){
		System.out.println("*********** Ending ***************");
	}
	public static boolean checkLogin(){
		return false;
	}
	@Before
	public void openBrowser(){
		System.out.println("opening browser...");
	}
	@Test
	public void sendEmailTest(){
		System.out.println("Sending Email....");
	}
	@Test
	public void sendMessage(){
		System.out.println("Sending Message");
	}
	@After
	public void closeBrowser(){
		System.out.println("Closing browser....");
	}
}
