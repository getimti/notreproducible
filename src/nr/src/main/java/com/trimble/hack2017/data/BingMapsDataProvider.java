package com.trimble.hack2017.data;

import java.util.List;

import com.trimble.hack2017.internal.enums.Keys;
import com.trimble.hack2017.transportation.GeoPoint;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BingMapsDataProvider implements DataProvider {

	public <T> List<T> getTrafficInfo(GeoPoint point, int surroundingAreaInMiles, List<String> knownObstacles) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://dev.virtualearth.net/REST/v1";		
		RequestSpecification httpRequest = RestAssured.given();
		
		String q = formQuery(knownObstacles);
		String geocode=formGeoCode(point, surroundingAreaInMiles);
		
		Response response = httpRequest.request(Method.GET, "/v1/Traffic/Incidents/37,-105,45,-94?key="+Keys.BING);
 		List<String> matchingTweets = response.getBody().jsonPath().getList("statuses.text"); 		
 		return (List<T>) matchingTweets; 
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
}
