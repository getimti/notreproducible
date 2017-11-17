package com.trimble.hack2017.data;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import com.trimble.hack2017.transportation.GeoPoint;

public class EventbriteDataProvider implements DataProvider {

	public <T> List<T> queryByGeoBounds(GeoPoint start, GeoPoint end, Map<String, Object> params) {
		RestAssured.baseURI = "https://www.eventbriteapi.com/v3";		
		RequestSpecification httpRequest = RestAssured.given();
		
		
		String url = "/events/search/?token=4472PMIWHTUD2J5CDRB6&location.viewport.northeast.latitude="
				+ "{start.1}&location.viewport.northeast.longitude={start.2}"
				+ "&location.viewport.southwest.latitude={end.1}&location.viewport.southwest.longitude={end.2}"
				+ "&start_date.range_start={start_date}&start_date.range_end={end_date}";
		
		url = url.replace("{start.1}", String.valueOf(start.getLat()))
				.replace("{start.2}", String.valueOf(start.getLng()))
				.replace("{end.1}", String.valueOf(end.getLat()))
				.replace("{end.2}", String.valueOf(end.getLng()))
				.replace("{start_date}", (String) params.get("startDate"))
				.replace("{end_date}", (String) params.get("endDate"));
		
		Response response = httpRequest.request(Method.GET, url);
 		List<String> matchingTweets = response.getBody().jsonPath().getList("statuses.text");
		return null;
	}

	public <T> List<T> queryByGeoBounds(GeoPoint start, GeoPoint end) {
		// TODO Auto-generated method stub
		return null;
	}

}
