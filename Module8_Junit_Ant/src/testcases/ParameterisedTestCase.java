package testcases;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
//1st step
@RunWith(Parameterized.class)
public class ParameterisedTestCase {
	//2nd step
	public String userName;
	public String Password;
	int pin;
	//3rd step
	public ParameterisedTestCase(String userName,String password,int pin) {
		this.userName=userName;
		this.Password=password;
		this.pin=pin;
		
	}
	@Parameters
	public static  Collection<Object[]> getdata(){
		// no of rows = no of times u wan to execute no of col = no of parameters u want to passs
		
		Object[][] data= new Object[3][3];
		
		//1st row 
		data[0][0]="user1";
		data[0][1]="pass";
		data[0][2]=42423;
		//2nd
		
		data[1][0]="user2";
		data[1][1]="pass2";
		data[1][2]=989898;
		
		// 3rd
		data[2][0]="user3";
		data[2][1]="pass3";
		data[2][2]=989898;
		
		return Arrays.asList(data);
	}
	@Test
	public void registerTest(){
		System.out.println(" Username "+userName +" Password "+Password+" Pin " + pin);
		
	}

}
