package com.trimble.hack2017.data;

import java.util.List;

import com.google.gson.JsonObject;
import com.trimble.hack2017.transportation.GeoPoint;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TwitterDataProvider implements DataProvider {
	
	public void auth() {
		RestAssured.baseURI = "https://api.twitter.com/1.1";		
		RequestSpecification httpRequest = RestAssured.given();
		
		 int status = httpRequest.auth().oauth("UyMCykdR4BwcWBXB1mDubsVTQ", "V3tL74lFvqe4shVkSLYgW2TFOdDRTrY759ERKuN5k6r7LAfxld", "1044207192-REd4d5HwcgnZdSzBCVL8AwPoiybsjk8ZdqpUXNt", "aglyG0kY2yP3En2CUmJGMI52fnNnOxI3R5v0xSq9NRDPc")
				 .when().post("https://api.twitter.com/1.1").getStatusCode();		 
		 System.out.println(status);	
	}

	public List<String> queryByGeoBounds(GeoPoint point, int surroundingAreaInMiles, List<String> knownObstacles) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://api.twitter.com/1.1";		
		RequestSpecification httpRequest = RestAssured.given();
		
		String q = formQuery(knownObstacles);
		String geocode=formGeoCode(point, surroundingAreaInMiles);
		
		Response response = httpRequest.request(Method.GET, "/tweets.json?q="+q+"&geocode="+geocode);
 		List<String> matchingTweets = response.getBody().jsonPath().getList("statuses.text");
 		return matchingTweets;
	}

	private String formGeoCode(GeoPoint point, int radius) {
		return point.getLat()+","+point.getLng()+","+radius+"mi";
	}

	private String formQuery(List<String> knownObstacles) {
		String query="";
		for(String s: knownObstacles)
			query = query+","+s;
		return query;
	}

	public <T> List<T> queryByGeoBounds(GeoPoint start, GeoPoint end) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public static void main(String[] args) {
		new TwitterDataProvider().auth();
	}

	public List<String> queryByGeoBounds(JsonObject segment,
			int surroundingAreaInMiles, List<String> knownObstacles) {
		
		// get GeoPoint from segment
		GeoPoint point = null;
		
		RestAssured.baseURI = "https://api.twitter.com/1.1";		
		RequestSpecification httpRequest = RestAssured.given();
		
		String q = formQuery(knownObstacles);
		String geocode=formGeoCode(point, surroundingAreaInMiles);
		
		Response response = httpRequest.request(Method.GET, "/tweets.json?q="+q+"&geocode="+geocode);
 		List<String> matchingTweets = response.getBody().jsonPath().getList("statuses.text");
 		return matchingTweets;
	}
	
}
