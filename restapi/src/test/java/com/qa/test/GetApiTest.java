package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
	TestBase testBase;
	public String serviceUrl;
	public String appUrl;
	public String url;
	public CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUP() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		appUrl = prop.getProperty("serviceURL");

		url = serviceUrl + appUrl;

	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		// 1st Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code " + statusCode);
		Assert.assertEquals( 200,RESPONSE_STATUS_CODE_200,
				"Status code is not matching");

		// 2 JSON String
		String responseString = EntityUtils.toString(
				closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from" + responseJson);
		String perpageValue = TestUtil.getValueByJPath(responseJson,
				"/per_page");
		System.out.println("Per Page Value " + perpageValue);
		// single value Assertion
		Assert.assertEquals(Integer.parseInt(perpageValue), 3);

		String totalpageValue = TestUtil.getValueByJPath(responseJson,
				"/total_pages");
		System.out.println("Total Page Value " + totalpageValue);
		Assert.assertEquals(Integer.parseInt(totalpageValue), 4);

		// Assertion for getting JSON Array
		String lastName = TestUtil.getValueByJPath(responseJson,
				"/data[0]/last_name");
		String firstName = TestUtil.getValueByJPath(responseJson,
				"/data[0]/first_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avtar = TestUtil
				.getValueByJPath(responseJson, "/data[0]/avatar");

		System.out.println(" last_name -->" + lastName);
		System.out.println(" first_name --> " + firstName);
		System.out.println(" id --> " + id);
		System.out.println(" avtar --> " + avtar);

		// 3 all headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders(); // gives
																		// information
																		// off
																		// all
																		// headers

		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("Hader Array " + allheaders);
	}
	
	@Test
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		RestClient restClient=new RestClient();
		
		HashMap<String,String>headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		closeableHttpResponse =restClient.get(url, headerMap);
		
		//1 Get Status
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is "+ statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not matching");
		
		//Json String 
		String responseString=EntityUtils.toString(
				closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("Json String "+ responseJson);
		
		String perpage=TestUtil.getValueByJPath(responseJson, "/per_page");
		
		Assert.assertEquals(perpage, 3);
		System.out.println("PerPage value"+perpage);
		
		String totalpage=TestUtil.getValueByJPath(responseJson, "/total_page");
		Assert.assertEquals(totalpage, 4);
	}

}
