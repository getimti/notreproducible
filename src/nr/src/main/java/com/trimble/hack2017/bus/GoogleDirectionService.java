package com.trimble.hack2017.bus;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.trimble.hack2017.internal.enums.Keys;
import com.trimble.hack2017.transportation.GeoPoint;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GoogleDirectionService {

	//public static void main(String[] args) {

	public List<GeoPoint> getWayPoints(String source, String dest) {		
		Response response = null; // this.getRoute(source, dest);
		int size = response.getBody().jsonPath().getList("routes[0].legs[0].steps.end_location").size();

		List<GeoPoint> wayPoints = new ArrayList<GeoPoint> ();
		for(int i=0; i<size; i++ ) {
			double lat = Double.parseDouble(response.getBody().jsonPath().get("routes[0].legs[0].steps["+i+"].end_location.lat").toString());
			double lng = Double.parseDouble(response.getBody().jsonPath().get("routes[0].legs[0].steps["+i+"].end_location.lng").toString());
			String way = response.getBody().jsonPath().get("routes[0].legs[0].steps["+i+"].html_instructions").toString();
			String placeName = extractWayPointName(way);
			wayPoints.add(new GeoPoint(lat,lng,placeName));
		}		
		return wayPoints;

	}

	public String getAlternateRoute(String source, String dest, GeoPoint wayPointToAvoid) {
		List<GeoPoint> wayPoints = new ArrayList<GeoPoint> ();

		RestAssured.baseURI = "https://maps.googleapis.com/maps/api";	
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/directions/json?origin="+source+"&destination=" + dest+
				// "&alternatives=true"+
				"&key="+Keys.GOOGLE);
		List<JsonObject> routes= response.getBody().jsonPath().getList("routes");
		System.out.println("ROUTES......"+routes);	
		
		return null;
		//TODO get another route, check if the route does not contain wayPointToAvoid
		/*for(int i=0;i<routes.size();i++) {
			if(route.steps.end_location.lat.contains(wayPointToAvoid.getLat()) && route.steps.end_location.lng.contains(wayPointToAvoid.getLng())) {
				continue;
			}
			return alternateRoute; 
		}*/
	}

	public String extractWayPointName(String step) {
		String wayPointName = step.substring(step.lastIndexOf("<b>"), step.lastIndexOf("</b>"));
		return wayPointName;
	}

	public List<JsonObject> getRoute(String source,String dest) {		
		RestAssured.baseURI = "https://maps.googleapis.com/maps/api";		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/directions/json?origin="+source+"&destination=" + dest+"&key="+Keys.GOOGLE);
		List<JsonObject> routes= response.getBody().jsonPath().getList("routes");
		return routes;
	}

}
