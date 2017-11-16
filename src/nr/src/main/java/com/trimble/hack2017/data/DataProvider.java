package com.trimble.hack2017.data;

import java.util.List;

import com.trimble.hack2017.transportation.GeoPoint;

public interface DataProvider {
	
	public <T> List<T> queryByGeoBounds(GeoPoint start, GeoPoint end);

}
