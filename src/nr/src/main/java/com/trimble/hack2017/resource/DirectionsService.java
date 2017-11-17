package com.trimble.hack2017.resource;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.trimble.hack2017.bus.GoogleDirectionService;
import com.trimble.hack2017.data.TwitterDataProvider;

public class DirectionsService {

	public static void main(String[] args) {

		// 1. extract start and end address from args

		String startAddress = "Boston,MA";
		String endAddress = "Concord,MA";		

		//		2. Call google Directions API to get directions	


		//		3. Check response.routes.bounds params to get lat lon
					//using response.steps.legs.endlocation instead

		GoogleDirectionService dirService = new GoogleDirectionService();
		List<JsonObject> routeDetails = dirService.getRoute(startAddress, endAddress);
	//	dirService.getAlternateRoute(startAddress, endAddress, new GeoPoint());

		System.out.println("Waypoints "+routeDetails);
		
		//		4. Call external services with that Geo bounds

		List<String> knownObstacles = new ArrayList();		
		knownObstacles.add("traffic");
		knownObstacles.add("fire");
		knownObstacles.add("crowd");
		knownObstacles.add("heavy,traffic");
		knownObstacles.add("flood");
		knownObstacles.add("rain");
		knownObstacles.add("trump,visit");		

		//			//using waypoints instead	
		//		
		////		4.1 Tweeter field
		JsonObject legs = (JsonObject) routeDetails.get(0).get("legs");
		TwitterDataProvider twitterSource = new TwitterDataProvider();
//		for(JsonElement segment : legs) {
//			List<String> tweets = twitterSource.queryByGeoBounds(segment.getAsJsonObject(), 2, knownObstacles);
//			
//			//if tweets.size() exceeds threshold
//			// dirService.getAlternateRoute(startAddress,endAddress,wayPointToAvoid);
//		}
		//			
		////		4.2 Road work Data set
		//			// ----> Imti
		//			
		////		4.3 Local Events Database
		//			// ----> Imti
		//			
		////		4.4. Local constructions projects db (schedules)
		//			// ----> Imti
		//			
		////		4.5 Traffic condition
		//			BingMapsDataProvider trafficSource = new BingMapsDataProvider();
		//			
		//			//how to compute maparea from wayPoint ?
		//			
		//			for(GeoPoint wayPoint : wayPoints) {
		//				List<String> areas = trafficSource.getTrafficInfo(mapArea, severity, type);
		//			}
		//			
		//		5. If any events fall in that geolocation, get alternatives or add nearby waypoints (close by major streets)

					//magic

		//		6. provide smarter directions


	}

}
