package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//note:  Marshalling is converting the data present in an Object into a an JSON/xml format 
	//and viewing it in an JSON/xml format (POJO-->JSON(Marshaling)
	// unmarshalling is reverse of it converting an JSON/xml file into an object.
	//(JSON-->POJO (UnMarshaling)
	//GetMethod without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient =HttpClients.createDefault(); //creates client connection and returs CloseableHttpClient object
		HttpGet httpget=new HttpGet(url); //http get request
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget); //hit the get URL
		/*//1st  Status Code
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code "+statusCode);
		//2 JSON String
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("Respon unmarshalling is reverse of it converting an xml file into an object.se JSON from" +responseJson);
		// 3 all headers
		Header[] headersArray= closeableHttpResponse.getAllHeaders(); //gives information off all headers
		
		HashMap<String,String> allheaders= new HashMap<String, String>();
		for(Header header:headersArray){
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("Hader Array "+allheaders);*/
		return closeableHttpResponse;
	}
	
	//GetMethod With headers
	public CloseableHttpResponse get(String url,HashMap<String, String>headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient=HttpClients.createDefault(); //creates client connection and returs CloseableHttpClient object
		HttpGet httpget=new HttpGet(url); //http get request
		
		for(Map.Entry<String,String>entry:headerMap.entrySet()){
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse coloseableHttpResponse=httpclient.execute(httpget); //hit the get URL
		return coloseableHttpResponse;
	}
	
	//Post Method
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
		for(Map.Entry<String, String> entry:headerMap.entrySet()){
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse coloseableHttpResponse= httpclient.execute(httppost);
		return coloseableHttpResponse;
		
	}
}
