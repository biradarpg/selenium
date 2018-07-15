package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase {
	TestBase testBase;
	public String  serviceUrl;
	public String appUrl;	
	public String url;
	RestClient restClient;
	public CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUP() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		appUrl = prop.getProperty("serviceURL");
		url = serviceUrl + appUrl;
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient=new RestClient();
		HashMap<String,String>headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		
		//Jackson API
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("morpheus","leader"); //expected users object
		
		// Object to JSON file conversion
		mapper.writeValue(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//Object to json string
		String usersJsonstring=mapper.writeValueAsString(users);
		System.out.println(usersJsonstring);
		
		closeableHttpResponse=restClient.post(url, usersJsonstring, headerMap);
		
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println(statusCode);
		
		//status code validation
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
		
		//JSON validation
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		
		System.out.println(responseJson);
		//JSON To Java
		Users usersResponse=mapper.readValue(responseString, Users.class); //actual users object getting
		System.out.println(usersResponse);
		
		Assert.assertTrue(users.getName().equals(usersResponse.getName()));
		Assert.assertTrue(users.getJob().equals(usersResponse.getJob()));
		
		System.out.println(usersResponse.getId());
		System.out.println(usersResponse.getCreatedAt());
	}
	

}
