package testcases;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class UnderStandingAssertion {
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Test
	public void testFriendListFB(){
		int actual_friends=100;
		int expected_friends=1001;
	
		/*if(actual_friends==expected_friends){
			System.out.println("Pass");
		}else{
			System.out.println("Fail");
		}*/
		System.out.println("A");
		try{
		Assert.assertEquals(expected_friends, actual_friends);
		}catch(Throwable t){
			System.out.println("ERROR ENCOUNTERED");
			//java code to report error
			errorCollector.addError(t);
		}
	
		System.out.println("B");
		
		try{
			Assert.assertEquals("A", "B");
			}catch(Throwable t){
				System.out.println("ERROR ENCOUNTERED");
				//java code to report error
				errorCollector.addError(t);
			}
		try{
			Assert.assertEquals("A", "A");
			}catch(Throwable t){
				System.out.println("ERROR ENCOUNTERED");
				//java code to report error
				errorCollector.addError(t);
			}
	}

}
